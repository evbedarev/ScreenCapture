package getRGB;

import logic.screen_shot.Capture;
import logic.screen_shot.ScreenShotStack;
import main.Prop;
import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.image.BufferedImage;

public class ForRoute {
    public static void main(String[] args) throws Exception {

        findPixelRgb(-1606855, "C:\\TEMP\\ScreenCapture\\src\\main\\resources\\getRGB\\fragment.png");
        findPixelRgb(-2752512, "C:\\TEMP\\ScreenCapture\\src\\main\\resources\\getRGB\\fragment1.png");

//        showRGB(new int[] {274,86}, "C:\\TEMP\\ScreenCapture\\src\\main\\resources\\getRGB\\fragment.png");
//        findPixelRgb(-3219746);
    }
// Coursor rgb -16250872, -1063003
//-3552801
//-395017

    /**
     * Searchs all pixels with one RGB
     * @param intRGB - integer RGB
     * @throws Exception
     */
    public static void findPixelRgb(int intRGB, String path) throws Exception {
        StorageImage storageImage = StorageImageFile.instance();
        BufferedImage image = storageImage.load(path);
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
        BufferedImage image = Prop.context.getBean(ScreenShotStack.class).pop();
        System.out.println(image.getRGB(xy[0], xy[1]));

    }
}
