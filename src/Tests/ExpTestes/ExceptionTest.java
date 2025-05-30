package Tests.ExpTestes;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ExceptionTest extends TestCase {
    private MyClass myClass; //Uma classe auxiliar para os métodos
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;
    private Logger logger;
    private MyLogHandler myLogHandler; //Para capturar logs

    public void setUp() {
        myClass = new MyClass();
        // Redirecionar System.err para capturar a saída de logs (se o log estiver indo para stderr)
        // No entanto, para java.util.logging, é melhor usar um Handler.

        logger = Logger.getLogger(MyClass.class.getName());
        logger.setUseParentHandlers(false); //Evitar que o log vá para o console padrão
        myLogHandler = new MyLogHandler();
        logger.addHandler(myLogHandler);
    }

    public void restoreStreams() {
        System.setErr(originalErr); //Restaurar System.err
        logger.removeHandler(myLogHandler);
    }

    public void testReverseStackTraceLogging() {
        Exception e = new RuntimeException("Test Exception");
        myClass.logExceptionInReverse(logger, e);

        String loggedMessage = myLogHandler.getLoggedOutput(); //Obtém a saída de captura

        assertTrue(loggedMessage.contains("Test Exception"));
        // Agora, verificar a ordem. Isso é um pouco mais complexo sem um parser de stack trace.
        // Vamos verificar se a linha que contém o nome do método que lançou a exceção (ex: blowsUp)
        // aparece antes da linha que chama o método de log (logExceptionInReverse).
        // Na ordem inversa, esperamos que a causa raiz (blowsUp) apareça por último no log.

        // Simulação de verificação da ordem:
        // Exemplo de stack trace invertido:
        // ... at mypackage.MyClass.logExceptionInReverse(MyClass.java:XX)
        // ... at mypackage.MyClass.rethrows(MyClass.java:YY)
        // ... at mypackage.MyClass.blowsUp(MyClass.java:ZZ)
        // Root Cause: Test Exception (ou whatever was at the top of the original stack)

        // Para simplificar a verificação do teste, vamos procurar por marcadores de "reverse"
        // que o formatter customizado possa adicionar, ou, mais realisticamente,
        // dividir o log por linhas e verificar a ordem de partes importantes do stack trace.

        String[] lines = loggedMessage.split("\\n");
        // Encontra o index da mensagem da exceção e o index da primeira linha do stack trace
        // e o index da última linha (causa raiz)
        int exceptionMessageIndex = -1;
        int lastStackTraceElementIndex = -1; //O topo do stack trace original (bottom in reverse)
        int firstStackTraceElementIndex = -1; //O final do stack trace original (top in reverse)

        for (int i=0; i < lines.length; i++) {
            if (lines[i].contains("Test Exception")) {
                exceptionMessageIndex = i;
            }
            // Supondo que a linha que lançou a exceção em blowsUp() seja a "raiz" que queremos no final.
            // Para este teste, vamos assumir que o ponto de falha é o "blowsUp".
            if (lines[i].contains("at MyClass.blowsUp(")) { // Ajustar conforme a implementação real
                lastStackTraceElementIndex = i; // Essa deveria ser a última linha no log invertido
            }
            if (lines[i].contains("at MyClass.logExceptionInReverse(")) {
                firstStackTraceElementIndex = i; // Essa deveria ser a primeira linha relevante no log invertido
            }
        }
        // No log invertido, esperamos que o elemento mais próximo do ponto de chamada (logExceptionInReverse)
        // venha primeiro, e o elemento que causou a exceção (blowsUp) venha por último.
        assertTrue("Log entry for logExceptionInReverse should appear before blowsUp in reverse order",
                firstStackTraceElementIndex != -1 && lastStackTraceElementIndex != -1 &&
                        firstStackTraceElementIndex < lastStackTraceElementIndex);
    }

    public void testBlowsUp() {
        myClass.blowsUp(); //Isso deve lançar uma exceção e falhar no teste
    }

    @Test(expected = RuntimeException.class)
    public void testBlowsUpExpectsException() {
        myClass.blowsUp();
    }

    public void testBlowsUpWithCorrectMessage() {
        try {
            myClass.blowsUp();
            fail("Expected RuntimeException was not thrown"); //Garante que uma exceção foi lançada
        } catch (RuntimeException e) {
            assertEquals("Somebody should catch this!", e.getMessage());
        }
    }

    public void testRethrowWithCause() {
        try {
            myClass.rethrows();
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            assertEquals("Wrapped Exception!", e.getMessage());
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof RuntimeException);
            assertEquals("Somebody should catch this!", e.getCause().getMessage());
        }
    }

    public void testBlowsUpExpectsSimpleException() {
        try {
            myClass.blowsUp();
            fail("Expected SimpleException was not thrown");
        } catch (SimpleException e) { //Agora espera SimpleException
            assertEquals("Somebody should catch this!", e.getMessage());
        } catch (RuntimeException e) {
            fail("Caught RuntimeException instead of SimpleException"); //Para garantir que pegamos a exceção específica
        }
    }

    public void testExceptionOrder1() {
        try {
            myClass.blowsUp(); // Lança SimpleException
            myClass.rethrows(); // Não será chamada
            fail("no exception");
        }
        catch (SimpleException yours) { // Captura SimpleException
            fail("caught wrong exception"); // FALHA! Isso vai falhar o teste porque pegou a exceção correta, mas a asserção 'fail' é chamada.
        }
        catch (RuntimeException success) {
        }
    }

    public void testExceptionOrder2() {
        try {
            myClass.rethrows(); // Lança RuntimeException (com SimpleException como causa)
            myClass.blowsUp(); // Não será chamada
            fail("no exception");
        }
        catch (SimpleException success) { // Não captura RuntimeException
        }catch (RuntimeException failure) { // Captura RuntimeException
            fail("caught wrong exception"); // FALHA! Essa asserção falhará o teste.
        }
    }

