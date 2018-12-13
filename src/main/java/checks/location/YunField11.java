package checks.location;

import logic.Capture;

import java.awt.*;

import java.awt.image.BufferedImage;

public class YunField11 extends Location {

    public YunField11() throws AWTException {
        capture = Capture.instance();
    }
    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1489,50, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1577,54, -6280000, -1200000};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1489,50, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1577,54, -6280000, -1200000};
        return checkRgbLocation(screenShot);
    }
}
