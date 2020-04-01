package checks.location;

import java.awt.image.BufferedImage;

//Change to propery file!!
public class CmdField01 extends Location {

    /**
     * Проверяет находится ли RGB пикселя в нужном промежутке значений.
     * PORTAL_RGB - array {x,y, minimal value RGB, max value RGB}
     * @return boolean
     */

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1515,51, -6000000, -1000000};
        PORTAL_RGB_1 = new int[] {1576,86, -6000000, -1000000};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1515,51, -6000000, -1000000};
        PORTAL_RGB_1 = new int[] {1576,86, -6000000, -1000000};
        return checkRgbLocation(screenShot);
    }
}
