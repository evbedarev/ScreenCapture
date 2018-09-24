package checks;

import actions.Actions;
import logger.LoggerSingle;
import logic.Capture;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CheckSP {
    private static volatile CheckSP instance;
    private Capture capture;
    private Actions actions;
    public static boolean enoughSP;
    private CheckSitDown checkSitDown;
    private CheckAgressorIsNear checkAgressorIsNear = CheckAgressorIsNear.instance();
    private CheckDie checkDie;

    private CheckSP() {
    }

    static public CheckSP instance() {
        if (instance == null) {
            synchronized (CheckSP.class) {
                if (instance == null) {
                    instance = new CheckSP();
                }
            }
        }
        return instance;
    }

    public void initialize() throws
            AWTException {
        capture = Capture.instance();
        actions = Actions.instance();
        checkSitDown = CheckSitDown.getInstance();
    }

    public boolean enoghtSP() {
        BufferedImage image = capture.takeScreenShot();
        enoughSP = image.getRGB(Prop.X_SP, Prop.Y_SP) == Prop.SP_RGB;
        return enoughSP;
    }

    private boolean regenerateSP() {
        BufferedImage image = capture.takeScreenShot();
        enoughSP = image.getRGB(Prop.X_SP_ENOUGHT, Prop.Y_SP) == Prop.SP_RGB;
        return enoughSP;
    }

    public boolean enoghtSP(BufferedImage image) {
        if(!Prop.NEED_CHECK_SP)
            return true;
        enoughSP = image.getRGB(Prop.X_SP, Prop.Y_SP) == Prop.SP_RGB;
        return enoughSP;
    }

    private void standUp() throws Exception {
        for (int i = 0; i < 3; i++) {
            if (!checkSitDown.check())
                break;
            actions.standUp();
            Thread.sleep(1000);
        }
        LoggerSingle.logInfo(this.toString(), "standing up");
    }

    private void sitDown() throws Exception {
        for (int i = 0; i < 3; i++) {
            if (checkSitDown.check())
                break;
            actions.sitDown();
            Thread.sleep(1000);
        }
        LoggerSingle.logInfo(this.toString(), "siting down");
    }

    /**
     * Проверят влкючена ли проверка сп.
     * Если сп мало, садится и ждёт пока регенится, если в это время его ударят, улетает.
     * Как только сп достаточно, встаёт.
     * @throws Exception
     */
    public void regenSP() throws Exception {
//        LoggerSingle.logInfo(this.toString(), "Regenerating SP...");
        if (!Prop.NEED_CHECK_SP) return;
        if (!enoghtSP()) {
            LoggerSingle.logInfo(this.toString(), "Regenerate SP...");
            sitDown();
            while (!regenerateSP()) {
                Thread.sleep(2000);
                if (!checkSitDown.check()) {
                    actions.teleport();
                }
                sitDown();
            }
            standUp();
        }
    }
}
