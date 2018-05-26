package getRGB;

import logic.Capture;
import storage_image.StorageImageFile;
import sun.awt.geom.AreaOp;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FiveScreenShots {
    public static final int COUNT_SCREENSHOTS = 5;
    public static final String PATH_TO_SAVE = "";
    static StorageImageFile storageImageFile = new StorageImageFile();

    public static void main(String[] args) throws AWTException, InterruptedException {
        Capture capture = Capture.instance();
        BufferedImage image = capture.takeScreenShot();
        for (int i = 0; i < COUNT_SCREENSHOTS; i++) {
           storageImageFile.save(image, PATH_TO_SAVE);
           Thread.sleep(1000);
        }
    }
}
