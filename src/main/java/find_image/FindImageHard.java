package find_image;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * Ищет искомый фрагмент в скриншоте.
 */

public class FindImageHard implements FindImage {

    /**
     * Find fragment of image in screenshot slowly.
     * @param screenShot - screenshot (png)
     * @param fragment - required fragment (png)
     * @return - int[] {x,y} or int[0]
     */
    @Override
    public Optional<int[]> findImage(BufferedImage screenShot,
                                     BufferedImage fragment) {

        for (int y = 0; y < screenShot.getHeight() - fragment.getHeight(); y++) {
            __columnspan: for (int x=0; x < screenShot.getWidth() - fragment.getWidth(); x++) {
                if (screenShot.getRGB(x, y) != fragment.getRGB(0,0))
                    continue;
                for (int yy = 0; yy < fragment.getHeight(); yy++) {
                    for (int xx = 0; xx < fragment.getWidth(); xx++) {
                        if (screenShot.getRGB(x + xx, y + yy) != fragment.getRGB(xx , yy))
                            continue __columnspan;
                    }
                }
                return Optional.of(new int[] {x, y});

            }
        }
        return Optional.empty();
    }
}
