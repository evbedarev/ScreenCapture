package logic.take_loot;

import find_fragments.FindFragmentFiles;
import find_fragments.FindFragments;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

//public class AntelopeHorn extends Loot {
//    public AntelopeHorn() throws AWTException {
//        super();
//        super.wildcard = "fragm*";
//        super.rootDir = Prop.getRootDir() + "Loot\\AntelopeHorn\\";
//    }
//
//    @Override
//    public boolean takeLoot() throws
//            IOException,
//            AWTException,
//            InterruptedException {
//
//        BufferedImage screenShot = capture.takeScreenShot();
//
//        //It's bad, later change. Need to load in constructor.
//        FindFragments fragmentFiles = new FindFragmentFiles(
//                wildcard,
//                rootDir);
//
//        Optional<int[]> xy = findImageHard.findImage2Pixels(screenShot, new int[] {-9745846, -8103334});
//        if (xy.isPresent()) {
//            int x = xy.get()[0];
//            int y = xy.get()[1];
//            mouse.mouseClick(x , y );
//            logger.info("Taking loot, coordinates: x=" + x + " y=" + y);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void pickUp() throws Exception {
//        TakeLoot takeLoot = this;
//        while (takeLoot.takeLoot()) {
//            Thread.sleep(1000);
//        }
//    }
//}
