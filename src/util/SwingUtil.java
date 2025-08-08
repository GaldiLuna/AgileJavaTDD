package util;

import javax.swing.*;

public class SwingUtil {
    public static JPanel createPanel(JComponent... components) {
        JPanel panel = new JPanel();
        for (JComponent component: components)
            panel.add(component);
        return panel;
    }

    public static JFrame createFrame(JPanel panel) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        return frame;
    }
}
