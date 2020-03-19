import find_image.FindPixels;
import logic.screen_shot.Capture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class TestCountsImage {
    public static void main(String[] args) throws AWTException, InterruptedException {
        final FindPixels findImageHard = new FindPixels();
        BufferedImage screenShot;
        Capture capture = Capture.instance();
        screenShot = capture.takeScreenShot();
        Thread.sleep(3000);
        Optional<Integer> someInt = findImageHard.findCountsFragmentsInImageInArea(
                screenShot,
                -2115,
                new int[] {90,90},
                new int[] {-2706028,-12644,-547469,-6530461},
                new int[] {0,1600,0,900}
        );
        if (
                someInt.isPresent())
        System.out.println(
                someInt.get()
        );
    }
}

