package Tests.sis.ui;
import Tests.sis.studentinfo.*;
import util.*;

import junit.framework.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

public class SisTest extends TestCase {
    private Sis sis;
    private JFrame frame;
    private CoursesPanel panel;
    private Robot robot;

    protected void setUp() throws Exception {
        sis = new Sis();
        frame = sis.getFrame();
        panel = (CoursesPanel)Util.getComponent(frame, CoursesPanel.NAME);
        robot = new Robot();
    }

    public void testCreate() {
        final double tolerance = 0.05;
        assertEquals(Sis.HEIGHT, frame.getSize().getHeight(), tolerance);
        assertEquals(Sis.WIDTH, frame.getSize().getWidth(), tolerance);
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        //assertTrue(containsCoursesPanel(frame));
        assertNotNull(Util.getComponent(frame, CoursesPanel.NAME));
        assertEquals(Sis.COURSES_TITLE, frame.getTitle());
        Image image = frame.getIconImage();
        assertEquals(image, ImageUtil.create("/images/courses.gif"));

        CoursesPanel panel = (CoursesPanel)Util.getComponent(frame, CoursesPanel.NAME);
        assertNotNull(panel);

        verifyFilter(panel);
    }

    private void verifyFilter(CoursesPanel panel) {
        DocumentFilter filter =
                getFilter(panel, CoursesPanel.DEPARTMENT_FIELD_NAME);
        assertTrue(filter.getClass() == UpcaseFilter.class);
    }

    private DocumentFilter getFilter(CoursesPanel panel, String fieldName) {
        JTextField field = panel.getField(fieldName);
        AbstractDocument document = (AbstractDocument)field.getDocument();
        return document.getDocumentFilter();
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

    public void testAddCourse() {
        CoursesPanel panel = (CoursesPanel)Util.getComponent(frame, CoursesPanel.NAME);
        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, "MATH");
        panel.setText(CoursesPanel.NUMBER_FIELD_NAME, "300");

        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
        button.doClick();

        Course course = panel.getCourse(0);
        assertEquals("MATH", course.getDepartment());
        assertEquals("300", course.getNumber());
    }

    public void testKeyListeners() throws Exception {
        sis.show();
        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
        assertFalse(button.isEnabled());
        selectField(CoursesPanel.DEPARTMENT_FIELD_NAME);
        type('A');
        selectField(CoursesPanel.NUMBER_FIELD_NAME);
        type('1');
        assertTrue(button.isEnabled());
    }

    private void selectField(String name) throws Exception {
        JTextField field = panel.getField(name);
        Point point = field.getLocationOnScreen();
        robot.mouseMove(point.x, point.y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    private void type(int key) throws Exception {
        robot.keyPress(key);
        robot.keyRelease(key);
    }

    public void testSetAddButtonState() throws Exception {
        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
        assertFalse(button.isEnabled());
        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, "a");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());
        panel.setText(CoursesPanel.NUMBER_FIELD_NAME, "1");
        sis.setAddButtonState();
        assertTrue(button.isEnabled());
        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, " ");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());
        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, "a");
        panel.setText(CoursesPanel.NUMBER_FIELD_NAME, " ");
        sis.setAddButtonState();
        assertFalse(button.isEnabled());
    }
}
