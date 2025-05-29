package Tests.sis.studentinfo;
import Tests.sis.report.TestHandler;
import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.StreamHandler;

public class StudentTest extends TestCase {
    private static final double GRADE_TOLERANCE = 0.05;

    // --- VARIÁVEIS PARA CAPTURAR O LOG ---
    private Logger logger;
    private Handler handler;
    private ByteArrayOutputStream logBuffer;
    // --- FIM DAS VARIÁVEIS DE LOG ---

    public void setUp() {
        // --- ADICIONADO PARA CAPTURA DO LOG ---
        //Obtenha o logger da classe Student. O nome deve ser o mesmo que Student.getClass().getName()
        logger = Logger.getLogger(Student.class.getName());
        //Garanta que o logger está no nível INFO ou mais baixo para capturar a mensagem INFO
        logger.setLevel(Level.INFO);

        //Cria um buffer para capturar a saída do log
        logBuffer = new ByteArrayOutputStream();
        //Cria um StramHandler que direciona a saída para o buffer, usando um SimpleFormatter
        handler = new StreamHandler(logBuffer, new java.util.logging.SimpleFormatter());
        //Adiciona o handler ao logger
        logger.addHandler(handler);
        // --- Fim da adição de Log ---
    }

    public void tearDown() {
        // --- ADICIONADO PARA REMOVER O HANDLER E LIMPAR ---
        logger.removeHandler(handler); //Importante para não vazar handlers entre testes
        handler.close(); //Feche o handler para liberar recursos
        // --- Fim da adição ---
    }

    public void testCreate() {
        final String firstStudentName = "Jane Doe";
        Student firstStudent = new Student(firstStudentName);
        assertEquals(firstStudentName, firstStudent.getName());
        assertEquals("Jane", firstStudent.getFirstName());
        assertEquals("Doe", firstStudent.getLastName());
        assertEquals("", firstStudent.getMiddleName());

        final String secondStudentName = "Blow";
        Student secondStudent = new Student(secondStudentName);
        assertEquals(secondStudentName, secondStudent.getName());
        assertEquals("", secondStudent.getFirstName());
        assertEquals("Blow", secondStudent.getLastName());
        assertEquals("", secondStudent.getMiddleName());

        final String thirdStudentName = "Raymond Douglas Davies";
        Student thirdStudent = new Student(thirdStudentName);
        assertEquals(thirdStudentName, thirdStudent.getName());
        assertEquals("Raymond", thirdStudent.getFirstName());
        assertEquals("Davies", thirdStudent.getLastName());
        assertEquals("Douglas", thirdStudent.getMiddleName());
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
        assertFalse(statStudent.isInState());
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
        student.setGradingStrategy(new HonorsGradingStrategy());
        return student;
    }

    public void testCharges() {
        Student student = new Student("a");
        student.addCharge(500);
        student.addCharge(200);
        student.addCharge(399);
        assertEquals(1099, student.totalCharges());
    }

    public void testBadlyFormattedName() {
        Handler handler = new TestHandler(); //Atribui a uma referência Handler
        Student.logger.addHandler(handler); //Usa a variável de classe logger de Student

        final String studentName = "a b c d";
        try {
            new Student(studentName);
            fail("expected exception from 4-part name");
        }
        catch (StudentNameFormatException expectedException) {
            String message = String.format(Student.TOO_MANY_NAME_PARTS_MSG, studentName, Student.MAX_NAME_PARTS);
            assertEquals(message, expectedException.getMessage());
            assertEquals(message, ((TestHandler)handler).getMessage()); //Verifica se foi logado e faz um cast para TestHandler
        }
    }
    private boolean wasLogged(String message, TestHandler handler) {
        return message.equals(handler.getMessage());
    }

}