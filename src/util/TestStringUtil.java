package util;

import junit.framework.TestCase;

public class TestStringUtil extends TestCase {
    public void testAppendNewLine() {
        assertEquals("test" + StringUtil.NEWLINE, StringUtil.appendNewLine("test"));
        assertEquals(StringUtil.NEWLINE, StringUtil.appendNewLine(null));
        assertEquals("." + StringUtil.NEWLINE, StringUtil.appendNewLine("."));
        assertEquals("abc" + StringUtil.NEWLINE, StringUtil.appendNewLine("abc"));
        assertEquals("123" + StringUtil.NEWLINE, StringUtil.appendNewLine("123"));
        assertEquals("\n", StringUtil.appendNewLine(""));

        /**
         * Atualize qualquer código que usava NEWLINE diretamente para referenciar StringUtil.NEWLINE.
         * Substitua concatenações manuais de \n por chamadas a StringUtil.appendNewLine.
         */
    }

    private static final String TEXT = "this is it, isn't it";

    public void testOccurrencesOne() {
        assertEquals(1, StringUtil.occurrences(TEXT, "his"));
    }

    public void testOccurrencesNone() {
        assertEquals(0, StringUtil.occurrences(TEXT, "smelt"));
    }

    public void testOccurrencesMany() {
        assertEquals(3, StringUtil.occurrences(TEXT, "is"));
        assertEquals(2, StringUtil.occurrences(TEXT, "it"));
    }

    public void testOccurrencesSearchStringTooLarge() {
        assertEquals(0, StringUtil.occurrences(TEXT, TEXT + "sdfas"));
    }
}

