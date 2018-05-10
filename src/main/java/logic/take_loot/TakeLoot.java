package logic.take_loot;

import java.awt.*;
import java.io.IOException;

public interface TakeLoot {
    boolean takeLoot() throws
            IOException,
            AWTException,
            InterruptedException;
}
