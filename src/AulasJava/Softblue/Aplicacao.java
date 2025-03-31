package AulasJava.Softblue;

public class Aplicacao {

    public static void main(String[] args) {

        Pessoa p1 = new Pessoa();
        p1.nome = "José";

        Pessoa p2 = new Pessoa();
        p2.nome = "Maria";

        p1.receber(5);
        System.out.println("\n" + p1.nome + " recebeu um pacote de figurinhas e agora tem: " + p1.numFigurinhas);
        p1.receber(7);
        System.out.println(p1.nome + " recebeu um pacote de figurinhas e agora tem: " + p1.numFigurinhas);
        p2.receber(4);
        System.out.println(p2.nome + " recebeu um pacote de figurinhas e agora tem: " + p2.numFigurinhas);
        p2.receber(4);
        System.out.println(p2.nome + " recebeu um pacote de figurinhas e agora tem: " + p2.numFigurinhas);


        p1.dar(2, p2);
        p2.dar(1, p1);
        System.out.println(p1.nome + " e " + p2.nome + " trocaram figurinhas entre si. \n");


        System.out.println("No final: ");
        System.out.println(p1.nome + " ficou com " + p1.numFigurinhas + " figurinhas.");
        System.out.println(p2.nome + " ficou com " + p2.numFigurinhas + " figurinhas. \n" );

        Matematica m = new Matematica();

        //Realizando a sobrecarga dos métodos
        int soma1 = m.somar(10, 20);
        System.out.println("Total da soma 1 = " + soma1 + "\n");
        int soma2 = m.somar(10, 20, 40);
        System.out.println("Total da soma 2 = " + soma2 + "\n");
        double soma3 = m.somar(1.0, 2.0);
        System.out.println("Total da soma 3 = " + soma3 + "\n");

        Operacao o = new Operacao();
        int x = 10;
        o.trocar(x);
        System.out.println("Número trocado = " + x);

        Valor valor = new Valor();
        valor.v = 10;
        o.alterarValor(valor);
        System.out.println("valor foi iniciado em 0 e após alteração => " + valor.v + "\n \n");

        ContaBancaria c = new ContaBancaria();
        String banco = c.getBanco();
        c.setNumConta(543789);
        c.setAtiva(true);

        c.depositar(1000);
        c.sacar(200);

        double saldo = c.getSaldo();

        System.out.println(banco + " " + saldo);
        System.out.println("\n \n");

        //Retângulo
        Retangulo r1 = new Retangulo();
        double area1 = r1.calcularArea();
        System.out.println("A área do Retângulo 1 é de: " + area1);

        Retangulo r2 = new Retangulo(5, 7);
        double area2 = r2.calcularArea();
        System.out.println("A área do Retângulo 2 é de: " + area2);

        Quadrado q = new Quadrado(7);
        System.out.println("A área do Quadrado é de: " + q.calcularArea());
    }
}
