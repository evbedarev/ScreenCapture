package find_image;

import java.awt.image.BufferedImage;
import java.util.Optional;

public interface FindPixelsInImage {
    Optional<int[]> findPixelsInImage (
            BufferedImage screenShot,
            int mainRgb,
            int[] subImgCoord,
            int[] ancillaryRgb);
}
