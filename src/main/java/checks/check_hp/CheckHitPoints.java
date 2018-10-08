package checks.check_hp;

import java.awt.image.BufferedImage;

public interface CheckHitPoints {
    void checkHp() throws Exception;
    void checkHp(BufferedImage image) throws Exception;
}
