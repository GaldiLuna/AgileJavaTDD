package Tests;

public class ThreadTest {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread() {
            public void run() {
                while (true)
                    System.out.println('.');
            }
        };
        t.setDaemon(true); //Define como thread daemon
        t.start();
        Thread.sleep(100); //Dá um tempo para a thread imprimir
        Runtime.getRuntime().exit(0); //Força a saída da aplicação
//        System.exit(0); //Isso também funcionará como saída da aplicação
    }
}
