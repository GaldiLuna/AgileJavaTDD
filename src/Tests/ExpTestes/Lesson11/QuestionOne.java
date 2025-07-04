package Tests.ExpTestes.Lesson11;

import junit.framework.TestCase;

import java.io.*;

public class QuestionOne extends TestCase {
    private static final String FILENAME = "exercise1_test.txt";
    private static final String FILE_TEXT = "Create a test to write the text of this exercise " +
            "to the file system. The test should read the file " +
            "back in and make assertions about the content. Ensure " +
            "that you can run the test multiple times and have it " +
            "pass. Finally, make sure that there are no leftover files " +
            "when the test finishes, even if an exception is thrown.";

    @Override
    protected void setUp() throws Exception {
        //Garante que o arquivo não existe antes do teste
        new File(FILENAME).delete();
    }

    @Override
    protected void tearDown() throws Exception {
        //Garante que o arquivo seja deletado depois do teste
        new File(FILENAME).delete();
    }

    public void testWriteAndReadFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            writer.write(FILE_TEXT);
        }

        StringBuilder contentRead = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentRead.append(line);
            }
        }

        assertEquals(FILE_TEXT, contentRead.toString());
    }
}
