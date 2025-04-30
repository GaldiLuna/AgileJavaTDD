package util;

public class StringUtil {
    public static final String NEWLINE = "\n";

    private StringUtil() {
        //Construtor privado para evitar instâncias.
    }

    public static String appendNewLine(String input) {
        return input + NEWLINE;
    }
}
