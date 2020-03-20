package checks.location;

import java.awt.image.BufferedImage;

//Change to propery file!!
public class YunField08 extends Location {

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1551,46, -6280000, -700000};
        PORTAL_RGB_1 = new int[] {1469,151, -6280000, -900000};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1551,46, -6280000, -700000};
        PORTAL_RGB_1 = new int[] {1469,151, -6280000, -900000};
        return checkRgbLocation(screenShot);
    }
}
