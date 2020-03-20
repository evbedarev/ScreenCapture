package logic.screen_shot;

import java.awt.image.BufferedImage;

public interface ScreenShots {
    void take(BufferedImage screenShot);
    BufferedImage pop();
}
