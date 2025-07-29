package Tests.sis.testing;

import java.lang.reflect.*;
import java.util.*;

public class TestRunnerUI {
    private TestRunner runner;

    public static void main(String[] args) throws Exception {
        String testClassName;
        if (args.length > 0) {
            testClassName = args[0];
        } else {
            // Se nenhum argumento for passado, usa um nome de classe padrão para teste
            System.out.println("No test class argument provided. Running TestRunnerTest by default.");
            testClassName = "Tests.sis.testing.TestRunnerTest"; //Nome da classe teste padrão
        }

        TestRunnerUI ui = new TestRunnerUI(testClassName);
        ui.run();
        System.exit(ui.getNumberOfFailedTests());
    }

    public TestRunnerUI(String testClassName) throws Exception {
        runner = new TestRunner(testClassName);
    }

    public void run() {
        runner.run();
        showResults();
        showIgnoredMethods();
    }

    public int getNumberOfFailedTests() {
        return runner.failed();
    }

    private void showResults() {
        System.out.println("passed: " + runner.passed() + "failed: " + runner.failed());
    }

    private void showIgnoredMethods() {
        if (runner.getIgnoredMethods().isEmpty())
            return;
        System.out.println("\nIgnored Methods");
        for (Map.Entry<Method, Ignore> entry: runner.getIgnoredMethods().entrySet()) {
            Ignore ignore = entry.getValue();
            System.out.printf("%s: %s (by %s)\n", entry.getKey(), Arrays.toString(ignore.reasons()), ignore.initials());
            //Linha acima imprime formatado, imprime array de razões e imprime as iniciais
        }
    }
}
