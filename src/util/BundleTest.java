package util;

import junit.framework.TestCase;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class BundleTest extends TestCase {
    private static final String KEY = "someKey";
    private static final String VALUE = "a value";
    private static final String TEST_APPEND = "test";
    private static final String FILENAME = "./classes/util/" + Bundle.getName() + "Test.properties";
    private String filename;
    private String existingBundleName;

    private void prepare() {
        TestUtil.delete(filename);
        existingBundleName = Bundle.getName();
        Bundle.setName(existingBundleName + TEST_APPEND);
    }

    protected void setUp() {
        TestUtil.delete(FILENAME);
        existingBundleName = Bundle.getName();
        Bundle.setName(existingBundleName + TEST_APPEND);
    }

    protected void tearDown() {
        Bundle.setName(existingBundleName);
        TestUtil.delete(filename);
    }

    public void testMessage() throws IOException {
        filename = getFilename();
        prepare();
        final String value = "open the door";
        writeBundle(value);
        assertEquals(value, Bundle.get(KEY));
    }

    public void testLocalizedMessage() throws IOException {
        final String language = "es";
        final String country = "MX";
        filename = getFilename(language, country);
        prepare();
        Locale mexican = new Locale(language, country);
        Locale current = Locale.getDefault();
        try {
            Locale.setDefault(mexican);
            final String value = "abre la puerta";
            writeBundle(value);
            assertEquals(value, Bundle.get(KEY));
        }
        finally {
            Locale.setDefault(current);
        }
    }

    private void writeBundle(String value) throws IOException {
        LineWriter writer = new LineWriter();
        String record = String.format("%s=%s", KEY, value);
        writer.write(getFilename(), new String[]{record});
    }

    private String getFilename(String language, String country) {
        StringBuilder builder = new StringBuilder();
        builder.append("./classes/util/");
        builder.append(Bundle.DEFAULT_BASE_NAME);
        builder.append("Test");
        if (language.length() > 0)
            builder.append("_" + language);
        if (country.length() > 0)
            builder.append("_" + country);
        builder.append(".properties");
        return builder.toString();
    }

    private String getFilename() {
        return getFilename("", "");
    }

//    public void testMessageFormat() {
//        String message = "You have {0}dependents, {1}. Is this correct?";
//        MessageFormat formatter = new MessageFormat(message);
//        assertEquals("You have 5 dependents, Señor Wences. Is this correct?",
//                formatter.format(message, 5, "Señor Wences"));
//    }
//
//    public void testChoiceFormat() {
//        String message = "You have {0}, {1}. Is this correct?";
//        double[] dependentLimits = {0, 1, 2 };
//        String[] dependentFormats = {"no dependents", "one dependent", "{0}dependents" };
//        ChoiceFormat formatter = new ChoiceFormat(dependentLimits, dependentFormats);
//        Format[] formats = {formatter, null };
//        MessageFormat messageFormatter = new MessageFormat(message);
//        messageFormatter.setFormats(formats);
//        assertEquals("You have one dependent, Señor Wences. Is this correct?",
//                messageFormatter.format(new Object[] {1, "Señor Wences" }));
//        assertEquals("You have 10 dependents, Señor Wences. Is this correct?",
//                messageFormatter.format(new Object[] {10, "Señor Wences" }));
//    }

    public void testCallByValuePrimitives() {
        int count = 1;
        increment(count);
        assertEquals(1, count);
    }

    private void increment(int count) {
        count++;
    }

    public void testCallByValueReferences() {
        Customer customer = new Customer(1);
        incrementId(customer);
        assertEquals(2, customer.getId());
    }

    private void incrementId(Customer customer) {
        customer.setId(customer.getId() + 1);
        customer = new Customer(22); // não faça isso
    }

    class Customer {
        private int id;
        Customer(int id) { this.id = id; }
        void setId(int id) { this.id = id; }
        public int getId() { return id; }
    }

    public void testGetPropertyWithDefault() {
        Properties existingProperties = System.getProperties();
        try {
            System.setProperties(new Properties()); // remove todas as propriedades
            assertEquals("default", System.getProperty("noSuchProperty", "default"));
        }
        finally {
            System.setProperties(existingProperties);
        }
    }
}
