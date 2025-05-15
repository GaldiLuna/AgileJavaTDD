package Tests.sis.summer;
import Tests.sis.studentinfo.CourseSession;
import Tests.sis.studentinfo.Session;
import java.util.*;

public class SummerCourseSession extends Session {
    public static SummerCourseSession create(String department, String number, Date startDate) {
        return new SummerCourseSession(department, number, startDate);
    }

    private SummerCourseSession(String department, String number, Date startDate) {
        super(department, number, startDate);
    }

    protected int getSessionLength() {
        return 8;
    }

}
