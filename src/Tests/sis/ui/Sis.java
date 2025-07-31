package Tests.sis.ui;

import javax.swing.*;

public class Sis {
    static final int WIDTH = 550;
    static final int HEIGHT = 700;

    private JFrame frame = new JFrame();

    public static void main(String[] args) {
        new Sis().show();
    }

    Sis() {
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
}
