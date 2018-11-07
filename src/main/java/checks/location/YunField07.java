package checks.location;

import logic.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class YunField07 extends Location {

    public YunField07() throws AWTException {
        capture = Capture.instance();
    }
    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1572,76, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1478,144, -6280000, -1200000};
        return checkRgbLocation();
    }
}
