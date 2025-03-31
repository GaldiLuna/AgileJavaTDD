package AulasJava.Softblue;

public class Operacao {

    void trocar(int n) {
        System.out.println("n foi iniciado em 20 e após trocado => " + n);
        n = 20;
    }

    void alterarValor(Valor valor) {
        valor.v = 20;
    }
}
