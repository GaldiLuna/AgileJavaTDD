package Tests.sis.report;
import Tests.sis.studentinfo.Student;

import java.util.EnumMap;
import java.util.Map;

public class ReportCard {
    static final String A_MESSAGE = "Excelente";
    static final String B_MESSAGE = "Muito bom";
    static final String C_MESSAGE = "Ok, Bom";
    static final String D_MESSAGE = "Você não está se esforçando";
    static final String F_MESSAGE = "Perdedor";

    private Map<Student.Grade, String> messages = null;

    public String getMessage(Student.Grade grade) {
        return getMessages().get(grade);
    }

    Map<Student.Grade, String> getMessages() {
        if (messages == null)
            loadMessages();
        return messages;
    }

    private void loadMessages() {
        messages = new EnumMap<Student.Grade, String>(Student.Grade.class);
        messages.put(Student.Grade.A, A_MESSAGE);
        messages.put(Student.Grade.B, B_MESSAGE);
        messages.put(Student.Grade.C, C_MESSAGE);
        messages.put(Student.Grade.D, D_MESSAGE);
        messages.put(Student.Grade.F, F_MESSAGE);
    }
}
