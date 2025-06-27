package Tests.sis.report;
import Tests.sis.report.RosterReporter;
import Tests.sis.studentinfo.*;
import junit.framework.*;

import java.io.*;

import static Tests.sis.report.ReportConstant.NEWLINE;

public class RosterReporterTest extends TestCase {
    private Session session;

    protected void setUp() {
        session = CourseSession.create(
                new Course("ENGL", "101"),
                DateUtil.createDate(2003, 1, 6));
        session.enroll(new Student("A"));
        session.enroll(new Student("B"));
    }

    public void testRosterReport() throws IOException {
//        Session session =
//                CourseSession.create(new Course("ENGL", "101"),
//                DateUtil.createDate(2003, 1, 6));

//        session.enroll(new Student("A"));
//        session.enroll(new Student("B"));

        Writer writer = new StringWriter();
        new RosterReporter(session).writeReport(writer);
        assertReportContents(writer.toString());

        //String rosterReport = writer.toString();
        //String rosterReport = new RosterReporter(session).getReport();
        //System.out.println(rosterReport);

    }

    private void assertReportContents(String rosterReport) {
        assertEquals(
                String.format(RosterReporter.ROSTER_REPORT_HEADER +
                        "A%n" +
                        "B%n" +
                        RosterReporter.ROSTER_REPORT_FOOTER,
                        session.getNumberOfStudents()),
                rosterReport);
    }

    public void testFiledReport() throws IOException {
        final String filename = "testFiledReport.txt";
        new RosterReporter(session).writeReport(filename);

        StringBuffer buffer = new StringBuffer();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while ((line = reader.readLine()) != null)
            buffer.append(String.format(line + "%n"));
        reader.close();

        assertReportContents(buffer.toString());
    }

}