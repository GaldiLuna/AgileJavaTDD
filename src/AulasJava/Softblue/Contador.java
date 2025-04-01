package AulasJava.Softblue;

public class Contador {
    public static int valor;

//Criação do bloco estático elimina a necessidade do construtor abaixo para inicializar a variável com valor.
    static {
        valor = 1;
    }

//    public Contador() {
//        valor = 1;
//    }

    public static void incrementar() {
        valor++;
    }

    public static int getValor() {
        return valor;
    }
}
