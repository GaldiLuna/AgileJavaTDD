package Tests.sis.summer;
import Tests.sis.studentinfo.*;
import junit.framework.*;
import java.util.*;

public class SummerCourseSessionTest extends SessionTest {
    public void testEndDate() {
        Date startDate = DateUtil.createDate(2003, 6, 9);
        Session session = createSession("ENGL", "200", startDate);
        Date eightWeeksOut = DateUtil.createDate(2003, 8, 1);
        assertEquals(eightWeeksOut, session.getEndDate());
    }

    protected Session createSession(String department, String number, Date date) {
        return SummerCourseSession.create(department, number, date);
    }
}
