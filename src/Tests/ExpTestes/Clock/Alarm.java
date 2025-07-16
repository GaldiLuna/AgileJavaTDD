//        //ESSA CLASSE PODERIA SER UMA CLASSE INTERNA PRIVADA NA CLASSE ALARMCLOCK
package Tests.ExpTestes.Clock;

        //CÓDIGO CRIADO NA PARTE 3 DO EXERCÍCIO
public class Alarm {
    public final String name;
    public final long time;
    public final AlarmListener listener;

    public Alarm(String name, long time, AlarmListener listener) {
        this.name = name;
        this.time = time;
        this.listener = listener;
    }
}
