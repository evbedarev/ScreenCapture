package checks.location;

import java.awt.image.BufferedImage;

public class PrtField03 extends Location {

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1464,72, -6280000, -300000};
        PORTAL_RGB_1 = new int[] {1579,71, -6280000, -200000};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1464,72, -6280000, -300000};
        PORTAL_RGB_1 = new int[] {1579,71, -6280000, -200000};
        return checkRgbLocation(screenShot);
    }
}
