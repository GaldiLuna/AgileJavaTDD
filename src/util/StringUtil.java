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

    static public int occurrences(String string, String substring) {
        int occurrences = 0;
        int length = substring.length();
        final boolean ignoreCase = true;
        for (int i = 0; i < string.length() - substring.length() +1; i++)
            if (string.regionMatches(ignoreCase, i, substring, 0, length))
                occurrences++;
        return occurrences;
    }
}
