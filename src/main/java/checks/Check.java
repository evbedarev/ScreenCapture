package checks;

import actions.InterfaceActions;
import actions.SleepTime;
import find_image.FindFragmentInImage;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import main.Prop;
import org.apache.log4j.Logger;
import java.awt.*;
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

    public void checkResources(BufferedImage screenShot) {
        if ((screenShot.getRGB(1340, 881) == -1) ||
                (screenShot.getRGB(1399, 887) == -1) ||
                (screenShot.getRGB(1364, 875) == -1)) {
            while (true) {
                SleepTime.sleep(5000);
            }
        }
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
