package Tests.sis.report;
import junit.framework.TestSuite;

public class Testes {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(RosterReporterTest.class);
        return suite;
    }
}