package logic.kill_monster;

import logger.LoggerSingle;
import logic.RgbParameter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

//-2305,-1793
public class Warp extends Monster {
    public Warp() throws AWTException {
        rgbParameterList.add(new RgbParameter(-723721,
                new int[] {100,100},
                new int[] {-3684641, -3421213}));

        rgbParameterList.add(new RgbParameter( -3158041,
                new int[] {100,100},
                new int[] {-2894872}));
    }

    @Override
    public boolean findAndKill() throws
            Exception {

        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixels3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                mouse.mouseClick(x, y + 5);
                LoggerSingle.logInfo(this.toString(), "Warp: Going to warp, coordinates: x=" + x + " y=" + y);
                Thread.sleep(1000);
                return true;
            }
        }
        return false;
    }
}
