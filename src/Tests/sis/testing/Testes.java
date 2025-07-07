package Tests.sis.testing;
import Tests.sis.report.RosterReporterTest;
import junit.framework.TestSuite;

public class Testes {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(RosterReporterTest.class);
        return suite;
    }
}