package logic.kill_monster;

import find_fragments.FindFragmentFiles;
import find_fragments.FindFragments;
import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class Attack extends Monster {
    public Attack(Logger logger) throws AWTException {
        super();
        super.wildcard = "fragm*";
        super.rootDir = Prop.getRootDir() + "KillMonsters\\Attack\\";
        super.logger = logger;
        super.mainRgb = -329224;
        super.subImageSize = new int[] {20,20};
        super.ancillaryRgb = new int[] {-329224};
    }

    public boolean killOrNot() throws Exception {
        BufferedImage screenShot = capture.takeScreenShot();

        //It's bad, later change. Need to load in constructor.
        FindFragments fragmentFiles = new FindFragmentFiles(
                wildcard,
                rootDir);

            Optional<int[]> xy = findImageHard.findPixelsInImage(
                    screenShot,
                    mainRgb,
                    subImageSize,
                    ancillaryRgb);

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
//                mouse.mouseClick(x + 18, y + 40);
//                System.out.println("Attacking");
                logger.info("Attacking !");
                return true;
        }
        return false;

    }
}
