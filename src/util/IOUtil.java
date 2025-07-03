package util;

import java.io.*;

public class IOUtil {
    public static boolean delete(String... filenames) {
        boolean deletedAll = true;
        for (String filename: filenames)
            if (!new File(filename).delete())
                deletedAll = false;
        return deletedAll;
    }
}
