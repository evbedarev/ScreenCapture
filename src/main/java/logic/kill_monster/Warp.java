package logic.kill_monster;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

//-2305,-1793
public class Warp extends Monster {
    public Warp(Logger logger) throws AWTException {
        this.logger = logger;
        rgbParameterList.add(new RgbParameter(-16448251,
                new int[] {100,100},
                new int[] {-16448251}));
    }

    @Override
    public boolean findAndKill(BufferedImage screenShot) throws
            Exception {

        logger.debug("Finding monster " + this.toString());
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsInImage(
                    screenShot,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                mouse.mouseClick(x, y + 5);
                logger.info("Warp: Going to warp " + this.toString() + ", coordinates: x=" + x + " y=" + y);
                Thread.sleep(1000);
                return true;
            }
        }
        return false;
    }
}
