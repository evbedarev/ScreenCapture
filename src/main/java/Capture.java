import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Capture {
    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;
    private static final String ROOT_DIR = "./src/main/resources/";
    private StorageImage storageImage = new StorageImageFile();


    public void takeScreenShot(String fileName) throws AWTException {
        Robot robot = new Robot();
        Rectangle rectangle = new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT);
        BufferedImage image = robot.createScreenCapture(rectangle);
        storageImage.save(image, ROOT_DIR + fileName + ".png");
    }
}
