package Tests.ExpTestes.Clock;

import java.util.*;
import junit.framework.*;
import java.util.concurrent.locks.*; //Importar a nova API de locks

public class ClockTest extends TestCase {
    private Clock clock;
    private Lock lock;
    private Object monitor = new Object();
    private Condition receivedEnoughTics; //Objeto Condition

    protected void setUp() {
        lock = new ReentrantLock(); //Inicializa o Lock
        receivedEnoughTics = lock.newCondition(); //Cria uma condição associada ao Lock
    }

    public void testClock() throws Exception {
        final int seconds = 2;
        final List<Date> tics = new ArrayList<Date>();
        ClockListener listener = createClockListner(tics, seconds);

//        ClockListener listener = new ClockListener() {
//            private int count = 0;
//            public void update(Date date) {
//                tics.add(date);
//                if (++count == seconds)
//                    synchronized (monitor) {
//                        monitor.notifyAll();
//                    }
//            }
//        };

        clock = new Clock(listener);
        lock.lock(); //Adquire o lock

//        synchronized (monitor) {
//            monitor.wait();
//        }

        try {
            receivedEnoughTics.await(); //Espera a condição
        }
        finally {
            lock.unlock(); //Garante que o lock seja liberado
        }

        clock.stop();
        verify(tics, seconds);
    }

    private ClockListener createClockListner(final List<Date> tics, final int seconds) {
        return new ClockListener() {
            private int count = 0;

            public void update(Date date) {
                tics.add(date);
                if (++count == seconds) {
                    lock.lock(); //Adquire o lock antes de sinalizar
                    try {
                        receivedEnoughTics.signalAll(); //Sinaliza a Condition
                    }
                    finally {
                        lock.unlock(); //Garante que o lock seja liberado
                    }
                }
            }
        };
    }

    private void verify(List<Date> tics, int seconds) {
        assertEquals(seconds, tics.size());
        for (int i = 1; i < seconds; i++)
            assertEquals(1, getSecondsFromLast(tics, i));
    }
    private long getSecondsFromLast(List<Date> tics, int i) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(tics.get(i));
        int now = calendar.get(Calendar.SECOND);
        calendar.setTime(tics.get(i - 1));
        int then = calendar.get(Calendar.SECOND);
        if (now == 0)
            now = 60;
        return now - then;
    }

}
