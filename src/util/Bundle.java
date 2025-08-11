package util;

import java.util.ResourceBundle;

public class Bundle {
    public static final String DEFAULT_BASE_NAME = "Messages";
    private static String baseName = DEFAULT_BASE_NAME;
    private static ResourceBundle bundle;

    static String getName() {
        return baseName;
    }

    static void setName(String name) {
        baseName = name;
        bundle = null;
    }

    public static String get(String key) {
        if (bundle == null)
            loadBundle();
        return (String)bundle.getString(key);
    }

    private static void loadBundle() {
        bundle = ResourceBundle.getBundle(".util." + getName());
    }
}
