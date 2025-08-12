package Tests.sis.ui;

import junit.framework.*;
import java.awt.*;
import java.util.prefs.BackingStoreException;
import static Tests.sis.ui.PersistentFrame.*;

public class PersistentFrameTest extends TestCase {
    private PersistentFrame frame;

    protected void setUp() throws BackingStoreException {
        frame = new PersistentFrame();
        frame.clearPreferences();
        frame.initialize();
        frame.setVisible(true);
    }

    protected void tearDown() {
        frame.dispose();
    }

    public void testCreate() {
        assertEquals(
                new Rectangle(
                        DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT),
                frame.getBounds());
    }

    public void testMove() {
        int x = 600;
        int y = 650;
        int width = 150;
        int height = 160;
        frame.setBounds(x, y, width, height);
        frame.saveWindowPosition();
        frame.dispose();

        PersistentFrame frame2 = new PersistentFrame();
        frame2.initialize();
        frame2.setVisible(true);
        assertEquals(new Rectangle(x, y, width, height), frame2.getBounds());
    }
}
