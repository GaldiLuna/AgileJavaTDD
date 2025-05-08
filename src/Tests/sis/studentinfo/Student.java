package Tests.sis.studentinfo;

import java.util.ArrayList;

public class Student {
    private String name;
    private int credits;
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    static final String IN_STATE = "AL";
    private String state = "";
    private ArrayList<Grade> grades = new ArrayList<Grade>();
    enum Grade { A, B, C, D, F }

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

    int gradePointsFor(Grade grade) {
        if (grade == Grade.A) return  4;
        if (grade == Grade.B) return  3;
        if (grade == Grade.C) return  2;
        if (grade == Grade.D) return  1;
        return 0;
    }

    double getGpa() {
        if (grades.isEmpty())
            return 0.0;
        double total = 0.0;
        for (Grade grade : grades)
            total += gradePointsFor(grade);
        return total / grades.size();
    }

}