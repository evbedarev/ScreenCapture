import find_fragments.FindFragmentFiles;
import find_fragments.FindFragments;
import find_image.FindImageHard;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import storage_image.StorageImage;
import storage_image.StorageImageFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class main {
    public static void
    main(String[] args) throws IOException, AWTException, InterruptedException {
        StorageImage storageImage = new StorageImageFile();
        FindFragments fragmentFiles = new FindFragmentFiles(
                "fragm*",
                "./src/main/resources/");
        takeScreenShot();
        Thread.currentThread().sleep(1000);
        BufferedImage image = storageImage.load(
                "./src/main/resources/" + "somefile.png");
        for (BufferedImage fragment: fragmentFiles.fragments()) {
            findImage(image, fragment);
        }

//        BufferedImage fragment = storageImage.load("./src/main/resources/" + "fragment.png");
//        findImage(image, fragment);
    }

    private static void takeScreenShot() throws AWTException {
        Capture capture = new Capture();
        capture.takeScreenShotToFile("somefile");
    }

    private static void findImage(BufferedImage screenShot, BufferedImage fragment)
            throws AWTException, InterruptedException {
        Mouse mouse = new Mouse();
        Keys keys = new Keys();
        Long begin = System.currentTimeMillis();
        FindImageHard findImageHard = new FindImageHard();
        Optional<int[]> xy = findImageHard.findImage(screenShot, fragment);
        if (xy.isPresent()) {
            int x = xy.get()[0];
            int y = xy.get()[1];
            System.out.println("Found! y = " + y + " x = " + x +
                    " take time = " + (System.currentTimeMillis() - begin) + "ms");
            mouse.mouseClick(x + 18, y + 20);
//            keys.keyPress(KeyEvent.VK_ENTER);
        } else {
            System.out.println("nothing found");
        }
    }
}
