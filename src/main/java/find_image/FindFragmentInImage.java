package find_image;

import find_fragments.FindFragmentFiles;
import logic.Capture;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

public class FindFragmentInImage {
    private static FindFragmentInImage instance;
    private FindImageHard findImageHard = FindImageHard.getInstance();
    private FindFragmentFiles findFragmentFiles = FindFragmentFiles.getInstance();
    private Capture capture;
    private int[] screen = new int[] {0,1600,0,900};
    private List<BufferedImage> imageList;

    public void setScreen(int[] screen) {
        this.screen = screen;
    }

    private FindFragmentInImage() {
    }

    public static FindFragmentInImage getInstance() {
        if (instance == null) {
            instance = new FindFragmentInImage();
        }
        return instance;
    }

    public Optional<int[]> findImage(String dir) throws Exception{
        capture = Capture.instance();
        imageList = findFragmentFiles.fragments("frag*", dir);
        Optional<int[]> xy;
        for (BufferedImage image: imageList) {
            BufferedImage screenshot = capture.takeScreenShot();
            xy = findImageHard.findImageInArea(screenshot, image,
                    screen);
            if (xy.isPresent())
                return xy;
        }
        return Optional.empty();
    }

    public Optional<int[]> findImageExcludeArea(String dir) throws Exception{
        capture = Capture.instance();
        imageList = findFragmentFiles.fragments("frag*", dir);
        Optional<int[]> xy;
        for (BufferedImage image: imageList) {
            BufferedImage screenshot = capture.takeScreenShot();
            xy = findImageHard.findImageExcludeArea(screenshot, image,
                    screen);
            if (xy.isPresent())
                return xy;
        }
        return Optional.empty();
    }



}
