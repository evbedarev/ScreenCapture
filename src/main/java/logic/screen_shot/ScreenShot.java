package logic.screen_shot;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenShot implements ScreenShots {
    private volatile BufferedImage screenShot;
    private Capture capture = Capture.instance();

    public ScreenShot() throws AWTException {
    }

    @Override
    public synchronized void take(BufferedImage screenShot) {
        this.screenShot = screenShot;
    }

    @Override
    public synchronized BufferedImage pop() {
//        take(capture.takeScreenShot());
        return screenShot;
    }
}
