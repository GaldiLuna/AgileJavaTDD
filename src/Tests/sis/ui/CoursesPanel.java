package Tests.sis.ui;
import Tests.sis.studentinfo.*;
import static Tests.sis.ui.FieldCatalog.*;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class CoursesPanel extends JPanel {
    static final char ADD_BUTTON_MNEMONIC = 'A';
    static final String NAME = "coursesPanel";
    static final String COURSES_LABEL_TEXT = "Courses";
    static final String COURSES_LABEL_NAME = "coursesLabel";
    static final String COURSES_TABLE_NAME = "coursesTable";
    static final String COURSES_LIST_NAME = "coursesList";
    static final String ADD_BUTTON_TEXT = "Add";
    static final String ADD_BUTTON_NAME = "addButton";

    private StatusBar statusBar = new StatusBar();
    private CoursesTableModel coursesTableModel = new CoursesTableModel();
    //private Status status;
    private JButton addButton;
    private DefaultListModel coursesModel = new DefaultListModel();

    public CoursesPanel() {
        setName(NAME);
        createLayout();
    }

    private void createLayout() {
        JTable coursesTable = createCoursesTable();
        JLabel coursesLabel = createLabel(COURSES_LABEL_NAME, COURSES_LABEL_TEXT);
        //JList coursesList = createList(COURSES_LIST_NAME, coursesModel);
        //JScrollPane coursesScroll = new JScrollPane(coursesList);
        JScrollPane coursesScroll = new JScrollPane(coursesTable);

        coursesScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        setLayout(new BorderLayout());

        final int pad = 6;
        //setBorder(BorderFactory.createEmptyBorder(pad, pad, pad, pad));
        Border emptyBorder = BorderFactory.createEmptyBorder(pad, pad, pad, pad);
        Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Border titleBorder = BorderFactory.createTitledBorder(bevelBorder, COURSES_LABEL_TEXT);

        add(coursesLabel, BorderLayout.NORTH);
        add(coursesScroll, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    JPanel createBottomPanel() {
        //JLabel statusBar = new JLabel(" ");
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        //status = new Status(statusBar);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.statusBar, BorderLayout.SOUTH);
        panel.add(createInputPanel(), BorderLayout.CENTER);
        return panel;
//        addButton = createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
//        addButton.setMnemonic(ADD_BUTTON_MNEMONIC);
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
//        panel.add(Box.createRigidArea(new Dimension(0, 6)));
//        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        panel.add(addButton);
//        panel.add(Box.createRigidArea(new Dimension(0, 6)));
//        panel.add(createFieldsPanel());
//        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
//        return panel;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        PanelWithFields fieldsResult = createFieldsPanel();
        //panel.add(createFieldsPanel(), BorderLayout.CENTER);
        panel.add(fieldsResult.panel, BorderLayout.CENTER);

        addButton = createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
        addButton.setMnemonic(ADD_BUTTON_MNEMONIC);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private PanelWithFields createFieldsPanel() {
        GridBagLayout layout = new GridBagLayout();
        JPanel panel = new JPanel(layout);
        List<JTextField> textFields = new ArrayList<>();

        int i = 0;
        FieldCatalog catalog = new FieldCatalog();
        //java.util.List<JTextField> textFields = new java.util.ArrayList<>();
        for (String fieldName: getFieldNames()) {
            Field fieldSpec = catalog.get(fieldName);
            JTextField textField = TextFieldFactory.create(fieldSpec);
            textFields.add(textField);

            statusBar.setInfo(textField, fieldSpec.getInfo());
            addField(panel, layout, i++, createLabel(fieldSpec), TextFieldFactory.create(fieldSpec));
        }

        PanelWithFields result = new PanelWithFields();
        result.panel = panel;
        result.fields = textFields;

        return result;
    }

    private String[] getFieldNames() {
        return new String[]{ FieldCatalog.DEPARTMENT_FIELD_NAME,
                             FieldCatalog.NUMBER_FIELD_NAME,
                             FieldCatalog.EFFECTIVE_DATE_FIELD_NAME };
    }

    private void addField(JPanel panel, GridBagLayout layout, int row,
                          JLabel label, JTextField field) {

//        JLabel label = createLabel(labelName, labelText);
//        JTextField field = createField(fieldName, fieldColumns);
        Insets insets = new Insets(3, 3, 3, 3); //top-left-bottom-right

        layout.setConstraints(label,
                new GridBagConstraints(
                        0, row,                    // x, y
                        1, 1,                  // gridwidth, gridheight
                        40, 1,                  // weightx, weighty
                        GridBagConstraints.LINE_END,    //anchor
                        GridBagConstraints.NONE,        // fill
                        insets, 0, 0));     // padx, ipady

        layout.setConstraints(field,
                new GridBagConstraints(1, row, 2, 1, 60, 1,
                        GridBagConstraints.CENTER,
                        GridBagConstraints.HORIZONTAL,
                        insets, 0, 0));

        panel.add(label);
        panel.add(field);
    }

    private static void show(JPanel panel) {
        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel); // 1
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        show(new CoursesPanel());
    }

    void addCourseAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    void addFieldListener(String name, KeyListener listener) {
        getField(name).addKeyListener(listener);
    }

    void clearCourses() {
        if (coursesModel != null) {
            coursesModel.clear();
        }
    }

    private JLabel createLabel(String name, String text) {
        JLabel label = new JLabel(text);
        label.setName(name);
        return label;
    }

    private JLabel createLabel(Field fieldSpec) {
        JLabel label = new JLabel(fieldSpec.getLabel());
        label.setName(fieldSpec.getLabelName());
        return label;
    }

//    private JList createList(String name, ListModel model) {
//        JList list = new JList(model);
//        list.setName(name);
//        return list;
//    }

    private JTable createCoursesTable() {
        JTable table = new JTable(coursesTableModel);
        table.setName(COURSES_TABLE_NAME);
        table.setShowGrid(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
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

//    JList getList(String name) {
//        return (JList)Util.getComponent(this, name);
//    }

    JButton getButton(String name) {
        return (JButton)Util.getComponent(this, name);
    }

    JTextField getField(String name) {
        return (JTextField)Util.getComponent(this, name);
    }

    void addCourse(Course course) {
        coursesTableModel.add(course);
    }

    Course getCourse(int index) {
        return coursesTableModel.get(index);
    }

    String getText(String textFieldName) {
        return getField(textFieldName).getText();
    }

    void setText(String textFieldName, String text) {
        getField(textFieldName).setText(text);
    }

    void setEnabled(String name, boolean state) {
        getButton(name).setEnabled(state);
    }

    int getCourseCount() {
        return coursesTableModel.getRowCount();
    }

    JTable getTable(String name) {
        return (JTable)Util.getComponent(this, name);
    }
}

