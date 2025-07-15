package Tests.sis.search;

import java.util.Timer;
import java.util.TimerTask;

public class SearchScheduler {
    private ResultsListener listener;
    private Timer timer; //Objeto Timer

    public SearchScheduler(ResultsListener listener) {
        this.listener = listener;
    }

    public void repeat(final Search search, long interval) {
        timer = new Timer(); //Cria um novo Timer
        TimerTask task = new TimerTask() { //Implementa a TimerTask
            @Override
            public void run() {
                search.execute(); //Executa a busca
                listener.executed(search); //Notifica o listener
            }
        };
        //Agenda a tarefa para ser executada a cada 'interval' ms, começando imediatamente
        timer.scheduleAtFixedRate(task, 0, interval);
    }

    public void stop() {
        if (timer != null) { //Verifica se o timer foi inicializado
            timer.cancel(); //Cancela o timer e suas tarefas
        }
    }
}
