package logic.kill_monster;

import find_fragments.FindFragmentFiles;
import find_fragments.FindFragments;
import find_image.FindImageHard;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;
import main.Settings;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

public class Monster implements KillMonster {
    String rootDir = "C:\\Users\\тест\\ScreenCapture\\src\\main\\resources\\KillMonsters\\";
    String wildcard = "fragm*";

    Capture capture;
    final Mouse mouse;
    final Keys keys;
    final FindImageHard findImageHard;
    Settings settings;

    public Monster() throws AWTException {
        settings = Settings.instance();
        capture = Capture.instance();
        mouse = new Mouse();
        keys = new Keys();
        findImageHard = new FindImageHard();
    }

    /**
     *
     * @return boolean;
     * @throws IOException
     * @throws AWTException
     * @throws InterruptedException
     */

    @Override
    public boolean findAndKill() throws
            IOException,
            AWTException,
            InterruptedException{

        BufferedImage screenShot = capture.takeScreenShot();

        //It's bad, later change. Need to load in constructor.
        FindFragments fragmentFiles = new FindFragmentFiles(
                wildcard,
                rootDir);

        for (BufferedImage fragment: fragmentFiles.fragments()) {
            Optional<int[]> xy = findImageHard.findImage(screenShot, fragment);
            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                mouse.mouseClick(x + 18, y + 20);

                System.out.println("Killing monster: " + this.toString());
                return true;
            }
        }
        return false;
    }
}
