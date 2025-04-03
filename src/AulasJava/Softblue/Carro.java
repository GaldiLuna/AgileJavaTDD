package AulasJava.Softblue;

public class Carro extends Veiculo {
    public boolean isQuatroPortas() {
        return quatroPortas;
    }

    public void setQuatroPortas(boolean quatroPortas) {
        this.quatroPortas = quatroPortas;
    }

    private boolean quatroPortas;

    @Override
    public void buzinar() {
        System.out.println("Pibite");
    }

    @Override
    public void imprimirDados() {
        super.imprimirDados(); //Forma de referenciar atributos da classe global no código local, evitando repetir código.
        System.out.println("Quatro portas: " + quatroPortas);
    }

//    public Carro(String marca) {
//        super("Lamborghini");
//        System.out.println("Carro(String)");
//    }
}
