package checks.check_hp;

import java.awt.image.BufferedImage;

public class CheckHP implements CheckHitPoints {
    private static volatile CheckHP instance;
    private boolean checkHp;
    private CheckHitPoints checkHitPoints;

    private CheckHP() {
    }

    static public CheckHP instance() {
        if (instance == null) {
            synchronized (CheckHP.class) {
                if (instance == null) {
                    instance = new CheckHP();
                }
            }
        }
        return instance;
    }

    public void initialize(boolean checkHp, CheckHitPoints checkHitPoints) {
        this.checkHp = checkHp;
        this.checkHitPoints = checkHitPoints;
    }

    public void checkHp() throws Exception {
        if (!checkHp)
            return;
        checkHitPoints.checkHp();
    }

    public void checkHp(BufferedImage image) throws Exception {
        if (!checkHp)
            return;
        checkHitPoints.checkHp(image);
    }
}
