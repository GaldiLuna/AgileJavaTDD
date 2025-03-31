package AulasJava.Softblue;

public class ContaBancaria {
    private static final String BANCO = "JavaBank";
    private int numConta;
    private boolean ativa;
    private double saldo;

    public void sacar(double valor) {
        if (valor < 0) {
            return;
        }
        if (valor > saldo) {
            return;
        }
        saldo -= saldo;
    }

    public void depositar(double valor) {
        if (valor < 0) {
            return;
        }
        saldo += saldo;
    }

    public String getBanco() {
        return BANCO;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        if (numConta > 0) {
            this.numConta = numConta;
        }
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public double getSaldo() {
        return saldo;
    }

//    public void setSaldo(double saldo) {
//        this.saldo = saldo;
//    }
}
