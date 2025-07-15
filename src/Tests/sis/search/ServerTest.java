package Tests.sis.search;
import util.*;

import junit.framework.TestCase;

import java.util.List;

public class ServerTest extends TestCase {
    private int numberOfResults = 0;
    private Server server;
    private static final long TIMEOUT = 3000L;
    private static final String[] URLS = { SearchTest.URL2, SearchTest.URL2, SearchTest.URL2 };

    protected void setUp() throws Exception {
        TestUtil.delete(SearchTest.FILE);
        LineWriter.write(SearchTest.FILE, SearchTest.TEST_HTML);

        ResultsListener listener = new ResultsListener() {
            public void executed(Search search) {
                numberOfResults++;
        }};

        server = new Server(listener);
    }

    protected void tearDown() throws Exception {
        assertTrue(server.isAlive());
        server.shutDown();
        server.join(3000); //Espera no máximo 3 segundos pela thread
        assertFalse(server.isAlive());
        TestUtil.delete(SearchTest.FILE);
    }

    public void testSearch() throws Exception {
        long start = System.currentTimeMillis();
        executeSearches();
        long elapsed = System.currentTimeMillis() - start;
//        long averageLatency = elapsed / URLS.length;
//        assertTrue(averageLatency < 20);
        assertTrue(elapsed < 20); //Teste de latência
        waitForResults();
    }

    public void testLogs() throws Exception {
        executeSearches();
        waitForResults();
        verifyLogs();
    }

    private void executeSearches() throws Exception {
        for (String url: URLS)
            server.add(new Search(url, "xxx"));
    }

    private void waitForResults() {
        waitForResults(URLS.length);
    }

    private boolean waitForResults(int count) {
        long start = System.currentTimeMillis();
        while (numberOfResults < count) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {}
            if (System.currentTimeMillis() - start > TIMEOUT)
                fail("timeout"); //Falha o teste em caso de timeout
//                return false;
        }
        return true;
    }

    private void verifyLogs() {
        List<String> list = server.getLog();
        assertEquals(URLS.length * 2, list.size()); //Espera o dobro de mensagens (inicio e fim)
        for (int i = 0; i < URLS.length * 2; i += 2) //Loop para pares de mensagens
            verifySameSearch(list.get(i), list.get(i + 1));
    }

    private void verifySameSearch( String startSearchMsg, String endSearchMsg) {
        String startSearch = substring(startSearchMsg, Server.START_MSG);
        String endSearch = substring(endSearchMsg, Server.END_MSG);
        assertEquals(startSearch, endSearch); //Verifica se o ID da busca é o mesmo
    }

    private String substring(String string, String upTo) {
        int endIndex = string.indexOf(upTo);
        assertTrue("didn't find " + upTo + " in " + string, endIndex != -1);
        return string.substring(0, endIndex);
    }

    public void testException() throws Exception {
        final String errorMessage = "problem";
        //Mock de Search que lança uma exceção
        Search faultySearch = new Search(URLS[0], "") {
            public void execute() {
                throw new RuntimeException(errorMessage);
            }
        };
        server.add(faultySearch);
        waitForResults(1); //Espera por 1 resultado (o da busca com erro)
        List<String> log = server.getLog();
        assertTrue(log.get(0).indexOf(errorMessage) != -1); //Verifica se a mensagem de erro está no log
    }

}
