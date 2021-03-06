package checks.location;

import logic.Capture;

import java.awt.*;

//Change to propery file!!
public class GefField08 extends Location {

    public GefField08() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1531,164, -6280000, -700000};
        PORTAL_RGB_1 = new int[] {1527,47, -6280000, -900000};
        return checkRgbLocation();
    }
}
