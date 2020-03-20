package logic.screen_shot;

import java.awt.image.BufferedImage;

public class ScreenShot implements ScreenShots {
    private volatile BufferedImage screenShot;

    @Override
    public synchronized void take(BufferedImage screenShot) {
        this.screenShot = screenShot;
    }

    @Override
    public synchronized BufferedImage pop() {
        return screenShot;
    }
}
