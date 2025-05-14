package Tests.sis.summer;
import Tests.sis.studentinfo.CourseSession;
import java.util.*;

public class SummerCourseSession extends CourseSession {
    //Metodo estático create para criar uma instância de SummerCourseSession
    public static SummerCourseSession create(String department, String number, Date startDate) {
        return new SummerCourseSession(department, number, startDate);
    }

    //Construtor protegido que chama o construtor da superclasse
    private SummerCourseSession(String department, String number, Date startDate) {
        super(department, number, startDate);
    }

    //Sobrescreve o metodo de CourseSession de 16 para 8
    @Override
    protected int getSessionLength() {
        return 8; //8 semanas para seções de verão
    }

}
