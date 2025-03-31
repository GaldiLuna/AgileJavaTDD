package AulasJava.Softblue;

public class SalaDeAula {
    private int numAlunos;
    private boolean temArmario;

    public SalaDeAula() {
        this.temArmario = true;
    }

    public SalaDeAula(int numAlunos) {
        this();
        this.numAlunos = numAlunos;
        SalaDeAula s1 = new SalaDeAula();
        SalaDeAula s2 = new SalaDeAula(20);
    }

    public int getNumAlunos() {
        return this.numAlunos;
    }

    public void setNumAlunos(int numAlunos) {
        this.numAlunos = numAlunos;
    }
}

