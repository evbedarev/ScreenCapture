package logic.kill_monster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface KillMonster {

    boolean findAndKill() throws
            Exception;

    boolean kill() throws Exception;

    boolean findAndKillAround() throws
            AWTException,
            InterruptedException;

    boolean killAround() throws AWTException, InterruptedException;

    boolean findMonster() throws Exception;
}
