package checks.location;

import logic.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

//Change to propery file!!
public class Sphinx03 extends Location {

    public Sphinx03() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1493,46, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1572,139, -6280000, -1200000};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1493,46, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1572,139, -6280000, -1200000};
        return checkRgbLocation(screenShot);
    }
}
