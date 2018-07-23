package checks.location;

import logic.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

//Change to propery file!!
public class Aldebaran implements VerifyMap {
    public static final int[] PORTAL_RGB = new int[] {1482,58, -7050398, -7050396};
    public static final int[] PORTAL_RGB_1 = new int[] {1492,76, -10265990, -10265988};
    Capture capture;

    public Aldebaran() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        BufferedImage image = capture.takeScreenShot();
        int rgb = image.getRGB(PORTAL_RGB[0], PORTAL_RGB[1]);
        int rgb1 = image.getRGB(PORTAL_RGB_1[0], PORTAL_RGB_1[1]);
        return (rgb > PORTAL_RGB[2] && rgb < PORTAL_RGB[3]) ||
                (rgb1 > PORTAL_RGB[2] && rgb1 < PORTAL_RGB[3]);
    }
}
