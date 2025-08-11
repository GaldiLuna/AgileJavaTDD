package util;

import junit.framework.TestCase;
import java.util.*;

public class CommandTest extends TestCase {
    private static final String TEST_SINGLE_LINE = "testOneLine";
    private static final String TEST_MULTIPLE_LINES = "testManyLines";
    private static final String TEST_LOTS_OF_LINES = "testLotsLines";
    private static Map<String, String> output =
            new HashMap<String, String>();
    private static final String COMMAND =
            "java " + // 1
                    "-classpath \"" + System.getProperty("java.class.path") + "\" " +
                    "sis.util.CommandTest %s";
    private Command command;

    static { // 2
        output.put(TEST_SINGLE_LINE, "a short line of text");
        output.put(TEST_MULTIPLE_LINES, "line 1\\nline 2\\");
        output.put(TEST_LOTS_OF_LINES, lotsOfLines());
    }

    static String lotsOfLines() {
        final int lots = 1024;
        StringBuilder lotsBuffer = new StringBuilder();
        for (int i = 0; i < lots; i++)
            lotsBuffer.append("" + i);
        return lotsBuffer.toString();
    }

    public static void main(String[] args) { // 3
        String methodName = args[0];
        String text = output.get(methodName);
        // saída redirecionada:
        System.out.println(text);
        System.err.println(text);
    }

    public void testSingleLine() throws Exception {
        verifyExecuteCommand(TEST_SINGLE_LINE);
    }

    public void testMultipleLines() throws Exception {
        verifyExecuteCommand(TEST_MULTIPLE_LINES);
    }

    public void testLotsOfLines() throws Exception {
        verifyExecuteCommand(TEST_LOTS_OF_LINES);
    }

    private void verifyExecuteCommand(String text) throws Exception {
        command = new Command(String.format(COMMAND, text));
        command.execute();
        assertEquals(output.get(text), command.getOutput());
    }
}
