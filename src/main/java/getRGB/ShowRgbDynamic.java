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
    public static void main(String[] args) throws AWTException, InterruptedException {
        Capture capture = Capture.instance();
        int [] xy = new int[] {1568, 247};

        SleepTime.sleep(5000);
        while (true) {
            BufferedImage image = capture.takeScreenShot();
//            System.out.println(image.getRGB(xy[0], xy[1]));
            System.out.println(checkStatus());
            SleepTime.sleep(500);
        }
    }

    static boolean checkStatus() throws AWTException {
        //It's bad, later change. Need to load in constructor.
        List<RgbParameter> rgbParameterList = new ArrayList<>();

        rgbParameterList.add(new RgbParameter(-2,
                new int[] {2,2},
                new int[] {-2}));

        rgbParameterList.add(new RgbParameter(-65538,
                new int[] {2,2},
                new int[] {-65538}));

        rgbParameterList.add(new RgbParameter(-65794,
                new int[] {2,2},
                new int[] {-65794}));

        rgbParameterList.add(new RgbParameter(-258,
                new int[] {2,2},
                new int[] {-258}));

        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsArround3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {1555, 1587, 190, 600});

            if (xy.isPresent()) {
                return true;
            }
        }
        return false;
    }


}
