package getRGB;

import logic.Capture;
import main.Prop;
import storage_image.StorageImageFile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FiveScreenShots {
    public static final int COUNT_SCREENSHOTS = 5;
    public static final String PATH_TO_SAVE = Prop.ROOT_DIR + "\\getRGB\\newImage\\";
    static StorageImageFile storageImageFile = new StorageImageFile();

    public static void main(String[] args) throws AWTException, InterruptedException {
        Capture capture = Capture.instance();
        Thread.sleep(5000);
        for (int i = 0; i < COUNT_SCREENSHOTS; i++) {
            BufferedImage image = capture.takeScreenShot();
           storageImageFile.save(image, PATH_TO_SAVE + "fragment" + i + ".png");
           Thread.sleep(1000);
        }
    }
}
