package logic.kill_monster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface KillMonster {

    boolean findAndKill(BufferedImage screenShot) throws
            IOException,
            AWTException,
            InterruptedException;

    boolean kill() throws Exception;

    boolean findAndKillAround(BufferedImage screenShot) throws
            AWTException,
            InterruptedException;

    boolean killAround() throws AWTException, InterruptedException;
}
