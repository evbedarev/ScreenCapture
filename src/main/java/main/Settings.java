package main;

import java.io.*;
import java.util.Properties;

public class Settings {
    public static final String PATH_TO_PROP = "./src/main/resources/ap.properties";
    static private Settings instance;
    public int screenWidth;
    public int screenHeight;
    public String rootDir;

    public int rgbHp;
    public int xHp;
    public int yHp;
    public int rgbHpHeal;
    public int xHpHeal;
    public int yHpHeal;

    public int x_left;
    public int x_right;
    public int y_up;
    public int y_down;

    public Settings() {
        try (InputStream file = new FileInputStream(new File(PATH_TO_PROP))) {
            Properties properties = new Properties();
            properties.load(file);

            screenWidth = Integer.parseInt(properties.getProperty("SCREEN_WIDTH"));
            screenHeight = Integer.parseInt(properties.getProperty("SCREEN_HEIGHT"));
            rootDir = properties.getProperty("ROOT_DIR");

            rgbHp = Integer.parseInt(properties.getProperty("RGB_HP"));
            xHp = Integer.parseInt(properties.getProperty("X_HP"));
            yHp = Integer.parseInt(properties.getProperty("Y_HP"));

            rgbHpHeal = Integer.parseInt(properties.getProperty("RGB_HP_HEAL"));
            xHpHeal = Integer.parseInt(properties.getProperty("X_HP_HEAL"));
            yHpHeal = Integer.parseInt(properties.getProperty("Y_HP_HEAL"));

            x_left = Integer.parseInt(properties.getProperty("EXCLUDE_X_LEFT"));
            x_right = Integer.parseInt(properties.getProperty("EXCLUDE_X_RIGHT"));
            y_up = Integer.parseInt(properties.getProperty("EXCLUDE_Y_UP"));
            y_down = Integer.parseInt(properties.getProperty("EXCLUDE_Y_DOWN"));

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    static public Settings instance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
