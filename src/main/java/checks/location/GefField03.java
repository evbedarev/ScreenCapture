package checks.location;

import logic.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GefField03 extends Location {

    public GefField03() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1582,144, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1480,46, -6280000, -1200000};
        return checkRgbLocation();
    }
}
