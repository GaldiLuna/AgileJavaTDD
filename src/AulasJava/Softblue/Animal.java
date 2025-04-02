package AulasJava.Softblue;

public class Animal {
    public void falar() {}
    public void comer() {}
    public void filhote() {}

//As instâncias a1, a2 e a3 chamam apenas os métodos globais, enquanto as instâncias
// dog, cat e cow chamam tanto os métodos globais quanto os métodos específicos.
    public static void main(String[] args) {
        Animal a1 = new Cachorro();
        Cachorro dog = new Cachorro();
        a1.falar();
        dog.morder();
        a1.comer();
        dog.cavar();
        a1.filhote();

        Animal a2 = new Gato();
        Gato cat = new Gato();
        a2.falar();
        cat.arranhar();
        a2.comer();
        cat.pular();
        a2.filhote();

        Animal a3 = new Vaca();
        Vaca cow = new Vaca();
        a3.falar();
        cow.chifrar();
        a3.comer();
        cow.leite();
        a3.filhote();
    }
}

class Cachorro extends Animal {
    public void falar() {
        System.out.println("Auau");;
    }
    public void morder() {
        System.out.println("Nhec!");
    }
    public void comer() {
        System.out.println("Pedigree");
    }
    public void cavar() {
        System.out.println("Dogs gostam de cavar!");
    }
    public void filhote() {
        System.out.println("O filhotinho é um Puppy\n");
    }
}

class Gato extends Animal {
    public void falar() {
        System.out.println("Miau");
    }
    public void arranhar() {
        System.out.println("Craw!");
    }
    public void comer() {
        System.out.println("Wiskas");
    }
    public void pular() {
        System.out.println("Gatos pulam bem alto!");
    }
    public void filhote() {
        System.out.println("O filhotinho é um Kitty\n");
    }
}

class Vaca extends Animal {
    public void falar() {
        System.out.println("Muuu");
    }
    public void chifrar() {
        System.out.println("Tóin!");
    }
    public void comer() {
        System.out.println("Capim");
    }
    public void leite() {
        System.out.println("Vacas produzem leite!");
    }
    public void filhote() {
        System.out.println("O filhotinho é um Bezerro");
    }
}

