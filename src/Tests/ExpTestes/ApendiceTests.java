package Tests.ExpTestes;
import util.StringUtil;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ApendiceTests extends TestCase {

    public void testSplit() {
        String source = "Jeffrey   Hyman";
        String expectedSplit[] = {"Jeffrey", "", "", "Hyman" };
        assertTrue(java.util.Arrays.equals(expectedSplit, source.split(" ")));
    }

    public void testExtraneousSpaces() {
        final String fullName = "Jeffrey   Hyman";
        Name name = createName(fullName);
        assertEquals("Jeffrey", name.getFirstName());
        assertEquals("Hyman", name.getLastName());
    }

    public void testExtraneousWhitespace() {
        final String fullName = "Jeffrey   \t\t\n \r\fHyman";
        Name name = createName(fullName);
        assertEquals("Jeffrey", name.getFirstName());
        assertEquals("Hyman", name.getLastName());
    }

    public void testStripPhoneNumber() {
        String input = "(719) 555-9353 (home)";
        assertEquals("7195559353", StringUtil.stripToDigits(input));
    }

    private Name createName(String fullName) {
        Name name = new Name(fullName);
        assertEquals(fullName, name.getFullName());
        return name;
    }

    private List<String> split(String fullName) {
        List<String> results = new ArrayList<String>();
        for (String name: fullName.split(" "))
            results.add(name);
        return results;
    }

    public static String stripToDigits(String input) {
        return input.replaceAll("\\D+", "");
    }
}

class Name {
    String firstName = "Jeffrey";
    String lastName = "Hyman";
    String fullName = firstName + lastName;
    Name name = new Name(fullName);

    void getFirstName() {
        this.firstName = firstName;
        return firstName;
    }

    void getLastName() {
        this.lastName = lastName;
        return lastName;
    }

    void getFullName() {
        this.fullName = firstName + lastName;
        return fullName;
    }
}
