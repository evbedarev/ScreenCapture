package checks.location;


import java.awt.image.BufferedImage;

public class YunField11 extends Location {

    @Override
    public boolean onDesiredLocation() {
        PORTAL_RGB = new int[] {1489,50, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1577,54, -6280000, -1200000};
        return checkRgbLocation();
    }

    @Override
    public boolean onDesiredLocation(BufferedImage screenShot) {
        PORTAL_RGB = new int[] {1489,50, -6280000, -1200000};
        PORTAL_RGB_1 = new int[] {1577,54, -6280000, -1200000};
        return checkRgbLocation(screenShot);
    }
}
