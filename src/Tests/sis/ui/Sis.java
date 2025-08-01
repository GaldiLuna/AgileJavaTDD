package Tests.sis.ui;
import Tests.sis.studentinfo.*;

import javax.swing.*;
import java.awt.event.*;

public class Sis {
    static final int WIDTH = 550;
    static final int HEIGHT = 700;

    private JFrame frame = new JFrame();
    private CoursesPanel panel;

    public static void main(String[] args) {
        new Sis().show();
    }

    public Sis() {
        initialize();
    }

    private void initialize() {
        createCoursesPanel();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CoursesPanel());
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
        panel.addCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });
    }

    private void addCourse() {
        Course course = new Course(panel.getText(CoursesPanel.DEPARTMENT_FIELD_NAME),
                                    panel.getText(CoursesPanel.NUMBER_FIELD_NAME));
        panel.addCourse(course);
    }
}
