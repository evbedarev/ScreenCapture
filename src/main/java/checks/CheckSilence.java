package checks;

import find_image.FindPixels;
import logger.LoggerSingle;
import logic.RgbParameter;
import logic.screen_shot.ScreenShot;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Проверка на иконку которая появляется с права когда перс сидит
 * Синглтон.
 */
public class CheckSilence {
    private static CheckSilence instance;
    private FindPixels findImageHard = new FindPixels();
    private int[] rgbSitDown = new int[] {-65811, -19, -18, -275};
    private  List<RgbParameter> rgbParameterList = new ArrayList<>();

    private CheckSilence() throws AWTException {
        for (int i: rgbSitDown) {
            rgbParameterList.add(new RgbParameter(i,
                    new int[] {2,2},
                    new int[] {i}));
        }
    }

    static public CheckSilence getInstance() throws AWTException {
        if (instance == null) {
            instance = new CheckSilence();
        }
        return instance;
    }

    public boolean check() {
        for (RgbParameter parameter: rgbParameterList) {
            BufferedImage image = Prop.context.getBean(ScreenShot.class).pop();
            Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                    image,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {1555, 1587, 190, 325});

            if (xy.isPresent()) {
                LoggerSingle.logInfo(this.toString(), "Checking silence. Silence true");
                return true;
            }
        }

        LoggerSingle.logDebug(this.toString(), "Checking silence. Silence false");
        return false;
    }
}
