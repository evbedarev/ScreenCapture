package actions.PressOnImage;

import actions.SleepTime;
import find_image.FindFragmentInImage;
import key_and_mouse.Mouse;
import logger.LoggerSingle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

public class PressOnImage {
    private final Mouse mouse;
    private final FindFragmentInImage findFragmentInImage = FindFragmentInImage.getInstance();

    public PressOnImage() throws AWTException {
        this.mouse = Mouse.getInstance();
    }

    public boolean press(int[] area,
                         int sleepTime,
                         String pathFragment,
                         String methodName) throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(area);
        xy = findFragmentInImage.findImage(pathFragment);
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0], xy.get()[1]);
            SleepTime.sleep(sleepTime);
            LoggerSingle.logInfo(this.toString() + "+" + methodName, ": find and click." );
            return true;
        }
        return false;
    }

    public boolean press(BufferedImage screenShot,
                         int[] area,
                         int sleepTime,
                         String pathFragment,
                         String methodName) throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(area);
        xy = findFragmentInImage.findImage(screenShot, pathFragment);
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0], xy.get()[1]);
            SleepTime.sleep(sleepTime);
            LoggerSingle.logInfo(this.toString() + "+" + methodName, ": find and click." );
            return true;
        }
        return false;
    }

    public boolean press(BufferedImage screenShot,
                         int[] area,
                         int sleepTime,
                         List<BufferedImage> imageList,
                         String methodName) throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(area);
        xy = findFragmentInImage.findImage(screenShot, imageList);
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0], xy.get()[1]);
            SleepTime.sleep(sleepTime);
            LoggerSingle.logInfo(this.toString() + "+" + methodName, ": find and click." );
            return true;
        }
        return false;
    }
}
