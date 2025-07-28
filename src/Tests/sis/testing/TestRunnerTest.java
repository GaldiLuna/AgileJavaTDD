package Tests.sis.testing;

import org.junit.Ignore;

import java.util.*;
import java.lang.reflect.*;

public class TestRunnerTest {
    private TestRunner runner;
    private static final String methodNameA = "testA";
    private static final String methodNameB = "testB";
    public static final String IGNORE_REASON1 = "because";
    public static final String IGNORE_REASON2 = "why not";

    @TestMethod
    public void singleMethodTest() {
        runTests(SingleMethodTest.class);
        verifyTests(methodNameA);

//        TestRunner runner = new TestRunner(SingleMethodTest.class);
//
//        Set<Method> testMethods = runner.getTestMethods();
//        assert 1 == testMethods.size() : "expected single test method";
//
//        Iterator<Method> it = testMethods.iterator();
//        Method method = it.next();
//
//        final String testMethodName = "testA";
//        assert testMethodName.equals(method.getName()) : "expected " + testMethodName + " as test method";
//        runner.run();
//
//        assert 1 == runner.passed() : "expected 1 pass";
//        assert 0 == runner.failed() : "expected no failures";
    }

    @TestMethod
    public void multipleMethodTest() {
        runTests(MultipleMethodTest.class);
        verifyTests(methodNameA, methodNameB);

//        TestRunner runner = new TestRunner(MultipleMethodTest.class);
//        runner.run();
//
//        assert 2 == runner.passed() : "expected 2 pass";
//        assert 0 == runner.failed() : "expected no failures";
//
//        Set<Method> testMethods = runner.getTestMethods();
//        assert 2 == testMethods.size() : "expected single test method";
//
//        Set<String> methodNames = new HashSet<String>();
//        for (Method method: testMethods)
//            methodNames.add(method.getName());
//
//        final String testMethodNameA = "testA";
//        final String testMethodNameB = "testB";
//
//        assert methodNames.contains(testMethodNameA): "expected " + testMethodNameA + " as test method";
//        assert methodNames.contains(testMethodNameB): "expected " + testMethodNameB + " as test method";
    }

    @TestMethod
    public void ignoreMethodTest() {
        runTests(IgnoreMethodTest.class);
        verifyTests(methodNameA, methodNameB);
        assertIgnoreReasons();
    }

    private void runTests(Class testClass) {
        runner = new TestRunner(testClass);
        runner.run();
    }

    private void verifyTests(String... expectedTEstMethodNames) {
        verifyNumberOfTests(expectedTEstMethodNames);
        verifyMethodNames(expectedTEstMethodNames);
        verifyCounts(expectedTEstMethodNames);
    }

    private void verifyCounts(String... testMethodNames) {
        assert testMethodNames.length == runner.passed() : "expected " + testMethodNames.length + " passed";
        assert 0 == runner.failed() : "expected no failures";
    }

    private void verifyNumberOfTests(String... testMethodNames) {
        assert testMethodNames.length == runner.getTestMethods().size() : "expected " + testMethodNames.length + " test method(s)";
    }

    private void verifyMethodNames(String... testMethodNames) {
        Set<String> actualMethodNames = getTestMethodNames();
        for (String methodName: testMethodNames)
            assert actualMethodNames.contains(methodName): "expected " + methodName + " as test method";
    }

    private Set<String> getTestMethodNames() {
        Set<String> methodNames = new HashSet<String>();
        for (Method method: runner.getTestMethods())
            methodNames.add(method.getName());
        return methodNames;
    }

    private void assertIgnoreReasons() {
        Map<Method, Ignore> ignoredMethods = runner.getIgnoredMethods();
        Map.Entry<Method, Ignore> entry = getSoleEntry(ignoredMethods);
        assert "testC".equals(entry.getKey().getName()): "unexpected ignore method: " + entry.getKey();
        Ignore ignore = entry.getValue();
        String[] ignoreReasons = ignore.value();
        assert 2 == ignoreReasons.length;
        assert IGNORE_REASON1.equals(ignoreReasons[0]);
        assert IGNORE_REASON2.equals(ignoreReasons[1]);
    }

    private <K, V> Map.Entry<K, V> getSoleEntry(Map<V , V> map) {
        assert 1 == map.size(): "expected one entry";
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        return it.next();
    }
}

class SingleMethodTest {
    @TestMethod public void testA() {}
}

class MultipleMethodTest {
    @TestMethod public void testA() {}
    @TestMethod public void testB() {}
}

class IgnoreMethodTest {
    @TestMethod public void testA() {}
    @TestMethod public void testB() {}
    @Ignore({TestRunnerTest.IGNORE_REASON1, TestRunnerTest.IGNORE_REASON2}) //Esse metodo será ignorado (fornecendo a razão)
    @TestMethod public void testC() {}
}
