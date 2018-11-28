package checks.location;

import logic.Capture;

import java.awt.*;

public class CmdFiled04 extends Location {

    public CmdFiled04() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1582,78, -6280000, -300000};
        PORTAL_RGB_1 = new int[] {1464,80, -6280000, -200000};
        return checkRgbLocation();
    }
}
