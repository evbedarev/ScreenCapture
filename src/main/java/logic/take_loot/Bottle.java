package logic.take_loot;

import find_fragments.FindFragmentFiles;
import find_fragments.FindFragments;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

public class Bottle extends Loot {
    private static final String ROOT_DIR = "C:\\Users\\тест\\ScreenCapture\\src\\main\\resources\\Loot\\Bottle\\";
    private static final String WILDCARD = "fragm*";

    public Bottle() throws AWTException {
        super();
    }

    @Override
    public void takeLoot() throws
            IOException,
            AWTException,
            InterruptedException {

        BufferedImage screenShot = capture.takeScreenShot();

        //It's bad, later change. Need to load in constructor.
        FindFragments fragmentFiles = new FindFragmentFiles(
                WILDCARD,
                ROOT_DIR);

        for (BufferedImage fragment: fragmentFiles.fragments()) {
            Optional<int[]> xy = findImageHard.findImage(screenShot, fragment);
            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                mouse.mouseClick(x + 18, y + 20);
            }
        }

    }
}
