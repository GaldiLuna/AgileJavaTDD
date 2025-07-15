package Tests.ExpTestes.Clock;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AlarmClock extends Thread {
    // A coleção DEVE ser thread-safe! Usaremos Collections.synchronizedMap para o exemplo.
    // Mas note: Collections.synchronizedMap apenas torna os métodos individuais sincronizados,
    // operações compostas (como iterar + remover) ainda podem falhar.
    private Map<String, Alarm> alarms = Collections.synchronizedMap(new HashMap<>());
    private long alarmTime;
    private AlarmListener listener;
    private volatile boolean running = true; //Flag para controlar a thread

    public AlarmClock() {
        start(); //Inicia a thread do clock construtor
    }

    public void schedule(String name, long time, AlarmListener listener) {
        //Ponto de falha potencial: 'alarms' é acessado por múltiplas threads
        alarms.put(name, new Alarm(name, time, listener));
    }

    public void cancel(String name) {
        //Ponto de falha potencial: 'alarms' é acessado por múltiplas threads
        alarms.remove(name);
    }

    public void schedule(long time, AlarmListener listener) {
        this.alarmTime = time;
        this.listener = listener;
        start(); //Inicia a thread quando o alarme é agendado
    }

    //Bloco da construção inicial antes de implementar Map<>, Collections() e HashMap<>() na classe.
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
//    public void stop() {
//        running = false;
//        this.interrupt(); //Interrompe a thread caso ela esteja dormindo
//    }

    @Override
    public void run() {
        while (running) {
            try {
                // Iterar sobre uma coleção sincronizada é seguro, mas remover durante a iteração não
                // sem um synchronized block no loop.
                // FORÇAR FALHA: Pausa intencional para expor problemas de concorrência
                Thread.sleep(1); // Simula trabalho pesado que aumenta a chance de conflito

                // *** ESTE CÓDIGO TEM UMA FALHA DE SINCRONIZAÇÃO INTENCIONAL ***
                // Iterar sobre alarms e remover itens pode causar ConcurrentModificationException
                // ou outros problemas se outra thread modificar o mapa simultaneamente.
                // Mesmo com Collections.synchronizedMap, a iteração exige sincronização externa.
                for (Alarm alarm : alarms.values()) { // Problema aqui sem synchronized(alarms)
                    if (System.currentTimeMillis() >= alarm.time) {
                        alarm.listener.alarm();
                        // Remover aqui pode gerar ConcurrentModificationException
                        // se outra thread também estiver modificando 'alarms'
                        alarms.remove(alarm.name); // PROBLEMA: Modifica a coleção enquanto itera sobre ela!
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            } catch (Exception e) {
                // Para depuração de outros problemas de concorrência
                System.err.println("Erro no AlarmClock run: " + e.getMessage());
            }
        }
    }

    public void stop() {
        running = false;
        this.interrupt();
    }
}
