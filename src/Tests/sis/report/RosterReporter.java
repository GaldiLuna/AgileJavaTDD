package Tests.sis.report;

import java.util.*;
import Tests.sis.studentinfo.*;
import static Tests.sis.report.ReportConstant.NEWLINE;
import java.io.*;

public class RosterReporter {
    static final String NEWLINE = System.getProperty("line.separator");
    static final String ROSTER_REPORT_HEADER = "Student%n-%n";
    static final String ROSTER_REPORT_FOOTER = "%n# students = %d%n";

    public Session session;
    private Writer writer;

    RosterReporter(Session session) {
        this.session = session;
    }

    void writeReport(Writer writer) throws IOException {
        this.writer = writer;
        writeHeader();
        writeBody();
        writeFooter();
    }

    public String getReport() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(ROSTER_REPORT_HEADER);

        for (Student student : session.getAllStudents()) {
            buffer.append(student.getName());
            buffer.append(NEWLINE);
        }

        buffer.append(
                ROSTER_REPORT_FOOTER + session.getAllStudents().size() + NEWLINE);

//        writeHeader(buffer);
//        writeBody(buffer);
//        writeFooter(buffer);
        return buffer.toString();

    }

    private void writeHeader() throws IOException {
        writer.write(String.format(ROSTER_REPORT_HEADER));
    }

    private void writeBody() throws IOException {
        for (Student student : session.getAllStudents()) {
            writer.write(String.format(student.getName() + "%n"));
        }

    }

    private void writeFooter () throws IOException {
        writer.write(String.format(ROSTER_REPORT_FOOTER, session.getAllStudents().size()));
    }
}
