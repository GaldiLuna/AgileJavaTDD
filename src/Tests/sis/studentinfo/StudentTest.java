package Tests.sis.studentinfo;
import junit.framework.TestCase;

public class StudentTest extends TestCase {
    private static final double GRADE_TOLERANCE = 0.05;

    public void testCreate() {
        final String firstStudentName = "Jane Doe";
        Student firstStudent = new Student(firstStudentName);
        assertEquals(firstStudentName, firstStudent.getName());

        final String secondStudentName = "Joe Blow";
        Student secondStudent = new Student(secondStudentName);
        assertEquals(secondStudentName, secondStudent.getName());
    }

    public void testStudentStatus() {
        Student testStudent = new Student("TestBolCred");
        assertEquals(0, testStudent.getCredits());
        assertFalse(testStudent.isFullTime());
        testStudent.addCredits(3);
        assertEquals(3, testStudent.getCredits());
        assertFalse(testStudent.isFullTime());
        testStudent.addCredits(4);
        assertEquals(7, testStudent.getCredits());
        assertFalse(testStudent.isFullTime());
        testStudent.addCredits(5);
        assertEquals(12, testStudent.getCredits());
        assertTrue(testStudent.isFullTime());
        assertTrue(
            "créditos insuficientes para status de tempo integral",
            testStudent.isFullTime());
    }

    public void testInState() {
        Student statStudent = new Student("Estado");
        assertFalse(statStudent.isInState());
        statStudent.setState(Student.IN_STATE);
        assertTrue(statStudent.isInState());
        statStudent.setState("PE");
        assertFalse(statStudent.isInState());;
    }

    private void assertGpa(Student student, double expectedGpa) {
        assertEquals(expectedGpa, student.getGpa(), GRADE_TOLERANCE);
    }

    public void testCalculateGpa() {
        Student student = new Student("a");
        assertGpa(student, 0.0);
        student.addGrade(Student.Grade.A);
        assertGpa(student, 4.0);
        student.addGrade(Student.Grade.B);
        assertGpa(student, 3.5);
        student.addGrade(Student.Grade.C);
        assertGpa(student, 3.0);
        student.addGrade(Student.Grade.D);
        assertGpa(student, 2.5);
        student.addGrade(Student.Grade.F);
        assertGpa(student, 2.0);
    }

    public void testCalculateHonorsStudentGpa() {
        assertGpa(createHonorsStudent(), 0.0);
        assertGpa(createHonorsStudent(Student.Grade.A), 5.0);
        assertGpa(createHonorsStudent(Student.Grade.B), 4.0);
        assertGpa(createHonorsStudent(Student.Grade.C), 3.0);
        assertGpa(createHonorsStudent(Student.Grade.D), 2.0);
        assertGpa(createHonorsStudent(Student.Grade.F), 0.0);
    }

    private Student createHonorsStudent(Student.Grade grade) {
        Student student = createHonorsStudent();
        student.addGrade(grade);
        return student;
    }

    private Student createHonorsStudent() {
        Student student = new Student("a");
        //student.setHonors();
        return student;

    }
}