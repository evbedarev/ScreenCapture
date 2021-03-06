package checks.location;

import logic.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;

//Change to propery file!!
public class Aldebaran extends Location {


    public Aldebaran() throws AWTException {
        capture = Capture.instance();
    }

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1482,58, -7050398, -7050396};
        PORTAL_RGB_1 = new int[] {1492,76, -10265990, -10265988};
        return checkRgbLocation();
    }
}
