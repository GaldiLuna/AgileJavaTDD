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
    }
}

/**
 * Atualize qualquer código que usava NEWLINE diretamente para referenciar StringUtil.NEWLINE.
 * Substitua concatenações manuais de \n por chamadas a StringUtil.appendNewLine.
 */