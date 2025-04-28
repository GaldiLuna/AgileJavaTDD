package Tests.sis.studentinfo;
import junit.framework.TestCase;

public class StudentTest extends TestCase {
    public void testCreate() {
        final String firstStudentName = "Jane Doe";
        Student firstStudent = new Student(firstStudentName);
        //firstStudent.name = "June Crow";
        assertEquals(firstStudentName, firstStudent.getName());

        final String secondStudentName = "Joe Blow";
        Student secondStudent = new Student(secondStudentName);
        assertEquals(secondStudentName, secondStudent.getName());

        //assertEquals(firstStudentName, firstStudent.name);
    }
// TESTE FEITO PARA VERIFICAR A VARIÁVEL name COMO ESTÁTICA DENTRO DE STUDENT
//    public void testBadStatic() {
//        Student studentA = new Student("a");
//        assertEquals("a", studentA.getName());
//        Student studentB = new Student("b");
//        assertEquals("b", studentB.getName());
//        assertEquals("a", studentA.getName());
//    }

// TESTES UNIFICADOS LOGO ABAIXO NO testStudentStatus
//    public void testFullTime() {
//        Student boolStudent = new Student("Bool");
//        assertFalse(boolStudent.isFullTime());
//    }
//
//    public void testCredits() {
//        Student credStudent = new Student("Credt");
//        assertEquals(0, credStudent.getCredits());
//        credStudent.addCredits(3);
//        assertEquals(3, credStudent.getCredits);
//        credStudent.addCredits(4);
//        assertEquals(7, credStudent.getCredits);
//    }

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
}