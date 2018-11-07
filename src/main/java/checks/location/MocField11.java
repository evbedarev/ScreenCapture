package checks.location;

import logic.Capture;

import java.awt.*;

//Change to propery file!!
public class MocField11 extends Location {

    public MocField11() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1520,49, -14000000, -500000};
        PORTAL_RGB_1 = new int[] {1584,106, -14000000, -700000};
        return checkRgbLocation();
    }
}
