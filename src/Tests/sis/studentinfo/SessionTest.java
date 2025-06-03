package Tests.sis.studentinfo;
import junit.framework.TestCase;
import java.util.logging.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.util.*;
import static Tests.sis.studentinfo.DateUtil.createDate;

abstract public class SessionTest extends TestCase {
    private Session session;
    private Date startDate;
    public static final int CREDITS = 3;
    //VARIÁVEIS PARA CAPTURAR A SAÍDA DO CONSOLE:
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private Logger logger;
    private Handler handler;
    private ByteArrayOutputStream logBuffer;

    protected void setUp() {
        startDate = new Date();
        session = createSession(new Course("ENGL", "101"), startDate);
        session.setNumberOfCredits(CREDITS);

        // --- ADICIONADO PARA CAPTURA DO LOG ---
        logger = Logger.getLogger(Student.class.getName()); //Obtenha o logger do Student
        logger.setLevel(Level.INFO); //Garanta que o logger está nível INFO ou mais baixo para capturar a mensagem INFO

        logBuffer = new ByteArrayOutputStream();
        handler = new StreamHandler(logBuffer, new java.util.logging.SimpleFormatter()); //Direciona para o buffer
        logger.addHandler(handler); //Adiciona o handler ao logger
        // --- Fim da adição ---
    }

    public void tearDown() {
        // --- ADICIONADO PARA REMOVER O HANDLER---
        logger.removeHandler(handler); // Importante para não vazar handlers entre testes
        handler.close(); //Feche o handler para liberar recursos
        // --- Fim da adição ---
    }

    abstract protected Session createSession(Course course, Date startDate);

    public void testCreate() {
        assertEquals("ENGL", session.getDepartment());
        assertEquals("101", session.getNumber());
        assertEquals(0, session.getNumberOfStudents());
        assertEquals(startDate, session.getStartDate());
    }

    public void testEnrollStudents() {
        Student student1 = new Student("Cain DiVoe");
        session.enroll(student1);
        assertEquals(CREDITS, student1.getCredits());
        assertEquals(1, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));

        Student student2 = new Student("Coralee DeVaughn");
        session.enroll(student2);
        assertEquals(CREDITS, student2.getCredits());
        assertEquals(2, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));
        assertEquals(student2, session.get(1));
    }

    public void testComparable() {
        final Date date = new Date();
        Session sessionA = createSession(new Course("CMSC", "101"), date);
        Session sessionB = createSession(new Course("ENGL", "101"), date);
        assertTrue(sessionA.compareTo(sessionB) < 0);
        assertTrue(sessionB.compareTo(sessionA) > 0);
        Session sessionC = createSession(new Course("CMSC", "101"), date);
        assertEquals(0, sessionA.compareTo(sessionC));
        Session sessionD = createSession(new Course("CMSC", "220"), date);
        assertTrue(sessionC.compareTo(sessionD) < 0);
        assertTrue(sessionD.compareTo(sessionC) > 0);
    }

    public void testSessionLength( ){
        Date date = new Date();
        Session session = createSession(new Course("ANY", "999"), date);
        assertTrue(session.getSessionLength() > 0);
    }

    public void testAverageGpaForPartTimeStudents() {
        session.enroll(createFullTimeStudent());
        Student partTimer1 = new Student("1");
        partTimer1.addGrade(Student.Grade.A);
        session.enroll(partTimer1);

        session.enroll(createFullTimeStudent());
        Student partTimer2 = new Student("2");
        partTimer2.addGrade(Student.Grade.B);
        session.enroll(partTimer2);

        assertEquals(3.5, session.averageGpaForPartTimeStudents(), 0.05);
    }

    private Student createFullTimeStudent() {
        Student student = new Student("a");
        student.addCredits(Student.CREDITS_REQUIRED_FOR_FULL_TIME);
        return student;
    }

    public void testIterate( ){
        enrollStudents(session);
        List<Student> results = new ArrayList<Student>();
        for (Student student: session)
            results.add(student);
        assertEquals(session.getAllStudents(), results);
    }

    private void enrollStudents(Session session) {
        session.enroll(new Student("1"));
        session.enroll(new Student("2"));
        session.enroll(new Student("3"));
    }

    public void testSessionUrl() throws SessionException {
        final String url = "http://course.langrsoft.com/cmsc300";
        session.setUrl(url);
        assertEquals(url, session.getUrl().toString());
    }

    public void testInvalidSessionUrl() {
        final String url = "httsp://course.langrsoft.com/cmsc300"; //URL inválida com erro no "https"
        try {
            session.setUrl(url);
            fail("expected exception due to invalid protocol in URL");
        }
        catch (SessionException expectedException) {
            Throwable cause = expectedException.getCause(); //Obtem a causa raiz
            assertEquals(MalformedURLException.class, cause.getClass()); //Verifica o tipo da causa
        }
        catch (Throwable e) {
            System.err.println("DEBUG: EXCEÇÃO INESPERADA CAPTURADA EM testInvalidSessionUrl:");
            e.printStackTrace();
            fail("Erro inesperado: " + e.getMessage() + " - Tipo: " + e.getClass().getName());
        }
    }

}
