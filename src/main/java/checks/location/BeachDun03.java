package checks.location;

import logic.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

//Change to propery file!!
public class BeachDun03 extends Location {

    public BeachDun03() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1464,55, -6280000, -700000};
        PORTAL_RGB_1 = new int[] {1583,144, -6280000, -900000};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1464,55, -6280000, -700000};
        PORTAL_RGB_1 = new int[] {1583,144, -6280000, -900000};
        return checkRgbLocation(screenShot);
    }
}
