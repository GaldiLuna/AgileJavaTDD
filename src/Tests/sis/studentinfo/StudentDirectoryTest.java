package Tests.sis.studentinfo;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentDirectoryTest extends TestCase {
    private StudentDirectory dir;
    //private Map<String, Student> students = new HashMap<String, Student>();

    protected  void setUp() {
        dir = new StudentDirectory();
    }

//    public void add(Student student) {
//        students.put(student.getId(), student);
//    }
//
//    public Student findById(String id) {
//        return students.get(id);
//    }

    public void testStoreAndRetrieve() throws IOException {
        final int numberOfStudents = 10;

        for (int i = 0; i < numberOfStudents; i++)
            addStudent(dir, i);

        for (int i = 0; i < numberOfStudents; i++)
            verifyStudentLookUp(dir, i);
    }

    void addStudent(StudentDirectory directory, int i) throws IOException {
        String id = "" + i;
        Student student = new Student(id);
        student.setId(id);
        student.addCredits(i);
        directory.add(student);
    }

    void verifyStudentLookUp(StudentDirectory directory, int i) throws IOException {
        String id = "" + i;
        Student student = dir.findById(id);
        // Ajuste a asserção aqui. Se o Student for criado apenas com ID, o lastName será nulo ou vazio
        // a menos que você o defina especificamente.
        // Para este teste, o ID deve ser igual ao lastName se você quiser que essa asserção passe.
        // Se `id` e `lastName` são coisas diferentes, você precisa ajustar sua lógica de Student ou o teste.
        // Por exemplo, se `lastName` não é definido no construtor `Student(String id)`, a linha abaixo pode falhar.
        // Para o propósito deste teste, vamos assumir que o ID é o LastName.
        // Se o `id` é "0", "1", etc., e você espera que seja o `lastName`, isso funciona.
        assertEquals(id, student.getLastName());
        assertEquals(id, student.getId());
        assertEquals(i, student.getCredits());
    }
}
