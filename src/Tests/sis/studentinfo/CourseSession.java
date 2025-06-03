package Tests.sis.studentinfo;
import Tests.sis.summer.SummerCourseSession;
import java.util.*;

/**
 * Representa uma sessão semestral de um curso universitário específico.
 *
 * @author GaldiLuna
 */
public class CourseSession extends Session {
    private static int count;

//    public static CourseSession create(String department, String number, Date startDate) {
//        return new CourseSession(department, number, startDate);
//    }

    public static Session create(Course course, Date startDate) {
        incrementCount();
        return new CourseSession(course, startDate);
    }

//    protected CourseSession(String department, String number, Date startDate) {
//        super(department, number, startDate);
//        CourseSession.incrementCount();
//    }

    protected CourseSession(Course course, Date startDate) {
        super(course, startDate);
        //CourseSession.incrementCount();
    }

    static private void incrementCount() {
        ++count;
    }

    static void resetCount() {
        count = 0;
    }

    static int getCount() {
        return count;
    }

    protected int getSessionLength() {
        return 16;
    }

}