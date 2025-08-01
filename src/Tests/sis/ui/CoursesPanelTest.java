package Tests.sis.ui;

import junit.framework.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Tests.sis.studentinfo.*;
import static Tests.sis.ui.CoursesPanel.*;

public class CoursesPanelTest extends TestCase {
    private CoursesPanel panel;
    private boolean wasClicked;

    protected void setUp() {
        panel = new CoursesPanel();
    }

    public void testCreate() {
        assertLabelText(COURSES_LABEL_NAME, COURSES_LABEL_TEXT);
        assertEmptyList(COURSES_LIST_NAME);
        assertButtonText(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
        assertLabelText(DEPARTMENT_LABEL_NAME, DEPARTMENT_LABEL_TEXT);
        assertEmptyField(DEPARTMENT_FIELD_NAME);
        assertLabelText(NUMBER_LABEL_NAME, NUMBER_LABEL_TEXT);
        assertEmptyField(NUMBER_FIELD_NAME);
    }

    private void assertLabelText(String name, String text) {
        JLabel label = panel.getLabel(name);
        assertEquals(text, label.getText());
    }

    private void assertEmptyField(String name) {
        JTextField field = panel.getField(name);
        assertEquals("", field.getText());
    }

    private void assertEmptyList(String name) {
        JList list = panel.getList(name);
        assertEquals(0, list.getModel().getSize());
    }

    private void assertButtonText(String name, String text) {
        JButton button = panel.getButton(name);
        assertEquals(text, button.getText());
    }

    private JLabel getLabel(JPanel panel) {
        for (Component component: panel.getComponents())
            if (component instanceof JLabel)
                return (JLabel)component;
        return null;
    }

    private Component getComponent(Container container, String name) {
        for (Component component: container.getComponents())
            if (name.equals(component.getName()))
                return component;
        return null;
    }

    public void testAddButtonClick() {
        JButton button = panel.getButton(ADD_BUTTON_NAME);
        wasClicked = false;
        panel.addCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wasClicked = true;
            }
        });
        button.doClick();
        assertTrue(wasClicked);
    }

    public void testAddCourse() {
        Course course = new Course("ENGL", "101");
        panel.addCourse(course);
        JList list = panel.getList(COURSES_LIST_NAME);
        ListModel model = list.getModel();
        assertEquals("ENGL-101", model.getElementAt(0).toString());
    }
}
