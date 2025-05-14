package Tests.sis.studentinfo;
import java.util.*;

public class Student { //public class Student implements Comparable<Student>
    private String name;
    private int credits;
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    static final String IN_STATE = "AL";
    private String state = "";
    private ArrayList<Grade> grades = new ArrayList<Grade>();
    private List<Student> students = new LinkedList<Student>();
    public enum Grade {
        A(4),
        B(3),
        C(2),
        D(1),
        F(0);

        private int points;

        Grade(int points) {
            this.points = points;
        }

        int getPoints() {
            return points;
        }
    }

    public Student(String name) {
        this.name = name;
        credits = 0;
    }

    public String getName() {
        return name;
    }

    boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    int getCredits() {
        return credits;
    }

    void addCredits(int credits) {
        this.credits += credits;
    }

    boolean isInState() {
        return state.equals(Student.IN_STATE);
    }

    void setState(String state) {
        this.state = state;
    }

    void addGrade(Grade grade) {
        grades.add(grade);
    }

    double getGpa() {
        if (grades.isEmpty())
            return 0.0;
        double total = 0.0;
        for (Grade grade : grades)
            total += gradingStrategy.getGradePointsFor(grade);
        return total / grades.size();
    }

    int gradePointsFor(Grade grade) {
        return gradingStrategy.getGradePointsFor(grade);
    }

    private Student createHonorsStudent() {
        Student student = new Student("a");
        student.setGradingStrategy(new HonorsGradingStrategy());
        return student;
    }

    //private GradingStrategy gradingStrategy = new RegularGradingStrategy();
    private GradingStrategy gradingStrategy = new BasicGradingStrategy();

    void setGradingStrategy(GradingStrategy gradingStrategy) {
        this.gradingStrategy = gradingStrategy;
    }

}