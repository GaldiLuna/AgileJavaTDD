package Tests.sis.report;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TestHandler extends Handler {
    private LogRecord record;

    public void flush() { }
    public void close() { }

    public void publish(LogRecord record) {
        this.record = record;
    }

    public String getMessage() {
        return record.getMessage();
    }
}
