package Tests.sis.search;
import util.*;

import junit.framework.TestCase;
import util.TestUtil;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SearchTest extends TestCase {
    //Manter URL1 para testCreate, se desejar testar via http, mas o foco é testar com arquivos.
    private static final String URL1 = "http://www.langrsoft.com";
    public static final String[] TEST_HTML = {
            "<html>",
            "<body>",
            "Book: Agile Java, by Jeff Langr<br />",
            "Synopsis: Mr Langr teaches you<br />",
            "Java via test-driven development.<br />",
            "</body></html>"
    };
    public static final String FILE = "/var/tmp/testFileSearch.html";
    public static final String URL2 = "file:" + FILE;

    protected void setUp() throws IOException {
        TestUtil.delete(FILE);
        LineWriter.write(FILE, TEST_HTML);
    }

    protected void tearDown() throws IOException {
        TestUtil.delete(FILE);
    }

    public void testCreate() throws IOException {
        Search search = new Search(URL1, "x");
        assertEquals(URL1, search.getUrl());
        assertEquals("x", search.getText());
    }

    public void testPositiveSearch() throws IOException {
        Search search = new Search(URL2, "Jeff Langr");
        search.execute();
        assertTrue(search.matches() >= 1);
        assertFalse(search.errored());
    }

    public void testNegativeSearch() throws IOException {
        final String unlikelyText = "mama cass elliott";
        Search search = new Search(URL2, unlikelyText);
        search.execute();
        assertEquals(0, search.matches());
        assertFalse(search.errored());
    }

    public void testErroredSearch() throws IOException {
        final String badUrl = "file:/nonexistent/path/z2468.html";
        Search search = new Search(badUrl, "whatever");
        search.execute();
        assertTrue(search.errored());
        assertEquals(FileNotFoundException.class, search.getError().getClass());
    }
}
