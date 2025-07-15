package Tests.ExpTestes.Clock;

import junit.framework.TestCase;
import java.util.Date; //Para simular alarmes mais complexos, embora não usado diretamente aqui
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger; //Para contagem thread-safe

public class AlarmClockTest extends TestCase {
    private AlarmClock clock;
    //Usaremos um objeto monitor para wait/notify
    private Object monitor = new Object();
    private volatile boolean alarmReceived; //Usamos volatile para garantir visibilidade entre threads
    private AtomicInteger alarmCount; //Usar AtomicInteger para contagem thread-safe

    //Mock AlarmListener para capturar o alarme
    private class MockAlarmListener implements AlarmListener {
        @Override
        public void alarm() {
            alarmReceived = true;
            synchronized (monitor) {
                monitor.notifyAll(); //Notifica a thread de teste que o alarme disparou
            }
        }
    }

    protected void setUp() throws Exception {
        alarmReceived = false;

        alarmCount = new AtomicInteger(0);
        clock = new AlarmClock(); //Agora AlarmClock será iniciado no construtor
    }

    protected void tearDown() throws Exception {
        if (clock != null) {
            clock.stop(); //Garante que a thread do clock pare
            clock.join(500); //Dá um pequeno tempo para a thread terminar
            assertFalse("Thread do clock deveria ter parado", clock.isAlive());
        }
    }

    public void testSingleAlarm() throws Exception {
        //Crie um alarme para daqui a 50 milissegundos
        long alarmTime = System.currentTimeMillis() + 50;

        AlarmListener listener = new MockAlarmListener();
        clock = new AlarmClock(); //O clock será iniciado no schedule
        clock.schedule(alarmTime, listener);

        //Loop de espera simples para o alarme ser recebido
        long startWait = System.currentTimeMillis();
        while (!alarmReceived && (System.currentTimeMillis() - startWait < 500)) {
            Thread.sleep(10); //Pequena pausa para evitar CPU alta
        }

        assertTrue("Alarme deveria ter sido recebido", alarmReceived);
    }

    public void testSingleAlarmNotify() throws Exception {
        long alarmTime = System.currentTimeMillis() + 50; //Alarme daqui a 50ms

        AlarmListener listener = new NotifyingAlarmListener();
        clock = new AlarmClock();
        clock.schedule(alarmTime, listener);

        //O teste espera no monitor até ser notificado ou até um timeout
        synchronized (monitor) {
            //Se o alarme ainda não foi recebido, espera por ele
            if (!alarmReceived) {
                monitor.wait(500); //Espera no máximo 500ms
            }
        }

        assertTrue("Alarme deveria ter sido recebido via notify", alarmReceived);
    }

    private class NotifyingAlarmListener implements AlarmListener {
        private String id;

        public NotifyingAlarmListener(String id) {
            this.id = id;
        }

        @Override
        public void alarm() {
            alarmCount.incrementAndGet();
            System.out.println("Alarme " + id + " disparou."); //Para depuração
            synchronized (monitor) {
                monitor.notifyAll(); //Notifica o teste
            }
        }
    }

    public void testMultipleAlarms() throws Exception {
        long now = System.currentTimeMillis();
        clock.schedule("Alarm1", now + 50, new NotifyingAlarmListener("1"));
        clock.schedule("Alarm2", now + 100, new NotifyingAlarmListener("2"));
        clock.schedule("Alarm3", now + 150, new NotifyingAlarmListener("3"));

        //Espera pelos 3 alarmes
        synchronized (monitor) {
            long startWait = System.currentTimeMillis();
            while (alarmCount.get() < 3 && (System.currentTimeMillis() - startWait < 1000)) {
                monitor.wait(100);
            }
        }
        assertEquals("Todos os 3 alarmes deveriam ter disparado", 3, alarmCount.get());
    }

    public void testCancelAlarm() throws Exception {
        long now = System.currentTimeMillis();
        clock.schedule("AlarmToCancel", now + 50, new NotifyingAlarmListener("Cancel"));
        clock.schedule("AlarmToKeep", now + 100, new NotifyingAlarmListener("Keep"));

        //Cancela o primeiro alarme antes que ele dispare
        clock.cancel("AlarmToCancel");

        //Espera pelo tempo necessário para o segundo alarme disparar
        synchronized (monitor) {
            long startWait = System.currentTimeMillis();
            while (alarmCount.get() < 1 && (System.currentTimeMillis() - startWait < 200)) {
                monitor.wait(100);
            }
        }
        assertEquals("Apenas um alarme (o que foi mantido) deveria ter disparado", 1, alarmCount.get());
    }
}
