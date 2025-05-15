package Tests.sis.studentinfo;
import Tests.sis.studentinfo.CourseSession;
import junit.framework.TestCase;
import java.util.*;
import static Tests.sis.studentinfo.DateUtil.createDate;

public class CourseSessionTest extends SessionTest {
    private CourseSession session;
    private Date startDate;
    private static int CREDITS = 3;

    public void testCourseDates() {
        Date startDate = DateUtil.createDate(2003, 1, 6);
        Session session = createSession("ENGL", "200", startDate);
        Date sixteenWeeksOut = createDate(2003, 4, 25);
        assertEquals(sixteenWeeksOut, session.getEndDate());
    }

    public Date createDate(int year, int month, int date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, date);
        return calendar.getTime();
    }

    public void testCount() {
        CourseSession.resetCount();
        createSession("", "", new Date());
        assertEquals(1, CourseSession.getCount());
        createSession("", "", new Date());
        assertEquals(2, CourseSession.getCount());
    }

    protected Session createSession(String department, String number, Date date) {
        return CourseSession.create(department, number, date);
    }

}