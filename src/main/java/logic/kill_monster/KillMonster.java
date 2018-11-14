package logic.kill_monster;

import logic.RgbParameter;
import java.awt.image.BufferedImage;
import java.util.List;

public interface KillMonster {

    boolean findMonster(BufferedImage image) throws
            Exception;

    boolean findAndKill() throws
            Exception;

    boolean kill() throws
            Exception;

    boolean killArround() throws
            Exception;

    boolean findAndKill(BufferedImage image) throws
            Exception;

    boolean findAndKillAround() throws
            Exception;

    boolean findMonster() throws
            Exception;

    boolean findAndKillAround(BufferedImage image) throws
            Exception;

    List<RgbParameter> getRgbParameterList();
}
