package find_image;

import main.Prop;

import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * Ищет искомый фрагмент в скриншоте.
 */

public class FindImageHard implements FindImage {
    private static FindImageHard instance;

    private FindImageHard() {
    }

    static public FindImageHard getInstance() {
        if (instance == null) {
            instance = new FindImageHard();
        }
        return instance;
    }

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

    /**
     * Ищет фрагмент в скриншоте, исключая заданную область.
     * @param screenShot - скриншот.
     * @param fragment - фрагмент изображения.
     * @return
     */
    @Override
    public Optional<int[]> findImageExcludeArea(BufferedImage screenShot,
                                     BufferedImage fragment) {

        int x_left = Prop.EXCLUDE_X_LEFT;
        int x_rigth = Prop.EXCLUDE_X_RIGHT;
        int y_up = Prop.EXCLUDE_Y_UP;
        int y_down = Prop.EXCLUDE_Y_DOWN;

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
        int xLeft = xy[0];
        int xRigth = xy[1];
        int y_up = xy[2];
        int y_down = xy[3];

        for (int y = 0; y < screenShot.getHeight() - fragment.getHeight(); y++) {
            __columnspan: for (int x=0; x < screenShot.getWidth() - fragment.getWidth(); x++) {

                if (x > xLeft && x < xRigth && y > y_up && y < y_down ) {
                    continue;
                }

                //Исключаем персонажа из поиска!! Какая то хрень
                if (x > 810 && x < 871 && y > 365 && y < 496) {
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
        //change
        for (int y = y_up; y < y_down - fragment.getHeight(); y++) {
            __columnspan: for (int x = x_left; x < x_rigth - fragment.getWidth(); x++) {
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
    public Optional<int[]> findPixel(BufferedImage screenShot, int[] rgb) {
        for (int i:rgb) {
            for (int y = 0; y < screenShot.getHeight(); y++) {
                for (int x = 0; x < screenShot.getWidth(); x++) {
                    if (screenShot.getRGB(x, y) == i) {
                        return Optional.of(new int[] {x, y});
                    }
                }
            }
        }
        return Optional.empty();
    }

    public  Optional<int[]> findImage2Pixels(BufferedImage screenShot, int[] rgb) {
        for (int y = 0; y < screenShot.getHeight(); y++) {
            for (int x = 0; x < screenShot.getWidth(); x++) {
                if (screenShot.getRGB(x, y) == rgb[0]) {
                    System.out.println("Find first rgb" + rgb[0] + " at " + x + "," + y );
                    System.out.println("Area is " + (x-15) + ","+ (x+15) + ","+ (y-19) + ","+ (y+19));
                    return findImageInArea(screenShot, rgb[1], new int[] {x-15,x+15,y-19,y+19});
                }
            }
        }
        return Optional.empty();
    }

    public Optional<int[]> findImageInArea(BufferedImage screenShot, int rgb, int[] xy) {

        int x_left = xy[0];
        int x_rigth = xy[1];
        int y_up = xy[2];
        int y_down = xy[3];

        for (int y = 0; y < screenShot.getHeight(); y++) {
            for (int x = 0; x < screenShot.getWidth(); x++) {
                if (x < x_left || x > x_rigth || y < y_up || y > y_down ) {
                    continue;
                }
                if (screenShot.getRGB(x, y) == rgb) {
                    System.out.println("Find secont rgb " + rgb + " at " + x + "," + y);
                    return Optional.of(new int[]{x, y});
                }
            }
        }
        return Optional.empty();
    }
}
