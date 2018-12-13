package checks.location;

import java.awt.image.BufferedImage;

public interface VerifyMap {
    boolean onDesiredLocation();
    boolean onDesiredLocation(BufferedImage screenShot);
}
