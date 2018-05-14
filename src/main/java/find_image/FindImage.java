package find_image;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Optional;

public interface FindImage {
    Optional<int[]> findImage (BufferedImage screenShot, BufferedImage fragment);
    Optional<int[]> findImageExcludeArea (BufferedImage screenShot, BufferedImage fragment);
    Optional<int[]> findImageExcludeArea (BufferedImage screenShot, BufferedImage fragment,
                                          int[] xy);
    Optional<int[]> findImageInArea (BufferedImage screenShot, BufferedImage fragment, int[] xy);
    Optional<int[]> findPixel (BufferedImage screenShot, int[] rgb);

}
