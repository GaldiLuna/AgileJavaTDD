package Tests.sis;
import Tests.sis.report.*;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TodosTestes {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(Tests.sis.report.Testes.suite());
        //suite.addTest(Tests.sis.TodosTestes.suite());
        return suite;
    }
}