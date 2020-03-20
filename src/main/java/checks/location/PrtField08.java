package checks.location;

import java.awt.image.BufferedImage;

public class PrtField08 extends Location {

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1513,49, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1477,163, -6280000, -1200000};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1513,49, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1477,163, -6280000, -1200000};
        return checkRgbLocation(screenShot);
    }
}
