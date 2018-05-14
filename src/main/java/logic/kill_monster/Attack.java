package logic.kill_monster;

import find_fragments.FindFragmentFiles;
import find_fragments.FindFragments;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class Attack extends Monster {
    public Attack() throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = settings.rootDir + "KillMonsters\\Attack\\";
    }

    public boolean killOrNot() throws Exception {
        BufferedImage screenShot = capture.takeScreenShot();

        //It's bad, later change. Need to load in constructor.
        FindFragments fragmentFiles = new FindFragmentFiles(
                wildcard,
                rootDir);

            Optional<int[]> xy = findImageHard.findPixel(screenShot, new int [] {-3552801,-395017});
            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
//                mouse.mouseClick(x + 18, y + 40);
                return true;
        }
        return false;

    }
}
