package find_image;
import main.Prop;
import org.apache.log4j.Logger;
import static java.lang.Math.abs;
import java.awt.image.BufferedImage;
import java.util.*;

public class FindPixels implements FindPixelsInImage {
    final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Optional<int[]> findPixelsInImage(
            BufferedImage screenShot,
            int mainRgb,
            int[] subImgCoord,
            int[] ancillaryRgb) {
        List<int[]> listCoords = new ArrayList<>();
        for (int y = 0; y < screenShot.getHeight(); y++) {
            for (int x = 0; x < screenShot.getWidth(); x++) {

                if (x > Prop.EXCLUDE_X_LEFT
                        && x < Prop.EXCLUDE_X_RIGHT
                        && y > Prop.EXCLUDE_Y_UP
                        && y < Prop.EXCLUDE_Y_DOWN ) {
                    continue;
                }

                if (screenShot.getRGB(x, y) == mainRgb) {
                    logger.debug( " coordinates is " + x + ',' + y );

                    if (getSubImage(screenShot, new int[] {x, y}, subImgCoord, ancillaryRgb)) {
                        logger.debug("Find rgb " + mainRgb + " coordinates is " + x + ',' + y);
                        listCoords.add(new int[] {x, y});
                    }
                }
            }
        }
        if (listCoords.size() > 0) {
            logger.debug("Size of listCoords is: " + listCoords.size());
            return Optional.of(findNearestFragment(listCoords));
        }
        return Optional.empty();
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
        logger.debug("return coords of fragment: x=" + listCoords.get(0)[0] + " y=" + listCoords.get(0)[1]);
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
        logger.debug("Find rgb " + ancillaryRgb + " in area x_up=" + x_up_left +
                "y_up_left " + y_up_left + " width " + width + " height " + height);
        return true;
    }

    public Optional<int[]> findPixel(BufferedImage screenShot, int ancillaryRgb) {
        logger.debug("Searching rgb " + ancillaryRgb);
        for (int y = 0; y < screenShot.getHeight(); y++) {
            for (int x = 0; x < screenShot.getWidth(); x++) {
                if (screenShot.getRGB(x, y) == ancillaryRgb) {
                    logger.debug("Find ancillary rgb " + ancillaryRgb + " coordinates is " + x + ',' + y);
                    return Optional.of(new int[] {x, y}) ;
                }
            }
        }
        return Optional.empty();
    }
}
