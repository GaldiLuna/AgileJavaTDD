package Tests.sis.ui;

import java.awt.*;
import javax.swing.*;

public class Util {
    static Component getComponent(Container container, String name) {
        for (Component component: container.getComponents()) {
            if (name.equals(component.getName()))
                return component;
            if (component instanceof Container) {
                Container subcontainer = (Container)component;
                Component subcomponent = getComponent(subcontainer, name);
                if (subcomponent != null)
                    return subcomponent;
            }
        }
        return null;
    }

    static Component getComponent(JFrame frame, String name) {
        return getComponent(frame.getContentPane(), name);
    }
}
