package getRGB;

import logic.Capture;
import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.image.BufferedImage;

public class RGB {
    public static void main(String[] args) throws Exception {
        CompareFragmentImage compareFragmentImage = new CompareFragmentImage();
        compareFragmentImage.getRgb();

//        findPixelRgb(-16776192);

//        showRGB(new int[] {70,82}, "C:\\TEMP\\ScreenCapture\\src\\main\\resources\\getRGB\\fragment.png");
//        findPixelRgb(-3219746);
    }
// Coursor rgb -16250872
//-3552801/-65794
//-395017

    /**
     * Searchs all pixels with one RGB
     * @param intRGB - integer RGB
     * @throws Exception
     */
    public static void findPixelRgb(int intRGB) throws Exception {
        StorageImage storageImage = StorageImageFile.instance();
        BufferedImage image = storageImage.load("C:\\TEMP\\ScreenCapture\\src\\main\\resources\\getRGB\\fragment.png");
//        BufferedImage image = storageImage.load("/home/mj/Projects/ScreenCapture/src/main/resources/for_test/fragment_picture.png");
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x=0; x < image.getWidth(); x++) {
                if (image.getRGB(x,y) == intRGB) {
                    System.out.println("x=" + x + " y=" + y);
                }
            }
        }
    }

    public static void showRGB(int[] xy, String pathImage) throws Exception {
        StorageImage storageImage = StorageImageFile.instance();
        BufferedImage image = storageImage.load(pathImage);
        System.out.println(image.getRGB(xy[0], xy[1]));

    }

    public static void showRGB(int[] xy) throws Exception {
        Capture capture = Capture.instance();
        BufferedImage image = capture.takeScreenShot();
        System.out.println(image.getRGB(xy[0], xy[1]));

    }
}
