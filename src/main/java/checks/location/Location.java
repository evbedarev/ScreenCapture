package checks.location;

import logger.LoggerSingle;
import logic.Capture;

import java.awt.image.BufferedImage;

public abstract class Location implements VerifyMap {
    protected Capture capture;
    protected static int[] PORTAL_RGB;
    protected static int[] PORTAL_RGB_1;


    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */
    boolean checkRgbLocation() {
        boolean findRGB1, findRGB2;
        BufferedImage image = capture.takeScreenShot();
        int rgb = image.getRGB(PORTAL_RGB[0], PORTAL_RGB[1]);
        int rgb1 = image.getRGB(PORTAL_RGB_1[0], PORTAL_RGB_1[1]);
        findRGB1 = rgb > PORTAL_RGB[2] && rgb < PORTAL_RGB[3];
        findRGB2 = rgb1 > PORTAL_RGB_1[2] && rgb1 < PORTAL_RGB_1[3];

        if (findRGB1 || findRGB2) {
            return true;
        } else {
            showRGB(PORTAL_RGB, image);
            showRGB(PORTAL_RGB_1, image);
            return false;
        }
    }

    private void showRGB(int[] xy, BufferedImage bufferedImage) {
        LoggerSingle.logInfo(this.toString(), "For coords {"+ xy[0] + "," + xy[1]+"} = "
                + bufferedImage.getRGB(xy[0], xy[1]));
    }

    @Override
    public abstract boolean onDesiredLocation();
}
