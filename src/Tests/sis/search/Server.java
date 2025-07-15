package Tests.sis.search;
import util.*;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server extends Thread {
    private BlockingQueue<Search> queue = new LinkedBlockingQueue<Search>();
    private ResultsListener listener;

    static final String START_MSG = "started"; //Mensagem de início
    static final String END_MSG = "finished"; //Mensagem de fim

    private static ThreadLocal<List<String>> threadLog = new ThreadLocal<List<String>>() { //ThreadLocal para log por thread
        protected List<String> initialValue() {
            return new ArrayList<String>(); //Inicializa uma lista vaiza para cada thread
        }
    };

    private List<String> completeLog = Collections.synchronizedList(new ArrayList<String>()); //Log completo, thread-safe

    public Server(ResultsListener listener) {
        // falha!
        this.listener = listener;
        start();
    }

    public void run() {
        while (true) {
//            if (!queue.isEmpty())
//                execute(queue.remove(0));
//            Thread.yield();
            try {
                execute(queue.take());
            }
            catch (InterruptedException e) {
                break; //Sai do loop infinito ao ser interrompido
            }
        }
    }

    public void shutDown() throws Exception {
        this.interrupt(); //Interrompe a thread para tirá-la do blocking state
    }

    public void add(Search search) throws Exception {
        queue.add(search);
    }

    private void execute(final Search search) {
        Thread thread = new Thread(new Runnable() { //Cria uma nova thread oara cada busca
            public void run() {
                log(START_MSG, search); //Log de início da busca na ThreadLocal da thread atual
                search.execute(); //Pode lançar uma exceção aqui
                log(END_MSG, search); //Log de fim da busca na ThreadLocal da thread atual
                listener.executed(search);
                completeLog.addAll(threadLog.get()); //Adiciona o log da thread ao log completo
            }
        });
        //Define um handler para exceções não capturadas
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread th, Throwable thrown) {
                completeLog.add(search + " " + thrown.getMessage()); //Loga a exceção
                listener.executed(search); //Notifica o listener
            }
        });
        thread.start();
    }

    private void log(String message, Search search) {
        threadLog.get().add(search + " " + message + " at " + new Date()); //Pega lista da ThreadLocal da thread atual e adiciona a mensagem
    }

    public List<String> getLog() {
        return completeLog;
    }
}
