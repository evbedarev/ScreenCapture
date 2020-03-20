package getRGB;

import actions.SleepTime;
import find_image.FindPixels;
import logic.RgbParameter;
import logic.screen_shot.ScreenShot;
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
        BufferedImage image = Prop.context.getBean(ScreenShot.class).pop();
        rgbParameterList.add(new RgbParameter(-610655,
                new int[] {2,2},
                new int[] {-610655}));
        SleepTime.sleep(5000);
        for (RgbParameter parameter: rgbParameterList) {
            image = Prop.context.getBean(ScreenShot.class).pop();
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
