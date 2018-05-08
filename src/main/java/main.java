import find_fragments.FindFragmentFiles;
import find_image.FindImageHard;
import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, AWTException, InterruptedException {
        StorageImage storageImage = new StorageImageFile();
        FindFragmentFiles fragmentFiles = new FindFragmentFiles("fragm*", "./src/main/resources/");
        takeScreenShot();
        Thread.currentThread().sleep(1000);
        BufferedImage image = storageImage.load("./src/main/resources/" + "somefile.png");
        for (BufferedImage fragment: fragmentFiles.fragments()) {
            findImage(image, fragment);
        }

//        BufferedImage fragment = storageImage.load("./src/main/resources/" + "fragment.png");
//        findImage(image, fragment);
    }

    private static void takeScreenShot() throws AWTException {
        Capture capture = new Capture();
        capture.takeScreenShot("somefile");
    }

    private static void findImage(BufferedImage screenShot, BufferedImage fragment) throws AWTException {
        PressAndMove move = new PressAndMove();
        Long begin = System.currentTimeMillis();
        FindImageHard findImageHard = new FindImageHard();
        int [] xy = findImageHard.findImage(screenShot, fragment);
        if (xy.length > 1) {
            int x = xy[0];
            int y = xy[1];
            System.out.println("Found! y = " + y + " x = " + x +
                    " take time = " + (System.currentTimeMillis() - begin) + "ms");
            move.moveMouse(x + 18, y + 20);
        } else {
            System.out.println("nothing found");
        }
    }
}
