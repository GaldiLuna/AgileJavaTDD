package Tests.ExpTestes;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ExceptionTest extends TestCase {
    private MyClass myClass; //Uma classe auxiliar para os métodos
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;
    private Logger logger;
    private MyLogHandler myLogHandler; //Para capturar logs

    @Override
    public void setUp() {
        myClass = new MyClass();
        // Redirecionar System.err para capturar a saída de logs (se o log estiver indo para stderr)
        // No entanto, para java.util.logging, é melhor usar um Handler.

        logger = Logger.getLogger(MyClass.class.getName());
        logger.setUseParentHandlers(false); //Evitar que o log vá para o console padrão
        myLogHandler = new MyLogHandler();
        // O CustomLogFormatter agora pode ser instanciado sem passar o handler para casos onde a contagem não é necessária.
        // Para este teste de log invertido, a contagem não é relevante, apenas o formato.
        myLogHandler.setFormatter(new CustomLogFormatter());
        logger.addHandler(myLogHandler);
        // Limpar o handler antes de cada teste para garantir isolamento
        myLogHandler.clear();
    }

    @Override
    public void tearDown() { //Para limpar recursos após cada teste
        System.setErr(originalErr); //Restaurar System.err
        //Remova o handler que foi adicionado em setUp
        logger.removeHandler(myLogHandler);
    }

    //Comentado por não estar mais sendo necessário, trabalho de limpeza feito aqui agora está em tearDown()
//    public void restoreStreams() {
//        System.setErr(originalErr); //Restaurar System.err
//        logger.removeHandler(myLogHandler);
//    }

    public void testReverseStackTraceLogging() {
        // Para testar a ordem inversa, precisamos de uma exceção que tenha um stack trace real.
        // Vamos simular uma chamada que geraria um stack trace mais profundo.
        // MyClass.rethrows() já gera uma exceção com causa, o que é bom para testar stack traces.
        try {
            myClass.rethrows(); // Lança RuntimeException que envolve SimpleException
            fail("Expected RuntimeException was not thrown by rethrows()");
        } catch (RuntimeException e) {
            myClass.logExceptionInReverse(logger, e);

            String loggedMessage = myLogHandler.getLoggedOutput();

            //Verificações básicas de conteúdo
            assertTrue(loggedMessage.contains("Wrapped Exception!"));
            assertTrue(loggedMessage.contains("Somebody should catch this!")); //Mensagem da causa raiz

            // Verificar a ordem dos elementos no stack trace invertido
            // No stack trace invertido, o elemento que causou a exceção original (blowsUp) deve aparecer por último.
            // O elemento que chamou o metodo de log (rethrows, que é onde a exceção é pega e relançada)
            // deve aparecer mais cedo no log invertido.

            // Para simplificar a verificação, podemos procurar por substrings que representam as chamadas de método.
            // A ordem esperada no log *invertido* é:
            // ... at Tests.ExpTestes.MyClass.rethrows(ExceptionTest.java:XXX)
            // ... at Tests.ExpTestes.MyClass.blowsUp(ExceptionTest.java:YYY)

            // A linha "at MyClass.rethrows" deve vir antes de "at MyClass.blowsUp" no log invertido.
            // Porque 'rethrows' é o metodo que capturou e relançou a exceção, então sua entrada estará mais "próxima"
            // do log. 'blowsUp' é a causa original, então estará no final do stack invertido.

            String[] lines = loggedMessage.split("\\n");
            int rethrowsIndex = -1;
            int blowsUpIndex = -1;

            for (int i = 0; i < lines.length; i++) {
                if (lines[i].contains("at Tests.ExpTestes.MyClass.rethrows(")) {
                    rethrowsIndex = i;
                }
                if (lines[i].contains("at Tests.ExpTestes.MyClass.blowsUp(")) {
                    blowsUpIndex = i;
                }
            }

            assertTrue("Expected 'rethrows' and     blowsUp' entries in log", rethrowsIndex != -1 && blowsUpIndex != -1);
            assertTrue("Log entry for 'rethrows' should appear before 'blowsUp' in reverse order", rethrowsIndex < blowsUpIndex);

            System.out.println("DEBUG - Log Invertido:\n" + loggedMessage);
            System.out.println("DEBUG - Index rethrows: " + rethrowsIndex);
            System.out.println("DEBUG - Index blows: " + blowsUpIndex);
        }

//        Exception e = new RuntimeException("Test Exception");
//        myClass.logExceptionInReverse(logger, e);
//
//        String loggedMessage = myLogHandler.getLoggedOutput(); //Obtém a saída de captura
//
//        assertTrue(loggedMessage.contains("Test Exception"));
        // Agora, verificar a ordem. Isso é um pouco mais complexo sem um parser de stack trace.
        // Vamos verificar se a linha que contém o nome do metodo que lançou a exceção (ex: blowsUp)
        // aparece antes da linha que chama o metodo de log (logExceptionInReverse).
        // Na ordem inversa, esperamos que a causa raiz (blowsUp) apareça por último no log.
//
        // Simulação de verificação da ordem:
        // Exemplo de stack trace invertido:
        // ... at mypackage.MyClass.logExceptionInReverse(MyClass.java:XX)
        // ... at mypackage.MyClass.rethrows(MyClass.java:YY)
        // ... at mypackage.MyClass.blowsUp(MyClass.java:ZZ)
        // Root Cause: Test Exception (ou whatever was at the top of the original stack)
//
        // Para simplificar a verificação do teste, vamos procurar por marcadores de "reverse"
        // que o formatter customizado possa adicionar, ou, mais realisticamente,
        // dividir o log por linhas e verificar a ordem de partes importantes do stack trace.
//
//        String[] lines = loggedMessage.split("\\n");
        // Encontra o index da mensagem da exceção e o index da primeira linha do stack trace
        // e o index da última linha (causa raiz)
//        int exceptionMessageIndex = -1;
//        int lastStackTraceElementIndex = -1; //O topo do stack trace original (bottom in reverse)
//        int firstStackTraceElementIndex = -1; //O final do stack trace original (top in reverse)
//
//        for (int i=0; i < lines.length; i++) {
//            if (lines[i].contains("Test Exception")) {
//                exceptionMessageIndex = i;
//            }
            // Supondo que a linha que lançou a exceção em blowsUp() seja a "raiz" que queremos no final.
            // Para este teste, vamos assumir que o ponto de falha é o "blowsUp".
//            if (lines[i].contains("at MyClass.blowsUp(")) { // Ajustar conforme a implementação real
//                lastStackTraceElementIndex = i; // Essa deveria ser a última linha no log invertido
//            }
//            if (lines[i].contains("at MyClass.logExceptionInReverse(")) {
//                firstStackTraceElementIndex = i; // Essa deveria ser a primeira linha relevante no log invertido
//            }
//        }
        // No log invertido, esperamos que o elemento mais próximo do ponto de chamada (logExceptionInReverse)
        // venha primeiro, e o elemento que causou a exceção (blowsUp) venha por último.
//        assertTrue("Log entry for logExceptionInReverse should appear before blowsUp in reverse order",
//                firstStackTraceElementIndex != -1 && lastStackTraceElementIndex != -1 &&
//                        firstStackTraceElementIndex < lastStackTraceElementIndex);
//
//        System.out.println("DEBUG - Log Invertido:\n" + loggedMessage);
//        System.out.println("DEBUG - Index logExceptionInverse: " + firstStackTraceElementIndex);
//        System.out.println("DEBUG - Index blowsUp: " + lastStackTraceElementIndex);
    }

    public void testCountingLogHandlerCountsSeverities() {
        CountingLogHandler handler = new CountingLogHandler();
        Logger testLogger = Logger.getLogger("testCounting");
        testLogger.setUseParentHandlers(false);
        testLogger.addHandler(handler);
        testLogger.setLevel(Level.ALL);

        testLogger.log(Level.INFO, "Info message 1");
        testLogger.log(Level.WARNING, "Warning message 1");
        testLogger.log(Level.INFO, "Info message 2");
        testLogger.log(Level.SEVERE, "Severe message 1");
        testLogger.log(Level.FINE, "Fine message 1");

        assertEquals(2, handler.getCount(Level.INFO));
        assertEquals(1, handler.getCount(Level.WARNING));
        assertEquals(1, handler.getCount(Level.SEVERE));
        assertEquals(1, handler.getCount(Level.FINE));
        assertEquals(0, handler.getCount(Level.CONFIG)); //Nível não logado

        testLogger.removeHandler(handler); //Limpar o handler após o teste
    }

    public void testCustomFormatterWithoutCountingHandler() {
        CustomLogFormatter formatter = new CustomLogFormatter();
        LogRecord record = new LogRecord(Level.WARNING, "watch out");
        //Para garantir um output consistente, podemos setar um tempo fixo para o record
        record.setMillis(1678886400000L); // 15 Mar 2023 00:00:00 GMT

        String formatted = formatter.format(record);
        //O formato esperado é "LEVEL: message"
        assertEquals("WARNING: watch out\n", formatted);
    }

    public void testCustomFormatterWithCountingHandler() {
        CountingLogHandler handler = new CountingLogHandler();
        //CustomLogFormatter formatter = new CustomLogFormatter(handler);
        Logger testLogger = Logger.getLogger("testCountingFormatted");
        testLogger.setUseParentHandlers(false);
        testLogger.addHandler(handler);
        testLogger.setLevel(Level.ALL); //Certifique-se que todos os níveis estão habilitados

        testLogger.log(Level.WARNING, "watch out"); //Primeira mensagem WARNING
        testLogger.log(Level.INFO, "info message");
        testLogger.log(Level.WARNING, "another warning"); //Segunda mensagem WARNING

        String output = handler.getFormattedOutput();

        //Atribua a classe anônima a uma variável com o tipo da sua nova interface
//        Handler tempHandlerForTest = new Handler() {
//            private StringBuilder capturedOutput = new StringBuilder();
//
//            @Override
//            public void publish(LogRecord record) {
//                capturedOutput.append(formatter.format(record));
//            }
//
//            @Override
//            public void flush() {
//
//            }
//
//            @Override
//            public void close() throws SecurityException {
//
//            }
//
//            public String getCapturedOutput() {
//                return capturedOutput.toString();
//            }
//        };
// --- EXLUIR BLOCO COMENTADO ABAIXO --- EXCLUIR BLOCO COMENTADO ABAIXO ---
//        CapturableOutput tempHandler = new Handler() implements CapturableOutput {
//            private StringBuilder capturedOutput = new StringBuilder();
//
//            @Override
//            public void publish(LogRecord record) {
//                capturedOutput.append(formatter.format(record));
//            }
//
//            @Override
//            public void flush() { }
//
//            @Override
//            public void close() throws SecurityException { }
//
//            public String getCapturedOutput() {
//                return capturedOutput.toString();
//            }
//        };

//        testLogger.addHandler(new Handler() {
//            private StringBuilder capturedOutput = new StringBuilder();
//
//            @Override
//            public void publish(LogRecord record) {
//                capturedOutput.append(formatter.format(record));
//            }
//
//            @Override
//            public void flush() { }
//
//            @Override
//            public void close() throws SecurityException { }
//
//            public String getCapturedOutput() {
//                return capturedOutput.toString();
//            }
//        });
//
//        final Handler anonymousHandler = new Handler() {
//            private StringBuilder capturedOutput = new StringBuilder();
//
//            @Override
//            public void publish(LogRecord record) {
//                capturedOutput.append(formatter.format(record));
//            }
//
//            @Override
//            public void flush() {
//
//            }
//
//            @Override
//            public void close() throws SecurityException {
//
//            }
//
//            //Metodo customizado
//            public String getCapturedOutput() {
//                return capturedOutput.toString();
//            }
//        };
//
//        //Adicione o Handler ao logger
//        testLogger.addHandler((Handler) tempHandler); //CUIDADO: aqui você pode precisar de um cast para Handler se a interface não extender Handler
//
//        testLogger.log(Level.WARNING, "watch out"); //Primeira mensagem WARNING
//        testLogger.log(Level.INFO, "info message");
//        testLogger.log(Level.WARNING, "another warning"); //Segunda mensagem WARNING
//
//        //Agora chame o metodo diretamente da variável tempHandler
//        String output = tempHandler.getCapturedOutput();
//
//        //Capturar a saída do handler temporário
//        Handler tempHandler = testLogger.getHandlers()[0];
//        String output = ((Handler) tempHandler).getCapturedOutput();

        //O que esperamos na primeira WARNING:
        assertTrue(output.contains("WARNING: watch out (WARNING total = 1)\n"));
        //O que esperamos na segunda WARNING:
        assertTrue(output.contains("WARNING: another warning (WARNING total = 2)\n"));
        assertTrue(output.contains("INFO: info message (INFO total = 1)\n"));

        //Limpar o logger
        testLogger.removeHandler(handler);
    }

    public void testBlowsUp() {
        //Esse teste agora espera que uma exceção seja lançada e
        //a captura, garantindo que não falhe sem uma verificação.
        try {
            myClass.blowsUp(); //Isso deve lançar uma exceção e falhar no teste
            fail("Expected SimpleException was not thrown");
        } catch (SimpleException e) {
            //Sucessp: a exceção esperada foi capturada.
            assertEquals("Somebody should catch this!", e.getMessage());
        }
    }

    @Test(expected = SimpleException.class)
    public void testBlowsUpExpectsException() {
        myClass.blowsUp();
    }

    public void testBlowsUpWithCorrectMessage() {
        try {
            myClass.blowsUp();
            fail("Expected RuntimeException was not thrown"); //Garante que uma exceção foi lançada
        } catch (SimpleException e) {
            assertEquals("Somebody should catch this!", e.getMessage());
        } catch (RuntimeException e) {
            fail("Caught RuntimeException instead of SimpleException"); // Para garantir que pegamos a exceção específica
        }
    }

    public void testRethrowWithCause() {
        try {
            myClass.rethrows();
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            assertEquals("Wrapped Exception!", e.getMessage());
            assertNotNull(e.getCause());
            assertTrue(e.getCause() instanceof SimpleException);
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

    public void testCountingLogHandlerUsesCustomFormatterAndStoresOutput() {
        CountingLogHandler handler = new CountingLogHandler();
        Logger testLogger = Logger.getLogger("testCountingDefaultFormatter");
        testLogger.setUseParentHandlers(false);
        testLogger.addHandler(handler);
        testLogger.setLevel(Level.ALL); // Certifique-se de que todos os níveis estão habilitados

        testLogger.log(Level.INFO, "Info message one");
        testLogger.log(Level.WARNING, "Warning message one");

        String output = handler.getFormattedOutput();

        //Verifica o formato e a contagem
        assertTrue(output.contains("INFO: Info message one (INFO total = 1)\n"));
        assertTrue(output.contains("WARNING: Warning message one (WARNING total = 1)\n"));

        //Verifica se a contagem interna do handler está correta
        assertEquals(1, handler.getCount(Level.INFO));
        assertEquals(1, handler.getCount(Level.WARNING));

        //Testar a limpeza do output
        handler.clearFormattedOutput();
        assertEquals("", handler.getFormattedOutput());

        testLogger.removeHandler(handler);
    }

    // --- TESTES DE ORDEM DE ERROS/EXCEÇÕES ---
    // A lógica é: se a exceção for capturada pelo bloco catch "correto", o teste PASSA.
    // Se for capturada por um bloco catch "errado" ou não for capturada, o teste FALHA.

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
            // O 'return' em finally pode mascarar falhas se uma exceção não for capturada.
            // Em testes, geralmente é melhor evitar 'return' no finally a menos que seja intencional para um caso de teste específico.
            // Para este exercício, vamos manter, mas em cenários reais, cuidado.
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

    // --- FIM DOS TESTES DE ORDEM DE ERROS/EXCEÇÕES ---

}

// --- Classes auxiliares para os métodos ---
class MyClass {
    void blowsUp() {
        throw new SimpleException("Somebody should catch this!"); //Agora lança SimpleException ao invés de RuntimeException
    }

    void rethrows() {
        try {
            blowsUp(); //Lança SimpleException
        } catch (SimpleException e) {
            throw new RuntimeException("Wrapped Exception!", e); //Lança RuntimeException com SimpleException como causa
        }
    }

    void doSometing() throws Exception {
        throw new Exception("blah");
    }

    void logExceptionInReverse(Logger logger, Exception e) {
        //Logar a mensagem de exceção
        logger.log(Level.SEVERE, "Exception: " + e.getMessage());

        //Obter os elementos do stack trace
        StackTraceElement[] stackTrace = e.getStackTrace();
        List<StackTraceElement> list = Arrays.asList(stackTrace);

        //Inverter a ordem
        Collections.reverse(list);

        //Logar os elementos do stack trace invertidos
        for (StackTraceElement element : list) {
            logger.log(Level.SEVERE, "\tat " + element.toString());
        }
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

    @Override
    public void publish(LogRecord record) {
        output.append(getFormatter().format(record));
    }

    @Override
    public void flush() {
        //Nada a fazer
    }

    @Override
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

class CountingLogHandler extends Handler {
    private Map<Level, Integer> counts = new HashMap<>();
    private StringBuilder formattedOutput = new StringBuilder(); //Adicionado para armazenar output formatado

    @Override
    public void publish(LogRecord record) {
        //Descarta a mensagem, mas incrementa a contagem
        Level level = record.getLevel();
        counts.put(level, counts.getOrDefault(level, 0) + 1);

        //Armazena a saída formatada
        formattedOutput.append(getFormatter().format(record));
    }

   @Override
    public void flush() {
        //Nada a fazer
    }

    @Override
    public void close() {
        counts.clear(); //Limpa as contagens ao fechar
        formattedOutput.setLength(0); // Limpar também o output formatado ao fechar
    }

    public int getCount(Level level) {
        return counts.getOrDefault(level, 0);
    }

    public Map<Level, Integer> getCounts() {
        return new HashMap<>(counts); //Retorna uma cópia para segurança
    }

    public CountingLogHandler() {
        //Define o formatador padrão para CustomLogFormatter, passando a si mesmo
        setFormatter(new CustomLogFormatter(this));
    }

    public String getFormattedOutput() {
        return formattedOutput.toString();
    }

    public void clearFormattedOutput() {
        formattedOutput.setLength(0);
    }

    public void getCapturedOutput() {
        //Preencher???
    }
}

class CustomLogFormatter extends Formatter {
    private CountingLogHandler countingLogHandler;

    public CustomLogFormatter() {
        this(null); // Construtor sem handler, para casos onde a contagem não é necessária
    }

    public CustomLogFormatter(CountingLogHandler handler) {
        this.countingLogHandler = handler;
    }

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getLevel().getName()).append(": ").append(record.getMessage());

        if (countingLogHandler != null) {
            // No momento da formatação, o handler já publicou o record, então a contagem já foi incrementada.
            // Se você quiser a contagem *antes* deste log específico, seria mais complexo.
            // Para a lógica atual, pegar o count aqui reflete o total *após* este log.
            int count = countingLogHandler.getCount(record.getLevel());
            sb.append(" (").append(record.getLevel().getName()).append(" total = ").append(count).append(")");
        }
        sb.append("\n");
        return sb.toString();
    }
}

//A interface foi comentada por não ser mais necessária (mantida apenas pelo histŕorico de uso)
//interface CapturableOutput {
//    String getCapturedOutput();
//}
