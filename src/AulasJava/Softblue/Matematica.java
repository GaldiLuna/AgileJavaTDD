package AulasJava.Softblue;

public class Matematica {

    int somar(int x, int y) {
        System.out.println("Método com 2 argumentos chamado:");
        return x + y;
    }

    int somar(int x, int y, int z) {
        System.out.println("Método com 3 argumentos chamado:");
        return x + y + z;
    }

    double somar(double x, double y) {
        System.out.println("Método double foi chamado:");
        return x + y;
    }

    public static int novoSomar(int a, int b) {
        System.out.println("Método novoSomar chamado:");
        return a + b;
    }

    public static int subtrair(int a, int b) {
        System.out.println("Método subtrair chamado:");
        return a - b;
    }
}
