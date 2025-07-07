package Tests.sis.testing;

import junit.framework.*;
import java.util.*;

public class SuiteBuilderTest extends TestCase {
    public void testGatherTestClassNames() {
        SuiteBuilder builder = new SuiteBuilder();
        List<String> classes = builder.gatherTestClassNames();
        assertTrue(classes.contains("Tests.sis.testing.SuiteBuilderTest"));
        assertFalse(classes.contains("Tests.sis.testing.testclasses.NotATestClass"));
    }
}
