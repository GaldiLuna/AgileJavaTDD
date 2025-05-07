package Tests.sis.report;
import Tests.sis.studentinfo.*;
import junit.framework.*;
import java.util.*;
import static Tests.sis.report.ReportConstant.NEWLINE;

public class CourseReportTest extends TestCase {
    public void testReport() {
        final Date date = new Date();
        CourseReport report = new CourseReport();
        report.add(CourseSession.create("ENGL", "101", date));
        report.add(CourseSession.create("CZEC", "200", date));
        report.add(CourseSession.create("ITAL", "410", date));
        assertEquals(
    //"CZEC 200" + NEWLINE +
            "ENGL 101" + NEWLINE +
            "CZEC 200" + NEWLINE +
            "ITAL 410" + NEWLINE,
            report.text());
    }

}
