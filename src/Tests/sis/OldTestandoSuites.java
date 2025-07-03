package Tests.sis;
import junit.framework.TestSuite;

public class OldTestandoSuites {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(Tests.sis.report.Testes.suite());
        //suite.addTest(Tests.sis.TodosTestes.suite());
        return suite;
    }
}