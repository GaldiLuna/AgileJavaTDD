package Tests.sis.ui;

import junit.framework.*;
import javax.swing.*;
import java.awt.*;

public class SisTest extends TestCase {
    private Sis sis;
    private JFrame frame;

    protected void setUp() {
        sis = new Sis();
        frame = sis.getFrame();
    }

    public void testCreate() {
        final double tolerance = 0.05;
        assertEquals(Sis.HEIGHT, frame.getSize().getHeight(), tolerance);
        assertEquals(Sis.WIDTH, frame.getSize().getWidth(), tolerance);
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertTrue(containsCoursesPanel(frame));
        assertNotNull(getComponent(frame, CoursesPanel.NAME));
        assertNotNull(Util.getComponent(frame, CoursesPanel.NAME));
    }

    private boolean containsCoursesPanel(JFrame frame) {
        Container pane = frame.getContentPane();
        for (Component component: pane.getComponents())
            if (component instanceof CoursesPanel)
                return true;
        return false;
    }

    public void testShow() {
        sis.show();
        assertTrue(frame.isVisible());
    }

    protected void tearDown() {
        sis.close();
    }

    private Component getComponent(JFrame frame, String name) {
        Container container = frame.getContentPane();
        for (Component component: container.getComponents())
            if (name.equals(component.getName()))
                return component;
        return null;
    }
}
