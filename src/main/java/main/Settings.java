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
