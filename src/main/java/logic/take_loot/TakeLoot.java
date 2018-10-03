package logic.take_loot;
import java.awt.image.BufferedImage;

public interface TakeLoot {
    boolean takeLoot() throws Exception;
    boolean takeLoot(BufferedImage screenShot) throws Exception;

    void pickUp() throws Exception;
    void pickUp(BufferedImage screenShot) throws Exception;


    boolean take() throws Exception;
}
