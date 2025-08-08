package Tests.sis.ui;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class StatusBar extends JLabel {
    public static final String NAME = "StatusBar";
    private final static String EMPTY = " ";
    private Map<JTextField,String> infos = new IdentityHashMap<JTextField,String>();

    public StatusBar() {
        super(EMPTY);
        setName(NAME);
        setBorder(BorderFactory.createLoweredBevelBorder());
    }

    public String getInfo(JTextField textField) {
        return infos.get(textField);
    }

    public void setInfo(final JTextField textField, final String text) {
        infos.put(textField, text);
        textField.addMouseListener(
            new MouseAdapter() {
                public void mouseEntered(MouseEvent event) {
                    setText(getInfo(textField));
                }
                public void mouseExited(MouseEvent event) {
                    setText(EMPTY);
                }
            }
        );
    }
}
