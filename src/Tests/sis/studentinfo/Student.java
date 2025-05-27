package Tests.sis.studentinfo;
import java.util.*;

public class Student { //public class Student implements Comparable<Student>
    private String firstName = "";
    private String middleName = "";
    private String lastName;
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
    
    public String getFirstName() { return firstName; }

    public String getMiddleName() { return middleName; }
    
    public String getLastName() { return lastName; }
    
    public Student(String fullName) {
        this.name = fullName;
        credits = 0;
        List<String> nameParts = split(fullName);
        final int maximumNumberOfNameParts = 3;
        if (nameParts.size() > maximumNumberOfNameParts) {
            String message = "Student name '" + fullName + "' contains more than " + maximumNumberOfNameParts + " parts";
            throw new StudentNameFormatException(message); //Lançamento da exceção customizada
        }
        setName(nameParts);
    }
    
    private void setName(List<String> nameParts) {
        this.lastName = removeLast(nameParts);
        String name = removeLast(nameParts);
        if (nameParts.isEmpty())
            this.firstName = name;
        else {
            this.middleName = name;
            this.firstName = removeLast(nameParts);
        }
    }

    private String removeLast(List<String> list) {
        if (list.isEmpty())
            return "";
        return list.remove(list.size() - 1);
    }

    private List<String> tokenize(String string) {
        List<String> results = new ArrayList<String>();
        StringBuffer word = new StringBuffer();
        int index = 0;
        while (index < string.length()) {
            char ch = string.charAt(index);
            if (ch != ' ') //preferir o Character.isSpace
                word.append(ch);
            else if (word.length() > 0) {
                results.add(word.toString());
                word = new StringBuffer();
            }
            index++;
        }
        if (word.length() > 0)
            results.add(word.toString());
        return results;
    }

    private List<String> split(String name) {
        return new ArrayList<>(Arrays.asList(name.split(" ")));
    }

//    private List<String> split0(String name) {
//        List<String> results = new ArrayList<String>();
//        StringBuffer word = new StringBuffer();
//        for (int index = 0; index < name.length(); index++) {
//            char ch = name.charAt(index);
//            if (!Character.isWhitespace(ch))
//                word.append(ch);
//            else if (word.length() > 0) {
//                results.add(word.toString());
//                word = new StringBuffer();
//            }
//        }
//        if (word.length() > 0)
//            results.add(word.toString());
//        return results;
//    }
//
//    private List<String> split1(String fullName) {
//        List<String> results = new ArrayList<String>();
//        for (String name : fullName.split(" "))
//            results.add(name);
//        return results;
//    }

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

    private List<Integer> charges = new ArrayList<Integer>();

    public void addCharge(int charge) {
        charges.add(charge);
    }
    public int totalCharges() {
        int total = 0;
        for (int charge: charges)
            total += charge;
        return total;
    }

}