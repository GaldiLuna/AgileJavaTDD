package Tests.sis.ui;

import javax.swing.*;

public class Status {
    private JLabel statusBar;

    public Status(JLabel statusBar) {
        this.statusBar = statusBar;
    }

    public void addText(JTextField textField, String message) {
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                statusBar.setText(message);
            }
        });
    }
}
