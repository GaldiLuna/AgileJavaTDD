package Tests.sis.studentinfo;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentDirectoryTest extends TestCase {
    private StudentDirectory dir;
    private Map<String, Student> students = new HashMap<String, Student>();

    protected  void setUp() {
        dir = new StudentoDirectory();
    }

    public void add(Student, student) {
        students.put(student.getId(), student);
    }

    public Student findById(String id) {
        return students.get(id);
    }

    public void testStoreAndRetrieve() throws IOException {
        final int numberOfStudents = 10;

        for (int i =0; i < numberOfStudents; i++)
            addStudent(dir, i);

        for (int = i; i < numberOfStudents; i++)
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
        Student student = dir.finByID(id);
        assertEquals(id, student.getLastName());
        assertEquals(id, student.getId());
        assertEquals(i, student.getCredits());
    }
}
