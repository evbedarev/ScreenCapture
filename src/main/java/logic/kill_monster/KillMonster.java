package logic.kill_monster;

import java.awt.*;
import java.io.IOException;

public interface KillMonster {
    boolean findAndKill() throws
            IOException,
            AWTException,
            InterruptedException;
}
