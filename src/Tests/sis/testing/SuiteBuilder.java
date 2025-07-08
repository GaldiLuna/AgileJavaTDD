package Tests.sis.testing;

import java.lang.reflect.Modifier;
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
                return TestCase.class.isAssignableFrom(klass) && isConcrete(klass);
            }
        };
        return Collections.list(collector.collectTests());
    }

    private boolean isConcrete(Class klass) {
        if (klass.isInterface())
            return false;
        int modifiers = klass.getModifiers();
        return !Modifier.isAbstract(modifiers);
    }

    protected boolean isTestClass(String classFileName) {
        return
                classFileName.endsWith(".class") &&
                        classFileName.indexOf('$') < 0 &&
                        classFileName.indexOf("Test") > 0;
    }

    private Class createClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

//    public boolean contains(TestSuite suite, Class testClass) {
//        List testClasses = Collections.list(suite.tests());
//        for (Object object: testClasses) {
//            if (object.getClass() == TestSuite.class)
//                if (contains((TestSuite)object, testClass))
//                    return true;
//            if (object.getClass() == testClass)
//                return true;
//        }
//        return false;
//    }
}
