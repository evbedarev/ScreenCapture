package checks.location;

import logic.screen_shot.Capture;

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

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1572,76, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1478,144, -6280000, -1200000};
        return checkRgbLocation(screenShot);
    }
}
