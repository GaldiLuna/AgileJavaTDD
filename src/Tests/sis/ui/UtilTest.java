package Tests.sis.ui;

import junit.framework.*;
import javax.swing.*;
import java.awt.*;

public class UtilTest extends TestCase {
    private JPanel panel;

    protected void setUp() {
        panel = new JPanel();
    }

    public void testNotFound() {
        assertNull(Util.getComponent(panel, "abc"));
    }

    public void testDirectlyEmbeddedComponent() {
        final String name = "a";
        Component component = new JLabel("x");
        component.setName(name);
        panel.add(component);
        assertEquals(component, Util.getComponent(panel, name));
    }

    public void testSubcomponent() {
        final String name = "a";
        Component component = new JLabel("x");
        component.setName(name);
        JPanel subpanel = new JPanel();
        subpanel.add(component);
        panel.add(subpanel);
        assertEquals(component, Util.getComponent(panel, name));
    }
}
