package Tests.sis.report;
import java.util.*;
import Tests.sis.studentinfo.*;
import static Tests.sis.report.ReportConstant.NEWLINE;

public class CourseReport {
    private ArrayList<Session> sessions = new ArrayList<Session>();

    public void add(Session session) {
        sessions.add(session);
    }

    public String text() {
        Collections.sort(sessions);
        StringBuilder builder = new StringBuilder();
        for (Session session : sessions)
            builder.append(
                session.getDepartment() + " " +
                session.getNumber() + NEWLINE);
        return builder.toString();
    }
}