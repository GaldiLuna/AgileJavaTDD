package Tests.sis.testing;
import junit.framework.TestSuite;

public class OldTestandoSuites {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(Testes.suite());
        //suite.addTest(Tests.sis.TodosTestes.suite());
        return suite;
    }
}