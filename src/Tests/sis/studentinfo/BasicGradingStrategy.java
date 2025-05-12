package Tests.sis.studentinfo;

public class BasicGradingStrategy { //public class BasicGradingStrategy implements GradingStrategy
    public static int getBasicGradePointsFor(Student.Grade grade) {
        if (grade == Student.Grade.A) return 4;
        if (grade == Student.Grade.B) return 3;
        if (grade == Student.Grade.C) return 2;
        if (grade == Student.Grade.D) return 1;
        return 0;
    }
}
