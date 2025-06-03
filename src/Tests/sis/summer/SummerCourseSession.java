package Tests.sis.summer;
import Tests.sis.studentinfo.Course;
import Tests.sis.studentinfo.CourseSession;
import Tests.sis.studentinfo.Session;
import java.util.*;

public class SummerCourseSession extends Session {

//    public static SummerCourseSession create(String department, String number, Date startDate) {
//        return new SummerCourseSession(department, number, startDate);
//    }

    public static Session create(Course course, Date startDate) {
        return new SummerCourseSession(course, startDate);
    }

//    private SummerCourseSession(String department, String number, Date startDate) {
//        super(department, number, startDate);
//    }

    private SummerCourseSession(Course course, Date startDate) {
        super(course, startDate);
    }

    protected int getSessionLength() {
        return 8;
    }

}
