package logic;

import main.Settings;
import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Singleton
 * Класс для создания скриншота экрана
 */
public class Capture {
    static private Capture instance;
    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;
    private final String ROOT_DIR;
    private StorageImage storageImage = new StorageImageFile();
    private Robot robot;

    public Capture() throws AWTException {
        Settings settings = Settings.instance();
        robot = new Robot();
        SCREEN_WIDTH = settings.screenWidth;
        SCREEN_HEIGHT = settings.screenHeight;
        ROOT_DIR = settings.rootDir;
    }

    public void takeScreenShotToFile(String fileName) {
        Rectangle rectangle = new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT);
        BufferedImage image = robot.createScreenCapture(rectangle);
        storageImage.save(image, ROOT_DIR + fileName + ".png");
    }

    public BufferedImage takeScreenShot() {
        Rectangle rectangle = new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT);
        return robot.createScreenCapture(rectangle);
    }

    static public Capture instance() throws AWTException {
        if (instance == null) {
            instance = new Capture();
        }
        return  instance;
    }


}
