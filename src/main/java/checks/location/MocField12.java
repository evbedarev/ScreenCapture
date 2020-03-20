package checks.location;

import java.awt.image.BufferedImage;

//Change to propery file!!
public class MocField12 extends Location {

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1525,44, -14000000, -500000};
        PORTAL_RGB_1 = new int[] {1511,160, -14000000, -700000};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1525,44, -14000000, -500000};
        PORTAL_RGB_1 = new int[] {1511,160, -14000000, -700000};
        return checkRgbLocation(screenShot);
    }
}
