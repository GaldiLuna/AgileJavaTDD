package Tests.sis.studentinfo;

public class Performance {
    private int[] tests;

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
        double total = 0.0;
        // TAMBÉM PODERIA SER USANDO O for() CLÁSSICO ABAIXO.
        //for (int i = 0; i < tests.lenght; i++)
        //      total += tests[i];
        for (int score : tests)
            total += score;
        return total / tests.length;
    }

    public void setScores(int... tests) {
        this.tests = tests;
    }
}
