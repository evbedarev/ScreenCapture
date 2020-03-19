package checks.location;

import logic.screen_shot.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class YunField12 extends Location {

    public YunField12() throws AWTException {
        capture = Capture.instance();
    }

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1521,50, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1466,96, -6280000, -1200000};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1521,50, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1466,96, -6280000, -1200000};
        return checkRgbLocation(screenShot);
    }
}
