package find_image;

import main.Settings;

import java.awt.image.BufferedImage;
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

    @Override
    public Optional<int[]> findImageExcludeArea(BufferedImage screenShot,
                                     BufferedImage fragment) {

        Settings settings = Settings.instance();
        int x_left = settings.x_left;
        int x_rigth = settings.x_right;
        int y_up = settings.y_up;
        int y_down = settings.y_down;


        for (int y = 0; y < screenShot.getHeight() - fragment.getHeight(); y++) {
            __columnspan: for (int x=0; x < screenShot.getWidth() - fragment.getWidth(); x++) {

                if (x > x_left && x < x_rigth && y > y_up && y < y_down ) {
                    continue;
                }

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

    /**
     *
     * @param screenShot - bufferedImage
     * @param fragment -fragment to find
     * @param xy - array of 4 values!!
     * @return
     */
    @Override
    public Optional<int[]> findImageExcludeArea(BufferedImage screenShot,
                                                BufferedImage fragment, int[] xy) {

        int x_left = xy[0];
        int x_rigth = xy[1];
        int y_up = xy[2];
        int y_down = xy[3];


        for (int y = 0; y < screenShot.getHeight() - fragment.getHeight(); y++) {
            __columnspan: for (int x=0; x < screenShot.getWidth() - fragment.getWidth(); x++) {

                if (x > x_left && x < x_rigth && y > y_up && y < y_down ) {
                    continue;
                }

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

    @Override
    public Optional<int[]> findImageInArea(BufferedImage screenShot, BufferedImage fragment, int[] xy) {

        int x_left = xy[0];
        int x_rigth = xy[1];
        int y_up = xy[2];
        int y_down = xy[3];

        for (int y = 0; y < screenShot.getHeight() - fragment.getHeight(); y++) {
            __columnspan: for (int x=0; x < screenShot.getWidth() - fragment.getWidth(); x++) {

                if (x < x_left && x > x_rigth && y < y_up && y > y_down ) {
                    continue;
                }

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
