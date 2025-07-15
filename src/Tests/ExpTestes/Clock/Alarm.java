package Tests.ExpTestes.Clock;

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
