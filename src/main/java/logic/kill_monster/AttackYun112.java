package logic.kill_monster;

import find_fragments.FindFragmentFiles;
import find_fragments.FindFragments;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

//-329224
//-3553057
public class AttackYun112 extends Monster implements AttackInterface {
    public AttackYun112(Logger logger) throws AWTException {
        super();
        super.logger = logger;
        super.mainRgb = -3487264;
        super.subImageSize = new int[] {40,40};
        super.ancillaryRgb = new int[] {-329481};
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
                logger.info(this.toString() + " Attacking !");
                return true;
        }
        return false;

    }
}
