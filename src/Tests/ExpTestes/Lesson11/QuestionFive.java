package Tests.ExpTestes.Lesson11;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class QuestionFive extends TestCase {
    public void testCaptureStackTrace() {
        try {
            //Força uma exceção
            int x = 1 / 0;
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            e.printStackTrace(ps);
            ps.close(); //Importante para descarregar o buffer

            String stackTrace = baos.toString();
            System.out.println(stackTrace); //Apenas para visualização

            assertTrue(stackTrace.contains("java.lang.ArithmeticException: / by zero"));
            assertTrue(stackTrace.contains("at Question05.testCaptureStackTrace"));
        }
    }
}
