package logic.take_loot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface TakeLoot {
    boolean takeLoot() throws
            IOException,
            AWTException,
            InterruptedException, Exception;

    void pickUp() throws Exception;

    boolean take() throws
            IOException,
            AWTException,
            InterruptedException, Exception;
}
