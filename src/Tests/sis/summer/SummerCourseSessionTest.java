package Tests.sis.summer;
import Tests.sis.studentinfo.CourseSession;
import Tests.sis.studentinfo.DateUtil;
import junit.framework.TestCase;

import java.util.Date;

public class SummerCourseSessionTest extends TestCase {
    public void testEndDate() {
        Date startDate = DateUtil.createDate(2003, 6, 9);
        CourseSession session = SummerCourseSession.create("ENGL", "200", startDate);
        Date eightWeeksOut = DateUtil.createDate(2003, 8, 1);
        assertEquals(eightWeeksOut, session.getEndDate());
    }
}
