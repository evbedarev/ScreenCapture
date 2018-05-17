package logic.take_loot;

import find_fragments.FindFragmentFiles;
import find_fragments.FindFragments;
import find_image.FindImageHard;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import main.Settings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

public class Loot implements TakeLoot {
    String rootDir = "C:\\Users\\тест\\ScreenCapture\\src\\main\\resources\\Loot\\Bottleq\\";
    String wildcard = "fragmq*";
    Settings settings;

    Capture capture;
    final Mouse mouse;
    final Keys keys;
    final FindImageHard findImageHard;

    public Loot() throws AWTException {
        settings = Settings.instance();
        capture = Capture.instance();
        mouse = new Mouse();
        keys = new Keys();
        findImageHard = new FindImageHard();
    }

    @Override
    public boolean takeLoot() throws
            IOException,
            AWTException,
            InterruptedException {

        BufferedImage screenShot = capture.takeScreenShot();

        //It's bad, later change. Need to load in constructor.
        FindFragments fragmentFiles = new FindFragmentFiles(
                wildcard,
                rootDir);

        for (BufferedImage fragment: fragmentFiles.fragments()) {
            Optional<int[]> xy = findImageHard.findImageExcludeArea(screenShot, fragment);
            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                mouse.mouseClick(x + 18, y + 20);
                return true;
            }
        }
        return false;
    }

    @Override
    public void pickUp() throws Exception {
        TakeLoot takeLoot = this;
        while (takeLoot.takeLoot()) {
            Thread.sleep(1000);
        }
    }
}
