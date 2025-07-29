package Tests.sis.testing;

import junit.framework.Test;
import junit.textui.*;
import java.util.*;
import java.lang.reflect.*;

public class TestRunner {
    private Class testClass;
    private int failed = 0;
    private Set<Method> testMethods = null;
    private Map<Method, Ignore> ignoredMethods = null;
    public static final String DEFAULT_IGNORE_REASON = "temporarily commenting out";

    public static void main(String[] args) throws Exception {
        String testClassName;
        if (args.length > 0) {
            testClassName = args[0];
        } else {
            // Se nenhum argumento for passado, usa um nome de classe padrão para teste
            System.out.println("No test class argument provided. Running TestRunnerTest by default.");
            testClassName = "Tests.sis.testing.TestRunnerTest"; //Nome da classe teste padrão
        }

        // Usa o TestRunner baseado em texto em vez do swingui
        // Isso executa o metodo suite() automaticamente
//        junit.textui.TestRunner.run(suite());
//        //Linha acima comentada para evitar testes longos e excessivos enquanto desenvolvo a lição 15.

        //new junit.swingui.TestRunner().run(TestRunner.class); //linha sugerida pelo livro comentada pela IA

        TestRunner runner = new TestRunner(testClassName);
        runner.run();
        System.out.println("passed: " + runner.passed() + " failed: " + runner.failed());
        if (runner.failed() > 0)
            System.exit(1);
    }

    public TestRunner(Class testClass) {
        this.testClass = testClass;
    }

    public TestRunner(String className) throws Exception {
        this(Class.forName(className));
    }

    public Set<Method> getTestMethods() {
        if (testMethods == null)
            loadTestMethods();
        return testMethods;
    }

    private void loadTestMethods() {
        testMethods = new HashSet<Method>();
        ignoredMethods = new HashMap<Method, Ignore>();
        for (Method method: testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(TestMethod.class))
                if (method.isAnnotationPresent(Ignore.class)) {
                    Ignore ignore = method.getAnnotation(Ignore.class);
                    ignoredMethods.put(method, ignore);
                } else
                    testMethods.add(method);
        }
    }

    public Map<Method, Ignore> getIgnoredMethods() {
        return ignoredMethods;
    }

    public void run() {
        for (Method method: getTestMethods())
            run(method);
    }

    private void run(Method method) {
        try {
            Object testObject = testClass.newInstance();
            method.invoke(testObject, new Object[] {});
        }
        catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof AssertionError)
                System.out.println(cause.getMessage());
            else
                e.printStackTrace();
            failed++;
        }
        catch (Throwable t) {
            t.printStackTrace();
            failed++;
        }
    }

    public int passed() {
        return testMethods.size() - failed;
    }

    public int failed() {
        return failed;
    }

    public static Test suite() {
        return new SuiteBuilder().suite();
    }

}

// Adicionar um target ao seu script de build que executa testing.TestRunner
// O metodo main, por sua vez, obtém o nome da classe do target do Ant:
//
//<target name="runAllTests" depends="build" description="run all tests">
//    <java classname="sis.testing.TestRunner" failonerror="true" fork="true">
//        <classpath refid="classpath" />
//        <jvmarg value="-enableassertions"/>
//        <arg value="sis.testing.TestRunnerTest" />
//    </java>
//</target>
