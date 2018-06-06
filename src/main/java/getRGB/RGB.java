package getRGB;

import logic.Capture;
import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.image.BufferedImage;
import java.util.*;

public class RGB {
    public static void main(String[] args) throws Exception {
        CompareFragmentImage compareFragmentImage = new CompareFragmentImage();
//        compareFragmentImage.getRgb();
        showRGB(new int[] {0,0}, "C:\\TEMP\\ScreenCapture\\src\\main\\resources\\Loot\\Shield\\fragment.png");
//        findPixelRgb(-3219746);
    }

//-3552801
//-395017

    /**
     * Searchs all pixels with one RGB
     * @param intRGB - integer RGB
     * @throws Exception
     */
    public static void findPixelRgb(int intRGB) throws Exception {
        StorageImage storageImage = new StorageImageFile();
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
        StorageImage storageImage = new StorageImageFile();
        BufferedImage image = storageImage.load(pathImage);
        System.out.println(image.getRGB(xy[0], xy[1]));

    }

    public static void showRGB(int[] xy) throws Exception {
        Capture capture = Capture.instance();
        BufferedImage image = capture.takeScreenShot();
        System.out.println(image.getRGB(xy[0], xy[1]));

    }
}
