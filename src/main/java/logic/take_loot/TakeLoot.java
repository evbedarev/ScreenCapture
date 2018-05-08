package logic.take_loot;

import java.awt.*;
import java.io.IOException;

public interface TakeLoot {
    void takeLoot() throws
            IOException,
            AWTException,
            InterruptedException;
}
