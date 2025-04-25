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
// TESTE FEITO PARA VERIFICAR A VARIÁVEL COMO ESTÁTICA DENTRO DE STUDENT
//    public void testBadStatic() {
//        Student studentA = new Student("a");
//        assertEquals("a", studentA.getName());
//        Student studentB = new Student("b");
//        assertEquals("b", studentB.getName());
//        assertEquals("a", studentA.getName());
//    }
}