package logic.attacks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public interface AttackInterface {
    boolean findAndKill() throws
            IOException,
            AWTException,
            InterruptedException;

    boolean kill() throws Exception;
}
