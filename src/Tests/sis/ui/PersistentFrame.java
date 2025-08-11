package Tests.sis.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.prefs.*;

public class PersistentFrame extends JFrame {
    static final int DEFAULT_X = 100;
    static final int DEFAULT_Y = 101;
    static final int DEFAULT_WIDTH = 300;
    static final int DEFAULT_HEIGHT = 400;

    private static final String X = "x";
    private static final String Y = "y";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";

    private Preferences preferences = Preferences.userNodeForPackage(this.getClass());

    public void initialize() {
        int x = preferences.getInt(X, DEFAULT_X); // 1
        int y = preferences.getInt(Y, DEFAULT_Y); // 2
        int width = preferences.getInt(WIDTH, DEFAULT_WIDTH);
        int height = preferences.getInt(HEIGHT, DEFAULT_HEIGHT);

        setBounds(x, y, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                saveWindowPosition();
            }
        });
    }

    private void saveWindowPosition() {
        Rectangle bounds = getBounds();
        preferences.putInt(X, bounds.x); // 3
        preferences.putInt(Y, bounds.y);
        preferences.putInt(WIDTH, bounds.width);
        preferences.putInt(HEIGHT, bounds.height);
        try {
            preferences.flush(); // 4
        }
        catch (BackingStoreException e) {
            // não é crucial; registrar mensagem
        }
    }

    // para teste
    void clearPreferences() throws BackingStoreException {
        preferences.clear();
        preferences.flush();
    }
}
