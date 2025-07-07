package Tests.sis.testing;

import java.util.*;
import junit.framework.*;
import junit.runner.*;

public class SuiteBuilder {
    public List<String> gatherTestClassNames() {
        TestCollector collector = new ClassPathTestCollector() {
            public boolean isTestClass(String classFileName) {
                if (!super.isTestClass(classFileName))
                    return false;
                String className = classNameFromFile(classFileName);
                Class klass = createClass(className);
                return TestCase.class.isAssignableFrom(klass);
            }
        };
        return Collections.list(collector.collectTests());
    }

    private Class createClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    protected boolean isTestClass(String classFileName) {
        return
                classFileName.endsWith(".class") &&
                classFileName.indexOf('$') < 0 &&
                classFileName.indexOf("Test") > 0;
    }
}
