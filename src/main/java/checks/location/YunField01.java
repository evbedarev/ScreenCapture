package checks.location;

import logic.screen_shot.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

//Change to propery file!!
public class YunField01 extends Location {

    public YunField01() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1481,47, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1552,50, -6280000, -1200000};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1481,47, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1552,50, -6280000, -1200000};
        return checkRgbLocation(screenShot);
    }
}
