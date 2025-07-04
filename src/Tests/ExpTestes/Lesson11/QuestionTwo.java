package Tests.ExpTestes.Lesson11;

import junit.framework.TestCase;

import java.io.*;

public class QuestionTwo extends TestCase {
    private static final String FILENAME = "buffer_perf_test.txt";

    @Override
    protected void tearDown() throws Exception {
        new File(FILENAME).delete();
    }

    public void testBufferPerformance() throws IOException {
        System.out.println("Tamanho (bytes) | Sem Buffer (ns) | Com Buffer (ns) | Ganho");
        System.out.println("------------------------------------------------------------------");

        for (int size = 1000; size <= 1000000; size *= 10) {
            String data = createTestData(size);

            long timeUnbuffered = timeWrite(new FileWriter(FILENAME), data);
            long timeBuffered = timeWrite(new BufferedWriter(new FileWriter(FILENAME)), data);

            double gain = (double) timeUnbuffered / timeBuffered;
            System.out.printf("%-15d | %-15d | %-15d | %.2fx%n", size, timeUnbuffered, timeBuffered, gain);
        }
    }

    private long timeWrite(Writer writer, String data) throws IOException {
        long start = System.nanoTime();
        try (writer) { //Usando try-with-resources para garantir o fechamento
            for (int i = 0; i < data.length(); i++) {
                writer.write(data.charAt(i));
            }
        }
        return System.nanoTime() - start;
    }

    private String createTestData(int size) {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append('a');
        }
        return sb.toString();
    }
}
