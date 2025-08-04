package util;

import junit.framework.*;
import java.awt.*;

public class ImageUtilTest extends  TestCase {
    public void testLoadImage() {
        assertNull(ImageUtil.create("/images/bogusFilename.gif"));
        assertNotNull(ImageUtil.create("/images/courses.gif"));
    }
}
