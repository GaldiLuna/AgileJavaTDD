package Tests.sis.studentinfo;
import java.util.*;

public class Student { //public class Student implements Comparable<Student>
    private String name;
    private int credits;
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    static final String IN_STATE = "AL";
    private String state = "";
    private ArrayList<Grade> grades = new ArrayList<Grade>();
    enum Grade { A, B, C, D, F }
    private List<Student> students = new LinkedList<Student>();

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

//    private int basicGradePointsFor(Grade grade) {
//        if (grade == Grade.A) return  4;
//        if (grade == Grade.B) return  3;
//        if (grade == Grade.C) return  2;
//        if (grade == Grade.D) return  1;
//        return 0;
//    }

    private Student createHonorsStudent() {
        Student student = new Student("a");
        student.setGradingStrategy(new HonorsGradingStrategy());
        return student;
    }

    private GradingStrategy gradingStrategy = new RegularGradingStrategy();

    void setGradingStrategy(GradingStrategy gradingStrategy) {
        this.gradingStrategy = gradingStrategy;
    }

//    double gradePointsFor(Grade grade) {
//        if (isSenatorsSon) {
//            if (grade == Grade.A) return  4;
//            if (grade == Grade.B) return  4;
//            if (grade == Grade.C) return  4;
//            if (grade == Grade.D) return  4;
//            return 3;
//        } else {
//            double points = basicGradePointsFor(grade);
//            if (isHonors)
//                if (points > 0)
//                    points += 1;
//            return points;
//        }
//    }

}