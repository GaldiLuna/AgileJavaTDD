package util;

import javax.swing.*;
import java.awt.*;

public class ImageUtil {
    public static Image create(String path) {
        java.net.URL imageURL = ImageUtil.class.getResource(path);
        if (imageURL == null)
            return null;
        return new ImageIcon(imageURL).getImage();
    }
}
