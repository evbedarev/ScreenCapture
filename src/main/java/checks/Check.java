package checks;

import actions.InterfaceActions;
import actions.SleepTime;
import actions.after_return_to_kafra.ReturnFromCmdField02;
import actions.after_return_to_kafra.ReturnToKafra;
import actions.kafra_actions.put_loot.KafraActionsPutLoot;
import actions.kafra_actions.put_loot.PutLoot;
import find_image.FindFragmentInImage;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.screen_shot.ScreenShot;
import main.Prop;
import org.apache.log4j.Logger;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class Check {
    private static final String CHAR_SELECT_IMAGE_PATH = Prop.ROOT_DIR + "Interface\\CheckInCharSelect\\";
    private static final String TRADE_OPEN_IMAGE_PATH = Prop.ROOT_DIR + "Interface\\TradeOpen\\";
    private static Optional<int[]> xy = Optional.empty();

    public static volatile Check instance;
    private final FindFragmentInImage findFragmentInImage = FindFragmentInImage.getInstance();
    private final Logger logger = Logger.getLogger(this.getClass());
    private final Keys keys;
    private final Mouse mouse;
    private Check() throws AWTException {
        keys = Keys.getInstance();
        mouse = Mouse.getInstance();
    }

    public static Check getInstance() throws AWTException {
        if (instance == null) {
            synchronized ((InterfaceActions.class)) {
                if (instance == null) {
                    instance = new Check();
                }
            }
        }
        return instance;
    }

    private void setScreenAndEmptyValue(int[] screenSize) {
        xy = Optional.empty();
        findFragmentInImage.setScreen(screenSize);
    }
    public boolean haveWings(BufferedImage screenShot) {
        return screenShot.getRGB(1340, 881) != -1;
    }
    public boolean havePotion(BufferedImage screenShot) {
       return screenShot.getRGB(1399, 887) != -1;
    }
    public boolean haveButWings(BufferedImage screenShot) {
        return screenShot.getRGB(1516, 877) != -1;
    }

    public void checkResources(BufferedImage screenShot) throws Exception {
        if (!haveWings(screenShot) || !havePotion(screenShot))
            if (!haveButWings(screenShot)) {
                SleepTime.loopSleep();
            } else {
                keys.keyPress(KeyEvent.VK_F7);
                SleepTime.sleep(4000);
                Prop.returnFromField.startAction();
            }
        if (checkOtherPlayerNear(screenShot)) {
           keys.keyPress(KeyEvent.VK_F1);
           SleepTime.sleep(3000);
           Prop.interfaceActions.goToCharSelect();
           if (checkInCharSelect()) {
               SleepTime.sleep(40000);
               keys.keyPress(KeyEvent.VK_ENTER);
           }
        }
    }

    public void checkIsThereWing(BufferedImage screenShot) {
        if ((screenShot.getRGB(1340, 881) == -1))
            SleepTime.loopSleep();
    }

    /**
     * Slowly method that checks is wings exists
     * if there no wings method sleep in cycle
     * @throws AWTException
     */
    public boolean checkIsThereWing() throws AWTException {
        BufferedImage screenShot = Prop.context.getBean(ScreenShot.class).pop();
        return screenShot.getRGB(1340, 881) != -1;
    }

    private boolean findImage(int[] area,
                              int sleepTime,
                              String pathFragment,
                              String methodName) throws Exception {
        xy = Optional.empty();
        findFragmentInImage.setScreen(area);
        xy = findFragmentInImage.findImage(pathFragment);
        if (xy.isPresent()) {
            SleepTime.sleep(sleepTime);
            logger.info(methodName + ": find and click." );
            return true;
        }
        return false;
    }

    public boolean checkInCharSelect() throws Exception {
        return findImage(new int[]{0, 1600, 0, 900},
                1000,
               CHAR_SELECT_IMAGE_PATH,
                "Check.checkInCharSelect");
    }

    public boolean checkTradeIsOpen() throws Exception {
        return findImage(new int[]{0, 1600, 0, 900},
                1000,
                TRADE_OPEN_IMAGE_PATH,
                "Check.checkTradeIsOpen");
    }

    /**
     * Check messages yellow and red shrift, if find retur true
     * @param screenShot
     * @return
     */
    public boolean checkOtherPlayerNear(BufferedImage screenShot) {
       if (screenShot.getRGB(13, 840) == -65536 ||
               screenShot.getRGB(13, 857) == -65536 ||
               screenShot.getRGB(13, 865) == -65536 ||
               screenShot.getRGB(13, 871) == -65536 ||
               screenShot.getRGB(13, 877) == -65536) {
           return true;
       }
       if (screenShot.getRGB(11, 850) == -256 ||
               screenShot.getRGB(11, 840) == -256 ||
               screenShot.getRGB(11, 865) == -256 ||
               screenShot.getRGB(11, 871) == -256 ||
               screenShot.getRGB(11, 877) == -256) {
           return true;
       }
       return false;
    }


    public Optional<int[]>  checkDeathLabel() throws Exception {
        xy = Optional.empty();
        findFragmentInImage.setScreen(new int[]{600, 1000, 500, 900});
        xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\CheckDie\\");
        return xy;
    }

    public Optional<int[]>  checkCharSelectLabel() throws Exception {
        xy = Optional.empty();
        findFragmentInImage.setScreen(new int[]{600, 1000, 500, 900});
        xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\CharSelect\\");
        return xy;
    }


}
