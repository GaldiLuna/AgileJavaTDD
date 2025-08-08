package Tests.sis.ui;
import Tests.sis.studentinfo.*;
import util.ImageUtil;

import Tests.sis.ui.FieldCatalog.*;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;

public class Sis {
    static final int WIDTH = 550;
    static final int HEIGHT = 700;
    static final String COURSES_TITLE = "Course Listing";

    private JFrame frame = new JFrame(COURSES_TITLE);
    private CoursesPanel panel;

    public static void main(String[] args) {
        new Sis().show();
    }

    public Sis() {
        initialize();
    }

    private void initialize() {
        createCoursesPanel();
        createKeyListeners();
        createInputFilters();
        Image image = ImageUtil.create("/images/courses.gif");
        frame.setIconImage(image);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
    }

    void show() {
        frame.setVisible(true);
    }

    JFrame getFrame() {
        return frame;
    }

    void close() {
        frame.dispose();
    }

    void createCoursesPanel() {
        panel = new CoursesPanel();
        panel.addCourseAddListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addCourse();
                }
            }
        );
    }

    void createKeyListeners() {
        KeyListener listener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                setAddButtonState();
            }
        };
        panel.addFieldListener(FieldCatalog.DEPARTMENT_FIELD_NAME, listener);
        panel.addFieldListener(FieldCatalog.NUMBER_FIELD_NAME, listener);
        setAddButtonState();
    }

    private void createInputFilters() {
        JTextField field =
                panel.getField(FieldCatalog.DEPARTMENT_FIELD_NAME);
        AbstractDocument document = (AbstractDocument)field.getDocument();
        document.setDocumentFilter(new UpcaseFilter());
    }

    private void addCourse() {
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            Course course = new Course(panel.getText(FieldCatalog.DEPARTMENT_FIELD_NAME),
                    panel.getText(FieldCatalog.NUMBER_FIELD_NAME));
            try { Thread.sleep(3000); } catch (InterruptedException e) {}
            JFormattedTextField effectiveDateField = (JFormattedTextField)panel.getField(FieldCatalog.EFFECTIVE_DATE_FIELD_NAME);
            Date date = (Date)effectiveDateField.getValue();
            course.setEffectiveDate(date);
            panel.addCourse(course);
        }
        finally {
            frame.setCursor(Cursor.getDefaultCursor());
        }

        //addCourse() usando o invokeAndWait() - ESSA MUDANÇA QUEBRARÁ O testAddCourse de SisTest.java
        /**
         * private void addCourse() {
         *     Thread thread = new Thread() {
         *         public void run() {
         *             panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, false);
         *             try {
         *                 final Course course = basicAddCourse();
         *                 SwingUtilities.invokeAndWait(new Runnable() {
         *                     public void run() {
         *                         panel.addCourse(course);
         *                         panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, true);
         *                     }
         *                 });
         *             }
         *             catch (Exception e) {}
         *         }
         *     };
         *     thread.start();
         * }
         */

        //ESTA É UMA SOLUÇÃO INADEQUADA
        /**
         * Thread thread = new Thread() {
         *         public void run() {
         *             panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, false);
         *             Course course = basicAddCourse();
         *             panel.addCourse(course);
         *             panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, true);
         *         }
         *     };
         *     thread.start();
         */
    }

    void setAddButtonState() {
        panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME,
                !isEmpty(FieldCatalog.DEPARTMENT_FIELD_NAME) && !isEmpty(FieldCatalog.NUMBER_FIELD_NAME));
    }

    private boolean isEmpty(String field) {
        String value = panel.getText(field);
        return value.trim().equals("");
    }

    //PARTE DA SOLUÇÃO INADEQUADA
    /**
     * private Course basicAddCourse() {
     *     try { Thread.sleep(3000); } catch (InterruptedException e) {}
     *     Course course =
     *         new Course(
     *             panel.getText(FieldCatalog.DEPARTMENT_FIELD_NAME),
     *             panel.getText(FieldCatalog.NUMBER_FIELD_NAME));
     *     JFormattedTextField effectiveDateField =
     *         (JFormattedTextField)panel.getField(FieldCatalog.EFFECTIVE_DATE_FIELD_NAME);
     *     Date date = (Date)effectiveDateField.getValue();
     *     course.setEffectiveDate(date);
     *     return course;
     * }
     */

}
