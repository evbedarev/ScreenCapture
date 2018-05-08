package logic.kill_monster;

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
import java.util.Optional;

public class KillMonsterNovice implements KillMonster{
    private static final String ROOT_DIR = "C:\\Users\\тест\\ScreenCapture\\src\\main\\resources\\KillMonstersNovice\\";
    private static final String WILDCARD = "fragm*";
    private final Capture capture;
    private final Mouse mouse;
    private final Keys keys;
    private final FindImageHard findImageHard;

    public KillMonsterNovice() throws AWTException {
        capture = new Capture();
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
                WILDCARD,
                ROOT_DIR);

        for (BufferedImage fragment: fragmentFiles.fragments()) {
            Optional<int[]> xy = findImageHard.findImage(screenShot, fragment);
            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                mouse.mouseClick(x + 18, y + 20);
                return true;
            }
        }
        return false;
    }


}
