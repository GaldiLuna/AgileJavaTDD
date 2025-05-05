package util;

/**
 * Atualize qualquer código que usava NEWLINE diretamente para referenciar StringUtil.NEWLINE.
 * Substitua concatenações manuais de \n por chamadas a StringUtil.appendNewLine.
 */

public class StringUtil {
    public static final String NEWLINE = System.lineSeparator();
    //public static final String NEWLINE = "\n";

    private StringUtil() {
        //Construtor privado para evitar instâncias.
        throw new AssertionError("Utility class - cannot be instantiated!");
    }

    public static String appendNewLine(String input) {
        if (input == null) {
            return NEWLINE;
        }
        return input + NEWLINE;
    }
}
