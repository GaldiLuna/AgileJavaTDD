package AulasJava.Softblue;

public class Pessoa {

    String nome;
    int numFigurinhas;

    void receber(int numFigurinhas) {
        this.numFigurinhas += numFigurinhas; //Usando o this. o Java entende que estou me referindo ao atributo e o outro se refere ao parâmetro do método.
    }

    void dar(int numFigurinhas, Pessoa p) {
        this.numFigurinhas -= numFigurinhas;
        //p.numFigurinhas += numFigurinhas;
        p.receber(numFigurinhas);
    }
}
