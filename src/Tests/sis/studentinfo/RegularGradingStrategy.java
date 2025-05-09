package Tests.sis.studentinfo;

public class RegularGradingStrategy implements GradingStrategy {
    public int getGradePointsFor(Student.Grade grade) {
        return BasicGradingStrategy.getBasicGradePointsFor(grade);
    }
}
