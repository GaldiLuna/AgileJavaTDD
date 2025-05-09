package Tests.sis.studentinfo;

public class HonorsGradingStrategy implements GradingStrategy {
    public int getGradePointsFor(Student.Grade grade) {
        int points = BasicGradingStrategy.getBasicGradePointsFor(grade);
        if (points > 0)
            points += 1;
        return points;
    }

}
