package Tests.sis.report;
import Tests.sis.studentinfo.*;
import junit.framework.*;
import java.util.*;
import static Tests.sis.report.ReportConstant.NEWLINE;

public class CourseReportTest extends TestCase {
    public void testReport() {
        final Date date = new Date();
        CourseReport report = new CourseReport();
//        report.add(CourseSession.create("ENGL", "101", date));
//        report.add(CourseSession.create("CZEC", "200", date));
//        report.add(CourseSession.create("ITAL", "410", date));
//        report.add(CourseSession.create("CZEC", "220", date));
//        report.add(CourseSession.create("ITAL", "330", date));
        report.add(create("ENGL", "101", date));
        report.add(create("CZEC", "200", date));
        report.add(create("ITAL", "410", date));
        report.add(create("CZEC", "220", date));
        report.add(create("ITAL", "330", date));


        assertEquals( //CORRIGIR A ORDEM ABAIXO DE ACORDO COM O LANÇAMENTO ACIMA (CASO O LIVRO NÃO MUDE ALGO)
            String.format(
            "CZEC 200%n" +
            "CZEC 220%n" +
            "ENGL 101%n" +
            "ITAL 330%n" +
            "ITAL 410%n"),
            report.text());
    }

    private Session create(String name, String number, Date date) {
        return CourseSession.create(new Course(name, number), date);
    }

}
