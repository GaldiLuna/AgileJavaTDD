//CÓDIGO FEITO EM VÁRIAS PARTES PARA PODER TER SUCESSO EM TODOS AS PARTES DOS TESTES PROPOSTOS NO EXERCÍCIO
package Tests.ExpTestes.Clock;

        //VOU MANTER OS IMPORTS DE TODAS AS PARTES JUNTOS
import jdk.jshell.ImportSnippet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

//        //CÓDIGO PARA A PARTE 1 e 2 DO EXERCÍCIO
//public class AlarmClock extends Thread {
//    private long alarmTime;
//    private AlarmListener listener;
//    private volatile boolean running = true; //Flag para controlar a thread
//
//    public void schedule(long time, AlarmListener listener) {
//        this.alarmTime = time;
//        this.listener = listener;
//        start(); //Inicia a thread quando o alarme é agendado
//    }
//
//    @Override
//    public void run() {
//        while (running) {
//            if (System.currentTimeMillis() >= alarmTime) {
//                if (listener != null) { //Verifica se há um listener
//                    listener.alarm();
//                }
//                running = false; //Desliga o clock após o alarme disparar (para um único alarme)
//            }
//            try {
//                Thread.sleep(1); //Pequena pausa para evitar uso excessivo da CPU
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt(); //Restaura o status de interrupção
//                running = false; //Sai do loop se for interrompido
//            }
//        }
//    }
//
//    public void shutdown() {
//        running = false;
//        this.interrupt(); //Interrompe a thread caso ela esteja dormindo
//    }
//}


//        //CÓDIGO PARA A PARTE 3 DO EXERCÍCIO
//public class AlarmClock extends Thread {
//    // A coleção DEVE ser thread-safe! Usaremos Collections.synchronizedMap.
//    // Mas note: Collections.synchronizedMap apenas torna os métodos individuais sincronizados,
//    // operações compostas (como iterar + remover) ainda podem falhar.
//    private Map<String, Alarm> alarms = Collections.synchronizedMap(new HashMap<>());
//    private volatile boolean running = true; //Flag para controlar a thread
//
//    public AlarmClock() {
//        start(); //Inicia a thread do clock construtor
//    }
//
//    public void schedule(String name, long time, AlarmListener listener) {
//        //Ponto de falha potencial: 'alarms' é acessado por múltiplas threads
//        alarms.put(name, new Alarm(name, time, listener));
//    }
//
//    public void cancel(String name) {
//        //Ponto de falha potencial: 'alarms' é acessado por múltiplas threads
//        alarms.remove(name);
//    }
//
//    @Override
//    public void run() {
//        while (running) {
//            try {
//                // Iterar sobre uma coleção sincronizada é seguro, mas remover durante a iteração não
//                // sem um synchronized block no loop.
//                // FORÇAR FALHA: Pausa intencional para expor problemas de concorrência
//                Thread.sleep(1); // Simula trabalho pesado que aumenta a chance de conflito
//
//                // *** ESTE CÓDIGO TEM UMA FALHA DE SINCRONIZAÇÃO INTENCIONAL ***
//                // Iterar sobre alarms e remover itens pode causar ConcurrentModificationException
//                // ou outros problemas se outra thread modificar o mapa simultaneamente.
//                // Mesmo com Collections.synchronizedMap, a iteração exige sincronização externa.
//                for (Alarm alarm : alarms.values()) { // Problema aqui sem synchronized(alarms)
//                    if (System.currentTimeMillis() >= alarm.time) {
//                        alarm.listener.alarm();
//                        // Remover aqui pode gerar ConcurrentModificationException
//                        // se outra thread também estiver modificando 'alarms'
//                        alarms.remove(alarm.name); // PROBLEMA: Modifica a coleção enquanto itera sobre ela!
//                    }
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                running = false;
//            } catch (Exception e) {
//                // Para depuração de outros problemas de concorrência
//                System.err.println("Erro no AlarmClock run: " + e.getMessage());
//            }
//        }
//    }
//
//    public void shutdown() {
//        running = false;
//        this.interrupt();
//    }
//}