//    public void testExceptionOrder3() {
//        try {
//            myClass.blowsUp(); // Lança SimpleException
//            myClass.rethrows(); // Não será chamada
//            fail("no exception");
//        }
//        catch (RuntimeException success) { // Captura SimpleException (polimorfismo)
//        }
//        catch (SimpleException yours) { // Código inalcançável! SimpleException é uma subclasse de RuntimeException.
//            // O catch de RuntimeException já teria capturado SimpleException.
//            fail("caught wrong exception");
//        }
//    }

//    public void testExceptionOrder4() {
//        try {
//            myClass.blowsUp(); // Lança SimpleException
//            myClass.rethrows(); // Não será chamada
//            fail("no exception");
//        }
//        catch (RuntimeException fail) { // Captura SimpleException
//            fail("exception unacceptable"); // FALHA! Teste falha aqui.
//        }
//        catch (SimpleException yours) { // Código inalcançável
//            fail("caught wrong exception");
//        }
//        finally {
//            return; // Isso pode mascarar falhas, geralmente não é recomendado em testes.
//        }
//    }

    public void testExceptionOrder5() {
        try {
            myClass.blowsUp(); // Lança SimpleException
            myClass.rethrows(); // Não será chamada
            fail("no exception");
        }
        catch (SimpleException yours) { // Captura SimpleException
            fail("caught wrong exception"); // FALHA!
        }
        catch (RuntimeException success) {
        }
    }

    public void testExceptionOrder6() {
        try {
            myClass.rethrows(); // Lança RuntimeException
            myClass.blowsUp(); // Não será chamada
            fail("no exception");
        }
        catch (SimpleException yours) { // Não captura RuntimeException
            fail("caught wrong exception");
        }
        catch (RuntimeException success) { // Captura RuntimeException
            // PASSA!
        }
    }

    public void testExceptionOrder7() {
        try {
            myClass.rethrows(); // Lança RuntimeException
            myClass.blowsUp(); // Não será chamada
            fail("no exception");
        }
        catch (SimpleException success) { // Não captura RuntimeException
        }
        catch (RuntimeException fail) { // Captura RuntimeException
            fail("caught wrong exception"); // FALHA!
        }
    }

    public void testErrorException1() {
        try {
            throw new RuntimeException("fail"); // Lança RuntimeException
        }
        catch (Exception success) { // Captura RuntimeException (polimorfismo)
            // PASSA!
        }
    }

    public void testErrorException2() {
        try {
            new Dyer(); // Lança RuntimeException
        }
        catch (Exception success) { // Captura RuntimeException
            // PASSA!
        }
    }

    public void testErrorException3() {
        try {new Dyer(); // Lança RuntimeException
        }
        catch (Error success) { // NÃO captura RuntimeException (Error e Exception são irmãos)
        }
    }

    public void testErrorException4() {
        try {
            new Dyer(); // Lança RuntimeException
        }
        catch (Throwable success) { // Captura RuntimeException (Throwable é a superclasse de Error e Exception)
            // PASSA!
        }
    }

//    public void testErrorException5() {
//        try {
//            new Dyer(); // Lança RuntimeException
//        }
//        catch (Throwable fail) { // Captura RuntimeException
//            fail("caught exception in wrong place"); // FALHA!
//        }
//        catch (Error success) { // Código inalcançável! Error é um Throwable.
//        }
//    }

    public void testErrorException6() {
        try {
            new Dyer(); // Lança RuntimeException
        }
        catch (Error fail) { // NÃO captura RuntimeException
            fail("caught exception in wrong place");
        }
        catch (Throwable success) { // Captura RuntimeException
            // PASSA!
        }
    }

    public void testErrorException7() {
        try {
            new Dyer(); // Lança RuntimeException
        }
        catch (Error fail) { // NÃO captura RuntimeException
            fail("caught exception in wrong place");
        }
        catch (Throwable success) { // Captura RuntimeException
        }finally {
            return; // Pode mascarar falhas.
        }
    }

//    public void testWithProblems() {
//        try {
//            doSomething();
//            fail("no exception");
//        }
//        catch (Exception success) {}
//    }
//    void doSomething() {
//        throw new Exception("blah");
//    }

    public void testWithProblemsPasses() {
        try {
            myClass.doSometing();
            fail("no exception");
        } catch (Exception sucess) {
            assertEquals("blah", sucess.getMessage());
        }
    }

}

// --- Classes auxiliares para os métodos ---
class MyClass {
    void blowsUp() {
        throw new SimpleException("Somebody should catch this!"); //Agora lança SimpleException ao invés de RuntimeException
    }

    void rethrows() {
        try {
            blowsUp(); //Lança SimpleException
        } catch (RuntimeException e) { //Captura SimpleException com o polimorfismo
            throw new RuntimeException("Wrapped Exception!", e); //Lança RuntimeException com SimpleException como causa
        }
    }

    void doSometing() throws Exception {
        throw new Exception("blah");
    }

}

class SimpleException extends RuntimeException {
    public SimpleException(String message) {
        super(message);
    }
}

class Dyer {
    Dyer() {
        throw new RuntimeException("oops.");
    }
}

class MyLogHandler extends Handler {
    private StringBuilder output = new StringBuilder();

    public void publish(LogRecord record) {
        output.append(getFormatter().format(record));
    }

    public void flush() {
        //Nada a fazer
    }

    public void close() throws SecurityException {
        //Nada a fazer
    }

    public String getLoggedOutput() {
        return output.toString();
    }

    public void clear() {
        output.setLength(0); //Limpa o StringBuilder
    }
}
