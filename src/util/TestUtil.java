package util;

import junit.framework.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class TestUtil {
    public static void assertGone(String... filenames) {
        for (String filename: filenames)
            Assert.assertFalse(new File(filename).exists());
    }

    public static void delete(String filename) {
        File file = new File(filename);
        if (file.exists())
            Assert.assertTrue(file.delete());
    }

    public static void assertDateEquals(int expectedYear, int expectedMonth, int expectedDay, Date actualDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actualDate);

        int actualYear = calendar.get(Calendar.YEAR);
        int actualMonth = calendar.get(Calendar.MONTH);
        int actualDay = calendar.get(Calendar.DAY_OF_MONTH);

        Assert.assertEquals(expectedYear, actualYear);
        // O Calendar usa meses de 0 a 11, então ajustamos o valor esperado.
        Assert.assertEquals(expectedMonth - 1, actualMonth);
        Assert.assertEquals(expectedDay, actualDay);
    }
}
