package checks.location;

import logic.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PayField07 extends Location {

    public PayField07() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1582,76, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1466,103, -6280000, -1200000};
        return checkRgbLocation();
    }
}
