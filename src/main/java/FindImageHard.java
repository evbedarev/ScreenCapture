import java.awt.image.BufferedImage;

public class FindImageHard implements FindImage {

    @Override
    public int[] findImage(BufferedImage screenShot, BufferedImage fragment) {
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
                return new int[] {x,y};
            }
        }
        return new int[0];
    }
}
