package checks.location;

import logic.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

//Change to propery file!!
public class EinDun01 extends Location {

    public EinDun01() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1571,75, -15724529, -13000000};
        PORTAL_RGB_1 = new int[] {1468,165, -6280000, -1200000};
        return checkRgbLocation();
    }
}
