package find_image;
import logic.screen_shot.Capture;
import main.Prop;
import static java.lang.Math.abs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class FindPixels implements FindPixelsInImage {
//    final Logger logger = Logger.getLogger(this.getClass());
    static Capture capture;
    BufferedImage screenShot;
    static {
        try {
            capture = Capture.instance();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ищет изображение по пикселям на картинке
     * @param screenShot - скриншот
     * @param mainRgb - основной цвет пикселя, ищет его в первую очередь.
     * @param subImgCoord - область, в которой производится поиск после нахождения основн-
     *                    ого пикселя.
     * @param ancillaryRgb - массив дополнительных цветов, которые ищутся в области subImgCoord.
     *                     Если все пикслеи присутствуют возвращает координаты основного пикселя.
     * @return
     */
    @Override
    public Optional<int[]> findPixelsInImage (
            BufferedImage screenShot,
            int mainRgb,
            int[] subImgCoord,
            int[] ancillaryRgb) {

        for (int y = 0; y < screenShot.getHeight(); y++) {
            for (int x = 0; x < screenShot.getWidth(); x++) {

                if (x > Prop.EXCLUDE_X_LEFT
                        && x < Prop.EXCLUDE_X_RIGHT
                        && y > Prop.EXCLUDE_Y_UP
                        && y < Prop.EXCLUDE_Y_DOWN ) {
                    continue;
                }

                if (screenShot.getRGB(x, y) == mainRgb) {
//                    logger.debug( " coordinates is " + x + ',' + y );

                    if (getSubImage(screenShot, new int[] {x, y}, subImgCoord, ancillaryRgb)) {
//                        logger.debug("Find rgb " + mainRgb + " coordinates is " + x + ',' + y);
                        return Optional.of(new int[] {x, y});
                    }
                }
            }
        }
        return Optional.empty();
    }

    public Optional<int[]> findPixels3Times(  int mainRgb,
                                              int[] subImgCoord,
                                              int[] ancillaryRgb) throws AWTException {
        Optional<int[]> xy = Optional.empty();
        for (int i=0; i < 2; i++) {
            screenShot = capture.takeScreenShot();
            xy = findPixelsInImageInArea(
                    screenShot,
                    mainRgb,
                    subImgCoord,
                    ancillaryRgb,
                    new int[] {550, 1000, 200, 700}
                    );
            if (xy.isPresent()) return xy;

            xy = findPixelsInImageExcludeArea(
                    screenShot,
                    mainRgb,
                    subImgCoord,
                    ancillaryRgb,
                    new int[] {550, 1000, 200, 700}
                    );
            if (xy.isPresent()) return xy;
        }
        return xy;
    }

    public Optional<int[]> findPixelsNear3Times(  int mainRgb,
                                              int[] subImgCoord,
                                              int[] ancillaryRgb) throws AWTException {
        Optional<int[]> xy = Optional.empty();
        for (int i=0; i < 2; i++) {
            screenShot = capture.takeScreenShot();
            xy = findPixelsInImageInArea(
                    screenShot,
                    mainRgb,
                    subImgCoord,
                    ancillaryRgb,
                    new int[] {600, 1000, 300, 600}
            );
            if (xy.isPresent()) return xy;
        }
        return xy;
    }



    /**
     * Ищет изображение по пикселям на картинке в определенной области.
     * @param screenShot - скриншот
     * @param mainRgb - основной цвет пикселя, ищет его в первую очередь.
     * @param subImgCoord - область, в которой производится поиск после нахождения основн-
     *                          ого пикселя.
     * @param ancillaryRgb - массив дополнительных цветов, которые ищутся в области subImgCoord.
     *      *                     Если все пикслеи присутствуют возвращает координаты основного пикселя.
     * @param coordsArea - массив из 4х элементов,
     *                   coordsArea[0] - левая точка по Х
     *                   coordsArea[1] - правая точка по Х
     *                   coordsArea[2] - верхняя точка по Y
     *                   coordsArea[3] - нижняя точка по Y
     * @return
     */

    @Override
    public Optional<int[]> findPixelsInImageInArea (
            BufferedImage screenShot,
            int mainRgb,
            int[] subImgCoord,
            int[] ancillaryRgb,
            int[] coordsArea) {
        for (int y = 0; y < screenShot.getHeight(); y++) {
            for (int x = 0; x < screenShot.getWidth(); x++) {

                if (x < coordsArea[0]
                        || x > coordsArea[1]
                        || y < coordsArea[2]
                        || y > coordsArea[3] ) {
                    continue;
                }

                if (screenShot.getRGB(x, y) == mainRgb) {
                    if (getSubImage(screenShot, new int[] {x, y}, subImgCoord, ancillaryRgb)) {
                        return Optional.of(new int[] {x, y});
                    }
                }
            }
        }
        return Optional.empty();
    }


    public Optional<int[]> findPixelsArround3Times(int mainRgb,
                                                 int[] subImgCoord,
                                                 int[] ancillaryRgb,
                                                 int[] coordsArea) throws AWTException {
        Optional<int[]> xy = Optional.empty();
        for (int i=0; i < 4; i++) {
            screenShot = capture.takeScreenShot();
            xy = findPixelsInImageInArea(screenShot,
                                        mainRgb,
                                        subImgCoord,
                                        ancillaryRgb,
                                        coordsArea);
            if (xy.isPresent()) return xy;
        }
        return xy;
    }

    /**
     * Определяет ближайшие координаты к центру экрана по оси Х
     * @param listCoords - список всех найденых объектов на экране
     * @return
     */
    private int[] findNearestFragment(List<int[]> listCoords) {
        int screenCenter = Prop.SCREEN_WIDTH/2;
        Collections.sort(listCoords, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(abs(o1[0] - screenCenter), abs(o2[0] - screenCenter));
            }
        });
//        logger.debug("return coords of fragment: x=" + listCoords.get(0)[0] + " y=" + listCoords.get(0)[1]);
        return listCoords.get(0);
    }

    /**
     * @param screenshot - снимок экрана
     * @param xy - 2 координаты точки вокруг которой надо вырезать изображение
     * @param subImgCoord - половина ширины и высоты вырезаемого участка.
     * @param ancillaryRgb - массив второстепенных ргб цветов.
     * @return
     */
    public boolean getSubImage(
            BufferedImage screenshot,
            int xy[],
            int[] subImgCoord,
            int[] ancillaryRgb)
    {
        int x = xy[0];
        int y = xy[1];
        int x_up_left = x - subImgCoord[0];
        int y_up_left = y - subImgCoord[1];

        if (x_up_left < 0 || x_up_left + subImgCoord[0]*2 > 1600) {
            return false;
        }
        if (y_up_left < 0 || y_up_left + subImgCoord[1]*2 > 900) {
            return false;
        }

        int width = subImgCoord[0]*2;
        int height = subImgCoord[1]*2;
        BufferedImage image = screenshot.getSubimage(x_up_left,y_up_left,width,height);

        for (int rgb:ancillaryRgb) {
            if (!findPixel(image, rgb).isPresent()) {
                return false;
            }
        }
//        logger.debug("Find rgb " + ancillaryRgb + " in area x_up=" + x_up_left +
//                "y_up_left " + y_up_left + " width " + width + " height " + height);
        return true;
    }

    public Optional<int[]> findPixel(BufferedImage screenShot, int ancillaryRgb) {
//        logger.debug("Searching rgb " + ancillaryRgb);
        for (int y = 0; y < screenShot.getHeight(); y++) {
            for (int x = 0; x < screenShot.getWidth(); x++) {
                if (screenShot.getRGB(x, y) == ancillaryRgb) {
//                    logger.debug("Find ancillary rgb " + ancillaryRgb + " coordinates is " + x + ',' + y);
                    return Optional.of(new int[] {x, y}) ;
                }
            }
        }
        return Optional.empty();
    }


    /**
     * Ищет изображение по пикселям на картинке в определенной области.
     * @param screenShot - скриншот
     * @param mainRgb - основной цвет пикселя, ищет его в первую очередь.
     * @param subImgCoord - область, в которой производится поиск после нахождения основн-
     *                          ого пикселя.
     * @param ancillaryRgb - массив дополнительных цветов, которые ищутся в области subImgCoord.
     *      *                     Если все пикслеи присутствуют возвращает координаты основного пикселя.
     * @param coordsArea - массив из 4х элементов,
     *                   coordsArea[0] - левая точка по Х
     *                   coordsArea[1] - правая точка по Х
     *                   coordsArea[2] - верхняя точка по Y
     *                   coordsArea[3] - нижняя точка по Y
     * @return
     */

    public Optional<Integer> findCountsFragmentsInImageInArea (
            BufferedImage screenShot,
            int mainRgb,
            int[] subImgCoord,
            int[] ancillaryRgb,
            int[] coordsArea) {
        List<int[]> listCoords = new ArrayList<>();
        for (int y = 0; y < screenShot.getHeight(); y++) {
            for (int x = 0; x < screenShot.getWidth(); x++) {

                if (x < coordsArea[0]
                        || x > coordsArea[1]
                        || y < coordsArea[2]
                        || y > coordsArea[3] ) {
                    continue;
                }

                if (screenShot.getRGB(x, y) == mainRgb) {
//                    logger.debug( " coordinates is " + x + ',' + y );

                    if (getSubImage(screenShot, new int[] {x, y}, subImgCoord, ancillaryRgb)) {
//                        logger.debug("Find rgb " + mainRgb + " coordinates is " + x + ',' + y);
                        listCoords.add(new int[] {x, y});
                    }
                }
            }
        }
        if (listCoords.size() > 0) {
//            logger.debug("Size of listCoords is: " + listCoords.size());
            return Optional.of(listCoords.size());
        }
        return Optional.empty();
    }

    /**
     * Ищет изображение по пикселям на картинке исключая площадь
     * @param screenShot - скриншот
     * @param mainRgb - основной цвет пикселя, ищет его в первую очередь.
     * @param subImgCoord - область, в которой производится поиск после нахождения основн-
     *                    ого пикселя.
     * @param ancillaryRgb - массив дополнительных цветов, которые ищутся в области subImgCoord.
     *                     Если все пикслеи присутствуют возвращает координаты основного пикселя.
     * @return
     */
    public Optional<int[]> findPixelsInImageExcludeArea (
            BufferedImage screenShot,
            int mainRgb,
            int[] subImgCoord,
            int[] ancillaryRgb,
            int[] excludeArea) {
        List<int[]> listCoords = new ArrayList<>();
        for (int y = 0; y < screenShot.getHeight(); y++) {
            for (int x = 0; x < screenShot.getWidth(); x++) {

                if (x > excludeArea[0]
                        && x < excludeArea[1]
                        && y > excludeArea[2]
                        && y < excludeArea[3] ) {
                    continue;
                }

                /**
                 * Исключаем зону статусов под миникартой
                 */
                if (x > 1555 && x < 1587
                        && y > 193 && y < 370 ) {
                    continue;
                }


                if (screenShot.getRGB(x, y) == mainRgb) {
//                    logger.debug( " coordinates is " + x + ',' + y );

                    if (getSubImage(screenShot, new int[] {x, y}, subImgCoord, ancillaryRgb)) {
//                        logger.debug("Find rgb " + mainRgb + " coordinates is " + x + ',' + y);
                        return Optional.of(new int[] {x, y});
                    }
                }
            }
        }
        return Optional.empty();
    }
}
