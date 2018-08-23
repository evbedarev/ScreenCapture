package checks.location;

import logger.LoggerSingle;
import logic.Capture;
import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.image.BufferedImage;

public abstract class Location implements VerifyMap {
    protected Capture capture;
    protected static int[] PORTAL_RGB;
    protected static int[] PORTAL_RGB_1;

    boolean checkRgbLocation() {
        boolean findRGB;
        BufferedImage image = capture.takeScreenShot();
        int rgb = image.getRGB(PORTAL_RGB[0], PORTAL_RGB[1]);
        int rgb1 = image.getRGB(PORTAL_RGB_1[0], PORTAL_RGB_1[1]);
        findRGB = (rgb > PORTAL_RGB[2] && rgb < PORTAL_RGB[3]) ||
                (rgb1 > PORTAL_RGB[2] && rgb1 < PORTAL_RGB[3]);
        if (findRGB) {
            return findRGB;
        } else {
            showRGB(PORTAL_RGB, image);
            showRGB(PORTAL_RGB_1, image);
            return findRGB;
        }
    }

    protected void showRGB(int[] xy, BufferedImage bufferedImage) {
        LoggerSingle.logInfo(this.toString(), "For coords {"+ xy[0] + "," + xy[1]+"} = "
                + bufferedImage.getRGB(xy[0], xy[1]));
    }

    @Override
    public abstract boolean onDesiredLocation();
}
