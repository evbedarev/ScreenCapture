package getRGB;

import actions.SleepTime;
import find_image.FindPixels;
import logger.LoggerSingle;
import logic.Capture;
import logic.RgbParameter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowRgbDynamic {
    static FindPixels findImageHard = new FindPixels();
    static int[] rgbSitDown = new int[] {-1, -2, -65794, -258};
    public static void main(String[] args) throws AWTException, InterruptedException {
        Capture capture = Capture.instance();
        int [] xy = new int[] {1569,194};
        int [] xy1 = new int[] {1569, 201};
        SleepTime.sleep(5000);

        while (true) {
            BufferedImage image = capture.takeScreenShot();
            System.out.println("First rgb is " + image.getRGB(xy[0], xy[1]));
//            System.out.println("Second rgb is " + image.getRGB(xy1[0], xy1[1]));

//            System.out.println(checkStatus());
            SleepTime.sleep(500);
        }
    }

    static boolean checkStatus() throws AWTException {
        //It's bad, later change. Need to load in constructor.
        List<RgbParameter> rgbParameterList = new ArrayList<>();
        for (int i: rgbSitDown) {
            rgbParameterList.add(new RgbParameter(i,
                    new int[] {2,2},
                    new int[] {i}));
        }

        Capture capture = Capture.instance();
        for (RgbParameter parameter: rgbParameterList) {
            BufferedImage image = capture.takeScreenShot();
            Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                    image,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {1569, 1572, 190, 600});

            if (xy.isPresent() &&
                    image.getRGB(xy.get()[0] + 1, xy.get()[1]) == parameter.getMainRgb() &&
                    image.getRGB(xy.get()[0] + 2, xy.get()[1]) == parameter.getMainRgb()) {
                System.out.println(parameter.getMainRgb());
                System.out.println(xy.get()[0] + " " + xy.get()[1]);
                return true;
            }
        }
        return false;
    }
}
