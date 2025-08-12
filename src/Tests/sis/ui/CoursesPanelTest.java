package Tests.sis.ui;

import Tests.sis.testing.Date;
import junit.framework.*;
import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

import Tests.sis.studentinfo.*;
import static Tests.sis.ui.CoursesPanel.*;
import static Tests.sis.ui.FieldCatalog.*;

public class CoursesPanelTest extends TestCase {
    private CoursesPanel panel;
    private boolean wasClicked;

    protected void setUp() {
        panel = new CoursesPanel();
    }

    public void testCreate() {
        //assertEmptyList(COURSES_LIST_NAME);
        assertEmptyTable(COURSES_TABLE_NAME);
        assertButtonText(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
        String[] fields = { FieldCatalog.DEPARTMENT_FIELD_NAME,
                            FieldCatalog.NUMBER_FIELD_NAME,
                            FieldCatalog.EFFECTIVE_DATE_FIELD_NAME };
        assertFields(fields);
        JButton button = panel.getButton(ADD_BUTTON_NAME);
        assertEquals(ADD_BUTTON_MNEMONIC, button.getMnemonic());

//        assertLabelText(COURSES_LABEL_NAME, COURSES_LABEL_TEXT);
//        assertLabelText(DEPARTMENT_LABEL_NAME, DEPARTMENT_LABEL_TEXT);
//        assertEmptyField(DEPARTMENT_FIELD_NAME);
//        assertLabelText(NUMBER_LABEL_NAME, NUMBER_LABEL_TEXT);
//        assertEmptyField(NUMBER_FIELD_NAME);
    }

    private void assertFields(String[] fieldNames) {
        StatusBar statusBar = (StatusBar)Util.getComponent(panel, StatusBar.NAME);
        assertNotNull(statusBar);
        FieldCatalog catalog = new FieldCatalog();

        for (String fieldName: fieldNames) {
            JTextField field = panel.getField(fieldName);
            Field fieldSpec = catalog.get(fieldName);
            assertEquals(fieldSpec.getInfo(), statusBar.getInfo(field));
            assertLabelText(fieldSpec.getLabelName(), fieldSpec.getLabel());
        }

//        List<JTextField> fields = panel.getFields();
//        for (int i = 0; i < fieldNames.length; i++) {
//            JTextField field = fields.get(i);
//            Field fieldSpec = catalog.get(fieldNames[i]);
//            assertEquals(fieldSpec.getInfo(), statusBar.getInfo(field));
//        }
    }

    private void assertLabelText(String name, String text) {
        JLabel label = panel.getLabel(name);
        assertEquals(text, label.getText());
    }

    private void assertEmptyField(String name) {
        JTextField field = panel.getField(name);
        assertEquals("", field.getText());
    }

//    private void assertEmptyList(String name) {
//        JList list = panel.getList(name);
//        assertEquals(0, list.getModel().getSize());
//    }

    private void assertEmptyTable(String name) {
        JTable table = panel.getTable(name);
        assertEquals(0, table.getModel().getRowCount());
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
        JTable table = panel.getTable(COURSES_TABLE_NAME);
        CoursesTableModel model = (CoursesTableModel)table.getModel();
        assertSame(course, model.get(0));

//        JList list = panel.getList(COURSES_LIST_NAME);
//        ListModel model = list.getModel();
//        assertEquals("ENGL-101", model.getElementAt(0).toString());
    }

    public void testEnableDisable() {
        panel.setEnabled(ADD_BUTTON_NAME, true);
        JButton button = panel.getButton(ADD_BUTTON_NAME);
        assertTrue(button.isEnabled());
        panel.setEnabled(ADD_BUTTON_NAME, false);
        assertFalse(button.isEnabled());
    }

    public void testAddListener() throws Exception {
        KeyListener listener = new KeyAdapter() {};
        panel.addFieldListener(DEPARTMENT_FIELD_NAME, listener);
        JTextField field = panel.getField(DEPARTMENT_FIELD_NAME);
        KeyListener[] listeners = field.getKeyListeners();
        assertEquals(1, listeners.length);
        assertSame(listener, listeners[0]);
    }

    private void verifyEffectiveDate() {
        assertLabelText(EFFECTIVE_DATE_LABEL_NAME,
                EFFECTIVE_DATE_LABEL_TEXT);
        JFormattedTextField dateField =
                (JFormattedTextField)panel.getField(EFFECTIVE_DATE_FIELD_NAME);
        DateFormatter formatter = (DateFormatter)dateField.getFormatter();
        SimpleDateFormat format = (SimpleDateFormat)formatter.getFormat();
        assertEquals("MM/dd/yy", format.toPattern());
        assertEquals(Date.class, dateField.getValue().getClass());
    }
}
