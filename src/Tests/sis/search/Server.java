package Tests.sis.search;
import util.*;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server extends Thread {
    //private List<Search> queue = Collections.synchronizedList(new LinkedList<Search>());
    private BlockingQueue<Search> queue = new LinkedBlockingQueue<Search>();
    private ResultsListener listener;

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

    public void add(Search search) throws Exception {
        queue.add(search);
    }

    private void execute(Search search) {
        search.execute();
        listener.executed(search);
    }

    public void shutDown() throws Exception {
        this.interrupt(); //Interrompe a thread para tirá-la do blocking state
    }
}
