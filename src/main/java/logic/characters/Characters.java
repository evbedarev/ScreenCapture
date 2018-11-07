package logic.characters;

import java.awt.*;

public interface Characters {

    boolean follow() throws
            Exception;


    boolean findAround() throws
            AWTException,
            InterruptedException;

    boolean findCharacter() throws Exception;
}
