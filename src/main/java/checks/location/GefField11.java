package checks.location;

import logic.Capture;

import java.awt.*;

public class GefField11 extends Location {

    public GefField11() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1492,46, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1581,75, -6280000, -1200000};
        return checkRgbLocation();
    }
}
