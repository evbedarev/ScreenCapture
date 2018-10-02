package checks.afterDeath;

import java.awt.image.BufferedImage;

public interface AfterDeath {
    boolean check() throws Exception;
    boolean check(BufferedImage image) throws Exception;
}
