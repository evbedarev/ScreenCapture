package checks.location;

import logic.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class YunField07 implements VerifyMap {
    public static final int[] PORTAL_RGB = new int[] {1572,76, -6280000, -1200000};
    public static final int[] PORTAL_RGB_1 = new int[] {1478,144, -6280000, -1200000};
    Capture capture;


    public YunField07() throws AWTException {
        capture = Capture.instance();
    }
    @Override
    public boolean onDesiredLocation() {
        BufferedImage image = capture.takeScreenShot();
        int rgb = image.getRGB(PORTAL_RGB[0], PORTAL_RGB[1]);
        int rgb1 = image.getRGB(PORTAL_RGB_1[0], PORTAL_RGB_1[1]);
        return (rgb > PORTAL_RGB[2] && rgb < PORTAL_RGB[3]) ||
                (rgb1 > PORTAL_RGB[2] && rgb1 < PORTAL_RGB[3]);
    }
}
