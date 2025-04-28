package Tests.sis.studentinfo;
import java.util.*;

/**
 * Representa uma sessão semestral de um curso universitário específico.
 *
 * @author GaldiLuna
 */
public class CourseSession {
    private String department;
    private String number;
    //private int numberOfStudents = 0;
    private ArrayList<Student> students = new ArrayList<Student>();
    private Date startDate;
    private static int count;
    //private static int CREDITS = 3;
    private int numberOfCredits;

    /**
     * Constrói uma CourseSession que começa em uma data específica.
     *
     * @param startDate a data em que a sessão do curso se inicia.
     */
    private CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
        //incrementCount();
        //CourseSession.incrementCount();
    }

    public static CourseSession create(String department, String number, Date startDate) {
        incrementCount();
        return new CourseSession(department, number, startDate);
    }

    private static void incrementCount() {
        count = count + 1; //pode ser "count += 1" ou também "++count" e "count++"
    }

    static void resetCount() {
        count = 0;
    }

    static int getCount() {
        return count;
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }

    public int getNumberOfStudents() {
        //return numberOfStudents;
        return students.size();
    }

    public void enroll(Student student) {
        //numberOfStudents = numberOfStudents +1;
        student.addCredits(numberOfCredits);
        students.add(student);
    }

    public Student get(int index) {
        return students.get(index);
    }

    public Date getStartDate() {
        return startDate;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        final int sessionLength = 16;
        final int daysInWeek = 7;
        final int daysFromFridayToMonday = 3;
        int numberOfDays = sessionLength * daysInWeek - daysFromFridayToMonday;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }
    void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

}