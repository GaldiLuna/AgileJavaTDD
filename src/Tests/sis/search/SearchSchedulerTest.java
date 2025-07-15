package Tests.sis.search;

import junit.framework.TestCase;
import util.LineWriter;
import util.TestUtil;

public class SearchSchedulerTest extends TestCase {
    private int actualResultsCount = 0;

    protected void setUp() throws Exception {
        TestUtil.delete((SearchTest.FILE));
        LineWriter.write(SearchTest.FILE, SearchTest.TEST_HTML);
    }

    protected void tearDown() throws Exception {
        TestUtil.delete(SearchTest.FILE);
    }

    public void testRepeatedSearch() throws Exception {
        final int searchInterval = 3000; //Intervalo de 3 segundos
        Search search = new Search(SearchTest.URL2, "xxx");
        ResultsListener listener = new ResultsListener() {
            @Override
            public void executed(Search search) {
                ++actualResultsCount; //Conta o número de execuções
        }};
        SearchScheduler scheduler = new SearchScheduler(listener);
        scheduler.repeat(search, searchInterval); //Agenda a busca
        final int expectedResultCount = 3; //Espera 3 execuções
        //Espera pelo tempo necessário para as execuções esperadas + um pouco
        Thread.sleep((expectedResultCount - 1) * searchInterval + 1000);
        scheduler.stop(); //Para o agendador
        assertEquals(expectedResultCount, actualResultsCount); //Verifica o resultado
    }
}
