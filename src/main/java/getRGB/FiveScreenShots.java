package getRGB;

import actions.SleepTime;
import logic.screen_shot.ScreenShot;
import main.Prop;
import storage_image.StorageImageFile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FiveScreenShots {
    public static final int COUNT_SCREENSHOTS = 5;
    public static final String PATH_TO_SAVE = Prop.ROOT_DIR + "\\getRGB\\newImage\\";
    static StorageImageFile storageImageFile = StorageImageFile.instance();

    public static void main(String[] args) throws AWTException, InterruptedException {
        SleepTime.sleep(5000);
        for (int i = 0; i < COUNT_SCREENSHOTS; i++) {
            BufferedImage image = Prop.context.getBean(ScreenShot.class).pop();
           storageImageFile.save(image, PATH_TO_SAVE + "fragment" + i + ".png");
           SleepTime.sleep(1000);
        }
    }
}
