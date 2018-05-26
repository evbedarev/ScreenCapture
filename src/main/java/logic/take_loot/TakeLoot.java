package logic.take_loot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface TakeLoot {
    boolean takeLoot(BufferedImage screenShot) throws
            IOException,
            AWTException,
            InterruptedException;

    void pickUp() throws Exception;

    boolean take() throws
            IOException,
            AWTException,
            InterruptedException;
}
