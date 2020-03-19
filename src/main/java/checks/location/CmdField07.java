package checks.location;

import logic.screen_shot.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

//Change to propery file!!
public class CmdField07 extends Location {

    public CmdField07() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1547,141, -10265990, -10265988};
        PORTAL_RGB_1 = new int[] {1502,126, -1088703, -1088701};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1547,141, -10265990, -10265988};
        PORTAL_RGB_1 = new int[] {1502,126, -1088703, -1088701};
        return checkRgbLocation(screenShot);
    }
}
