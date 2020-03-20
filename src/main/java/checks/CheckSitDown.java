package checks;

import find_image.FindPixels;
import logic.RgbParameter;
import logic.screen_shot.ScreenShot;
import main.Prop;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Проверка на иконку которая появляется с права когда перс сидит
 * Синглтон.
 */
public class CheckSitDown {
    private static CheckSitDown instance;
    private FindPixels findImageHard = new FindPixels();
    private int[] rgbSitDown = new int[] {-1, -2, -65794, -258};

    private CheckSitDown() {
    }

    static public CheckSitDown getInstance() {
        if (instance == null) {
            instance = new CheckSitDown();
        }
        return instance;
    }

    public boolean check() {
        List<RgbParameter> rgbParameterList = new ArrayList<>();
        for (int i: rgbSitDown) {
            rgbParameterList.add(new RgbParameter(i,
                    new int[] {2,2},
                    new int[] {i}));
        }

        for (RgbParameter parameter: rgbParameterList) {
            BufferedImage image = Prop.context.getBean(ScreenShot.class).pop();
            Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                    image,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {1569, 1572, 190, 600});

            if (xy.isPresent() &&
                    image.getRGB(xy.get()[0] + 1, xy.get()[1]) == parameter.getMainRgb() &&
                    image.getRGB(xy.get()[0] + 2, xy.get()[1]) == parameter.getMainRgb()) {
                return true;
            }
        }
        return false;
    }
}
