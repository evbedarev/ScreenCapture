package find_image;

import java.awt.image.BufferedImage;
import java.util.Optional;

public class FindPixels implements FindPixelsInImage {

    @Override
    public Optional<int[]> findPixelsInImage(
            BufferedImage screenShot,
            int mainRgb,
            int[] subImgCoord,
            int[] ancillaryRgb) {

    for (int y = 0; y < screenShot.getHeight(); y++) {
        for (int x = 0; x < screenShot.getWidth(); x++) {
            if (screenShot.getRGB(x, y) == mainRgb) {
                if (getSubImage(screenShot, new int[] {x, y}, subImgCoord, ancillaryRgb)) {
                    return Optional.of(new int[] {x, y});
                }
            }
        }
    }
    return Optional.empty();
    }

    /**
     *
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
            int width = subImgCoord[0]*2;
            int height = subImgCoord[1]*2;
            BufferedImage image = screenshot.getSubimage(x_up_left,y_up_left,width,height);
            for (int rgb:ancillaryRgb) {
                if (!findPixel(image, rgb).isPresent()) {
                    return false;
                }
            }
            return true;
    }

    public Optional<int[]> findPixel(BufferedImage screenShot, int ancillaryRgb) {
        for (int y = 0; y < screenShot.getHeight(); y++) {
            for (int x = 0; x < screenShot.getWidth(); x++) {
                if (screenShot.getRGB(x, y) == ancillaryRgb) {
                    return Optional.of(new int[] {x, y}) ;
                }
            }
        }
        return Optional.empty();
    }
}
