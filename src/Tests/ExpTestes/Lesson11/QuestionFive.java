package Tests.ExpTestes.Lesson11;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class QuestionFive extends TestCase {
    public void testCaptureStackTrace() {
        String capturedStackTrace = "";

        try {
            //Forçar uma exceção
            int i = 1 / 0;
            fail("Deveria ter lançado ArithmeticException"); // Garante que o teste falhe se a exceção não ocorrer
        } catch (ArithmeticException e) {

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 PrintStream ps = new PrintStream(baos)) {

                e.printStackTrace(ps);

                capturedStackTrace = baos.toString();
            } catch (java.io.IOException ioException) {
                fail("Erro de I/O ao capturar stack trace: " + ioException.getMessage());
            }
            //ps.close(); //Importante para descarregar o buffer

        }

        //Apenas para visualização
        System.out.println("--- Stack Trace Capturado ---");
        System.out.println(capturedStackTrace);
        System.out.println("------------------------------");

        //Verificar o conteúdo da string capturada
        assertNotNull("A string do stack trace não deveria ser nula", capturedStackTrace);
        assertFalse("A string do stack trace não deveria estar vazia", capturedStackTrace.isEmpty());

        //Asserções chave
        assertTrue("O stack trace deve conter a classe da exceção",
                capturedStackTrace.contains("java.lang.ArithmeticException: / by zero"));
        assertTrue("O stack trace deve conter o nome do método teste",
                capturedStackTrace.contains("at Tests.ExpTestes.Lesson11.QuestionFive.testCaptureStackTrace"));
    }
}
