package logic.kill_monster;

import logic.RgbParameter;
import java.awt.*;
import java.util.List;

public interface KillMonster {

    boolean findAndKill() throws
            Exception;

    boolean kill() throws Exception;

    boolean findAndKillAround() throws
            AWTException,
            InterruptedException;

    boolean findMonster() throws Exception;

    List<RgbParameter> getRgbParameterList();
}
