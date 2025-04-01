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

        Matematica m1 = new Matematica();

        //Realizando a sobrecarga dos métodos
        int soma1 = m1.somar(10, 20);
        System.out.println("Total da soma1 = " + soma1 + "\n");
        int soma2 = m1.somar(10, 20, 40);
        System.out.println("Total da soma2 = " + soma2 + "\n");
        double soma3 = m1.somar(1.0, 2.0);
        System.out.println("Total da soma3 = " + soma3 + "\n");

        Operacao op = new Operacao();
        int x = 10;
        op.trocar(x);
        System.out.println("Número trocado = " + x);

        Valor valor = new Valor();
        valor.v = 10;
        op.alterarValor(valor);
        System.out.println("valor foi iniciado em 0 e após alteração => " + valor.v + "\n \n");

        ContaBancaria cb = new ContaBancaria();
        String banco = cb.getBanco();
        cb.setNumConta(543789);
        cb.setAtiva(true);

        double saldo1 = cb.getSaldo();
        cb.depositar(1000);
        cb.sacar(500);
        cb.pix(300);
        cb.receberSalario(2500);
        cb.pagarContas(2000);

        double saldo2 = cb.getSaldo();
        int conta = cb.getNumConta();
        //String extrato = "Seu saldo inicial é de R$ " ;

        System.out.println(banco + ":\n Conta " + conta + ":\n Seu saldo atual é de R$ " + saldo2);
        //System.out.println(banco + ":\n Conta " + conta + ":\n Seu saldo atual é de R$ " + saldo2);
        System.out.println("\n \n");

        //Retângulo
        Retangulo r1 = new Retangulo();
        double area1 = r1.calcularArea();
        System.out.println("A área do Retângulo 1 é de: " + area1);

        Retangulo r2 = new Retangulo(5, 7);
        double area2 = r2.calcularArea();
        System.out.println("A área do Retângulo 2 é de: " + area2);

        Quadrado q = new Quadrado(7);
        System.out.println("A área do Quadrado é de: " + q.calcularArea() + "\n");

//        Comentado o bloco com a criação do objeto para chamar a classe e seu metodo estático logo abaixo.
//        Matematica m2 = new Matematica();
//        int soma4 = m2.novoSomar(10, 5);
//        int subtracao = m2.subtrair(50, 10);

        int soma4 = Matematica.novoSomar(10,5);
        System.out.println("Total da soma4 = " + soma4 + "\n");

        int subtracao = Matematica.subtrair(50, 10);
        System.out.println("Total da subtracao = " + subtracao + "\n");

//Criação do bloco estático elimina a necessidade do construtor abaixo para inicializar a variável com valor.
//        Contador c1 = new Contador();
//        c1.incrementar();
//        Contador c2 = new Contador();
//        c2.incrementar();
//        Contador c3 = new Contador();
//        c3.incrementar();

        System.out.println("O valor inicial do Contador chamando o método getValor é: " + Contador.getValor());
        System.out.println("O valor inicial do Contador chamando o atributo da classe é: " + Contador.valor);

        Contador.incrementar();
        Contador.incrementar();
        Contador.incrementar();

        System.out.println("Após incrementar, o valor do Contador chamando o método getValor é: " + Contador.getValor());
        System.out.println("Após incrementar, o valor do Contador chamando o atributo da classe é: " + Contador.valor + "\n");

        Constantes.setAV1(8.0);
        double nota1 = Constantes.getAV1();
        Constantes.setAV2(4.0);
        double nota2 = Constantes.getAV2();
        Constantes.setAV3(7.0);
        double nota3 = Constantes.getAV1();
        Constantes.setAV4(5.0);
        double nota4 = Constantes.getAV2();
        double media = Constantes.mediaProvas();

        String situacao;
        if (media >= Constantes.MEDIA_FINAL) {
            situacao = Constantes.APROVADO;
        } else {
            situacao = Constantes.REPROVADO;
        }

        String mensagem;
        if (situacao == Constantes.APROVADO) {
            mensagem = Constantes.WINNER;
        } else {
            mensagem = Constantes.LOSER;
        }

        System.out.println("Prezado Aluno, suas notas foram: Av1- " + nota1 + ", Av2- " + nota2 + ", Av3- " + nota3 + ", Av4- " + nota4);
        System.out.println("Ficando com a Média: " + media);
        System.out.println("Sendo assim a situação do Aluno é de: " + situacao + " - " + mensagem);
    }
}
