package util;
import junit.framework.TestCase;

public class TestStringUtil extends TestCase {
    public void testAppendNewLine() {
        assertEquals("test\n", StringUtil.appendNewLine("test"));
        assertEquals(".\n", StringUtil.appendNewLine("."));
        assertEquals("\n", StringUtil.appendNewLine(""));
    }
}
