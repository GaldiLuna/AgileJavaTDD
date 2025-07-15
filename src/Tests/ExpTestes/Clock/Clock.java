package Tests.ExpTestes.Clock;

import java.util.*;

public class Clock implements Runnable {
    private ClockListener listener;
    private boolean run = true;

    public Clock(ClockListener listener) {
        this.listener = listener;
        new Thread(this).start();
    }

    public void stop() {
        run = false;
    }

    public void run() {
        Thread.currentThread().setPriority(Thread.NORM_PRIORITY - 1); //Define a prioridade da Thread atual
        long lastTime = System.currentTimeMillis();
        while (run) {
            try {
                Thread.sleep(10); //Pausa menor para maior frequência de verificação
            } catch (InterruptedException e) {}
            long now = System.currentTimeMillis();
            if ((now / 1000) - (lastTime / 1000) >= 1) { //Verifica se pelo menos 1 segundo completo passou
                listener.update(new Date(now));
                lastTime = now;
            }
            //listener.update(new Date());
        }
    }
}
