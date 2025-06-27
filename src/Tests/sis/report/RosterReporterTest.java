package Tests.sis.report;
import Tests.sis.report.RosterReporter;
import Tests.sis.studentinfo.*;
import junit.framework.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static Tests.sis.report.ReportConstant.NEWLINE;

public class RosterReporterTest extends TestCase {
    public void testRosterReport() throws IOException {
        Session session =
                CourseSession.create(new Course("ENGL", "101"),
                        DateUtil.createDate(2003, 1, 6));

        session.enroll(new Student("A"));
        session.enroll(new Student("B"));

        Writer writer = new StringWriter();
        new RosterReporter(session).writeReport(writer);

        String rosterReport = writer.toString();
        //String rosterReport = new RosterReporter(session).getReport();
        //System.out.println(rosterReport);

        assertEquals(
                String.format(RosterReporter.ROSTER_REPORT_HEADER +
                        "A%n" +
                        "B%n" +
                        RosterReporter.ROSTER_REPORT_FOOTER, 2),
                rosterReport);
    }

}