package find_image;

import logic.Capture;
import storage_image.StorageImageFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

public class FindFragmentInImage {
    private FindImageHard findImageHard = FindImageHard.getInstance();
    private StorageImageFile storageImageFile = StorageImageFile.instance();
    private Capture capture;

    public FindFragmentInImage() throws AWTException  {
        capture = Capture.instance();
    }

    public void findImage(String frag) throws IOException {
        BufferedImage screenshot = capture.takeScreenShot();
        BufferedImage fragment = storageImageFile.load(frag);

        Optional<int[]> xy = findImageHard.findImageInArea(screenshot, fragment ,
                new int[] {0, 800, 0, 900});

    }
}
