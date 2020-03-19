package getRGB;

import actions.SleepTime;
import find_image.FindPixels;
import logic.screen_shot.Capture;
import logic.RgbParameter;
import logic.screen_shot.ScreenShotStack;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindRgbOnScreen {
    static FindPixels findImageHard = new FindPixels();
    static int[] rgbSitDown = new int[] {-1, -2, -65794, -258};
    public static void main(String[] args) throws AWTException, InterruptedException {
        List<RgbParameter> rgbParameterList = new ArrayList<>();
        Capture capture = Capture.instance();
        BufferedImage image = Prop.context.getBean(ScreenShotStack.class).pop();
        rgbParameterList.add(new RgbParameter(-610655,
                new int[] {2,2},
                new int[] {-610655}));
        SleepTime.sleep(5000);
        for (RgbParameter parameter: rgbParameterList) {
            image = Prop.context.getBean(ScreenShotStack.class).pop();
            Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                    image,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[]{0,1600, 0, 900});
            if (xy.isPresent())
                System.out.println("True");
            else
                System.out.println("False");
        }
    }
}