//        //CÓDIGO PARA A PARTE 4 DO EXERCÍCIO
//public class AlarmClock extends Thread {
//    // Usamos HashMap e sincronizamos acesso A ELE
//    private final Map<String, Alarm> alarms = new HashMap<>();
//    private volatile boolean running = true; //Flag para controlar a thread
//
//    public AlarmClock() {
//        start(); //Inicia a thread do clock construtor
//    }
//
//    //Sincroniza o metodo para garantir acesso seguro ao 'alarms'
//    public synchronized void schedule(String name, long time, AlarmListener listener) {
//        alarms.put(name, new Alarm(name, time, listener));
//    }
//
//    //Sincroniza o metodo para garantir acesso seguro ao 'alarms'
//    public synchronized void cancel(String name) {
//        alarms.remove(name);
//    }
//
//    @Override
//    public void run() {
//        while (running) {
//            try {
//                // Pausa para dar chance a outras threads de rodarem (e testar concorrência)
//                Thread.sleep(10); // Pausa estratégica para concorrência
//
//                //Precisamos de um bloco sincronizado para iterar e modificar o mapa
//                Set<String> alarmsToFireAndRemove = new HashSet<>();
//
//                //Primeira fase: identificar alarmes a serem disparados/removidos
//                synchronized (alarms) { //Sincroniza o acesso ao mapa DURANTE A ITERAÇÃO
//                    for (Alarm alarm : alarms.values()) {
//                        if (System.currentTimeMillis() >= alarm.time) {
//                            alarm.listener.alarm();
//                            alarmsToFireAndRemove.add(alarm.name);
//                        }
//                    }
//                    //Segunda fase: remover os alarmes identificados
//                    for (String alarmName : alarmsToFireAndRemove) {
//                        alarms.remove(alarmName);
//                    }
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                running = false;
//            } catch (Exception e) {
//                // Para depuração de outros problemas de concorrência
//                System.err.println("Erro no AlarmClock run: " + e.getMessage());
//            }
//        }
//    }
//
//    public void shutdown() {
//        running = false;
//        this.interrupt();
//    }
//}


//        //CÓDIGO PARA A PARTE 5 DO EXERCÍCIO
public class AlarmClock { //Não estende mais Thread
    // Usamos HashMap e sincronizamos acesso A ELE
    private final Map<String, Alarm> alarms = new HashMap<>();
    private Timer timer; //Usaremos um Timer para agendar a tarefa de verificação

    public AlarmClock() {
        //O timer será iniciado aqui, agendando uma TimerTask
        timer = new Timer(true); //O 'true' torna o timer uma thread daemon para que não impeça a JVM de desligar

        //TimerTask para verificar alarmes a cada 500ms
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //A lógica de verificação de alarmes vem para cá
                checkAlarms();
            }
        }, 0, 500); //Começa imediatamente e repete a cada 500ms
    }

    //Sincroniza o metodo para garantir acesso seguro ao 'alarms'
    public synchronized void schedule(String name, long time, AlarmListener listener) {
        alarms.put(name, new Alarm(name, time, listener));
    }

    //Sincroniza o metodo para garantir acesso seguro ao 'alarms'
    public synchronized void cancel(String name) {
        alarms.remove(name);
    }

    //Metodo que verifica e dispara os alarmes (chamado pelo TimerTask)
    private void checkAlarms() {
        Set<String> alarmsToFireAndRemove = new HashSet<>();

        synchronized (alarms) { //Sincroniza o acesso ao mapa DURANTE A ITERAÇÃO
            for (Alarm alarm : alarms.values()) {
                if (System.currentTimeMillis() >= alarm.time) {
                    alarm.listener.alarm(); //Dispara o alarme
                    alarmsToFireAndRemove.add(alarm.name); //Adiciona para remoção
                }
            }
            for (String alarmName : alarmsToFireAndRemove) {
                alarms.remove(alarmName); //Remove os disparados
            }
        }
    }

    public void shutdown() {
        if (timer != null) {
            timer.cancel(); //Cancela o timer e suas tarefas agendadas
        }
    }
}
