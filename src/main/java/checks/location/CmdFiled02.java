package checks.location;

import logic.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CmdFiled02 extends Location {

    public CmdFiled02() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1575,137, -6280000, -300000};
        PORTAL_RGB_1 = new int[] {1530,48, -6280000, -200000};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1575,137, -6280000, -300000};
        PORTAL_RGB_1 = new int[] {1530,48, -6280000, -200000};
        return checkRgbLocation(screenShot);
    }
}
