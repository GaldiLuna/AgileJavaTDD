package Tests.ExpTestes;

import junit.framework.TestCase;
import java.util.regex.*;

public class RegexTest extends TestCase {
    public void testSearch() {
        String[] textLines =
                {"public class Test {",
                        "public void testMethod() {}",
                        "public void testNotReally(int x) {}",
                        "public void test() {}",
                        "public String testNotReally() {}",
                        "}" };
        String text = join(textLines);
        String testMethodRegex = "public\\s+void\\s+test\\w*\\s*\\(\\s*\\)\\s*\\{";
        Pattern pattern = Pattern.compile(testMethodRegex);
        Matcher matcher = pattern.matcher(text);
        assertTrue(matcher.find());
        assertEquals(text.indexOf(textLines[1]), matcher.start());
        assertTrue(matcher.find());
        assertEquals(text.indexOf(textLines[3]), matcher.start());
        assertFalse(matcher.find());
    }

    private String join(String[] textLines) {
        StringBuilder builder = new StringBuilder();
        for (String line: textLines) {
            if (builder.length() > 0)
                builder.append("\n");
            builder.append(line);
        }
        return builder.toString();
    }
}
