package getRGB;

import actions.SleepTime;
import find_image.FindPixels;
import logic.Capture;
import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.image.BufferedImage;
import java.util.Optional;

public class RGB {
    public static void main(String[] args) throws Exception {

//        CompareFragmentImage compareFragmentImage = new CompareFragmentImage();
//        compareFragmentImage.getRgb();
//Map rgb -2374501
//        findPixelRgb(-2374501);

        showRGB(new int[] {14,844}, "C:\\TEMP\\ScreenCapture\\src\\main\\resources\\Interface\\CheckIsAttackMonsterOtherPlayer\\checkAttackOtherPlayer.png");
//        showRGB(new int[] {1073,375}, "C:\\TEMP\\ScreenCapture\\src\\main\\resources\\getRGB\\fragment.png");
//        findPixelRgb(-3219746);

//        while (true) {
//            Optional<int[]> xy = takeCoordsFromMap();
//            if (xy.isPresent()) {
//                System.out.println("points.add(new int[] {" + xy.get()[0] +", " + xy.get()[1] +"});");
//            }
//        }
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
//                    System.out.println("x=" + x + " y=" + y);
                    System.out.println("points.add(new int[] {"+ x +"," + y +"});");
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

    public static Optional<int[]> takeCoordsFromMap() throws Exception {
        Capture capture = Capture.instance();
        FindPixels findImageHard = new FindPixels();
        BufferedImage screenShot;
        screenShot = capture.takeScreenShot();
        Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                screenShot,
                -2752512,
                new int[] {2,2},
                new int[] {-2752512},
                new int[] {1459, 1586, 43, 168});

        if (xy.isPresent()) {
            int x = xy.get()[0];
            int y = xy.get()[1];

//            LoggerSingle.logInfo(this.toString(), "Location TP " + this.toString() + ", coordinates: x=" + x + " y=" + y);
            SleepTime.sleep(1300);
        }
        return xy;
    }
}
