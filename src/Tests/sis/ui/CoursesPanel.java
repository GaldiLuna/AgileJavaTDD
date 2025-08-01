package Tests.sis.ui;
import Tests.sis.studentinfo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CoursesPanel extends JPanel {
    static final String NAME = "coursesPanel";
    static final String COURSES_LABEL_TEXT = "Courses";
    static final String COURSES_LABEL_NAME = "coursesLabel";
//    static final String LABEL_TEXT = "Courses";
//    static final String LABEL_NAME = "coursesLabel";
    static final String COURSES_LIST_NAME = "coursesList";
    static final String ADD_BUTTON_TEXT = "Add";
    static final String ADD_BUTTON_NAME = "addButton";
    static final String DEPARTMENT_FIELD_NAME = "deptField";
    static final String NUMBER_FIELD_NAME = "numberField";
    static final String DEPARTMENT_LABEL_NAME = "deptLabel";
    static final String NUMBER_LABEL_NAME = "numberLabel";
    static final String DEPARTMENT_LABEL_TEXT = "Department";
    static final String NUMBER_LABEL_TEXT = "Number";

    private JButton addButton;
    private DefaultListModel coursesModel = new DefaultListModel();

    public static void main(String[] args) {
        show(new CoursesPanel());
    }

    private static void show(JPanel panel) {
        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel); // 1
        frame.setVisible(true);
    }

    public CoursesPanel() {
        setName(NAME);
        createLayout();
    }

    private void createLayout() {
        JLabel coursesLabel = createLabel(COURSES_LABEL_NAME, COURSES_LABEL_TEXT);
        JList coursesList = createList(COURSES_LIST_NAME, coursesModel);

        addButton = createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
        int columns = 20;

        JLabel departmentLabel = createLabel(DEPARTMENT_LABEL_NAME, DEPARTMENT_LABEL_TEXT);
        JTextField departmentField = createField(DEPARTMENT_FIELD_NAME, columns);
        JLabel numberLabel = createLabel(NUMBER_LABEL_NAME, NUMBER_LABEL_TEXT);
        JTextField numberField = createField(NUMBER_FIELD_NAME, columns);
//        JLabel coursesLabel = createLabel(COURSES_LABEL_NAME, COURSES_LABEL_TEXT);
//        JList coursesList = createList(COURSES_LIST_NAME, coursesModel);

        addButton = createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);

        int rows = 4;
        int cols = 2;
        setLayout(new GridLayout(rows, cols));
        setLayout(new BorderLayout());

        add(coursesLabel, BorderLayout.NORTH);
        add(coursesList, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
        add(addButton);
        add(new JPanel());
        add(departmentLabel);
        add(departmentField);
        add(numberLabel);
        add(numberField);
    }

    void addCourseAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    private JLabel createLabel(String name, String text) {
        JLabel label = new JLabel(text);
        label.setName(name);
        return label;
    }

    private JList createList(String name, ListModel model) {
        JList list = new JList(model);
        list.setName(name);
        return list;
    }

    private JButton createButton(String name, String text) {
        JButton button = new JButton(text);
        button.setName(name);
        return button;
    }

    private JTextField createField(String name, int columns) {
        JTextField field = new JTextField(columns);
        field.setName(name);
        return field;
    }

    JLabel getLabel(String name) {
        return (JLabel)Util.getComponent(this, name);
    }

    JList getList(String name) {
        return (JList)Util.getComponent(this, name);
    }

    JButton getButton(String name) {
        return (JButton)Util.getComponent(this, name);
    }

    JTextField getField(String name) {
        return (JTextField)Util.getComponent(this, name);
    }

    void addCourse(Course course) {
        coursesModel.addElement(new CourseDisplayAdapter(course));
    }

    Course getCourse(int index) {
        Course adapter = (CourseDisplayAdapter)coursesModel.getElementAt(index);
        return adapter;
    }

    String getText(String textFieldName) {
        return getField(textFieldName).getText();
    }

    void setText(String textFieldName, String text) {
        getField(textFieldName).setText(text);
    }

    JPanel createBottomPanel() {
        addButton = createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 6)));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(addButton);
        panel.add(Box.createRigidArea(new Dimension(0, 6)));
        panel.add(createFieldsPanel());
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        return panel;
    }

    JPanel createFieldsPanel() {
        GridBagLayout layout = new GridBagLayout();
        JPanel panel = new JPanel(layout);
        int columns = 20;

        addField(panel, layout, 0,
                DEPARTMENT_LABEL_NAME, DEPARTMENT_LABEL_TEXT,
                DEPARTMENT_FIELD_NAME, columns);
        addField(panel, layout, 1,
                NUMBER_LABEL_NAME, NUMBER_LABEL_TEXT,
                NUMBER_FIELD_NAME, columns);

        return panel;
    }

    private void addField(JPanel panel, GridBagLayout layout, int row,
                          String labelName, String labelText, String fieldName, int fieldColumns) {

        JLabel label = createLabel(labelName, labelText);
        JTextField field = createField(fieldName, fieldColumns);
        Insets insets = new Insets(3, 3, 3, 3); //top-left-bottom-right

        layout.setConstraints(label,
                new GridBagConstraints(
                        0, row,                // x, y
                        1, 1,              // gridwidth, gridheight
                        40, 1,              // weightx, weighty
                        LINE_END,                   //anchor
                        NONE,                       // fill
                        insets, 0, 0)); // padx, ipady

        layout.setConstraints(field,
                new GridBagConstraints(1, row, 2, 1, 60, 1,
                        CENTER, HORIZONTAL,
                        insets, 0, 0));

        panel.add(label);
        panel.add(field);
    }

}
