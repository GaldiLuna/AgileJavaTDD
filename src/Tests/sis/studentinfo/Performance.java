package Tests.sis.studentinfo;

public class Performance {
    private int[] tests = {};

    public void setNumberOfTests(int numberOfTests) {
        tests = new int[numberOfTests];
    }

    public void set(int testNumber, int score) {
        tests[testNumber] = score;
    }

    public int get(int testNumber) {
        return tests[testNumber];
    }

    public double average() {
        if (tests.length == 0)
            return 0.0;
        //Definir o total como "int total = 0;" quebraria os testes em PerformanceTest.
        //Uma solução para usar total como int é colocar "(double)" antes do total no return.
        double total = 0.0;

        for (int score : tests)
            total += score;
        return total / tests.length;

        // TAMBÉM PODERIA SER USANDO O for() CLÁSSICO ABAIXO.
        //for (int i = 0; i < tests.lenght; i++)
        //      total += tests[i];
    }

    public void setScores(int... tests) {
        this.tests = tests;
    }
}
