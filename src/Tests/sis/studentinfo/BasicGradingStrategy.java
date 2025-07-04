package Tests.sis.studentinfo;

import java.io.Serializable;

public class BasicGradingStrategy implements GradingStrategy {

    private static final long serialVersionUID = 1L; //AJUDA O JAVA A GERENCIAR VERSÕES

    public int getGradePointsFor(Student.Grade grade) {
        return grade.getPoints();
    }
}
