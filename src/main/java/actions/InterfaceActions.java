package actions;

import checks.CheckMsg;
import find_image.FindFragmentInImage;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class InterfaceActions {
    private static volatile InterfaceActions instance;
    Keys keys = Keys.getInstance();
    Mouse mouse;
    CheckMsg checkMsg;
    Logger logger = Logger.getLogger(this.getClass());
    FindFragmentInImage findFragmentInImage = FindFragmentInImage.getInstance();

    private InterfaceActions() throws AWTException {
        mouse = Mouse.getInstance();
    }

    public static InterfaceActions getInstance() throws AWTException {
        if (instance == null) {
            synchronized (InterfaceActions.class) {
                if (instance == null) {
                    instance = new InterfaceActions();
                }
            }
        }
        return instance;
    }

    private boolean pressOnImage(int[] area,
                              int sleepTime,
                              String pathFragment,
                              String methodName) throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(area);
        xy = findFragmentInImage.findImage(pathFragment);
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0], xy.get()[1]);
            SleepTime.sleep(sleepTime);
            logger.info(methodName + ": find and click." );
            return true;
        }
        return false;
    }

    private boolean pressOnImage(BufferedImage screenShot,
                              int[] area,
                              int sleepTime,
                              String pathFragment,
                              String methodName) throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(area);
        xy = findFragmentInImage.findImage(screenShot, pathFragment);
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0], xy.get()[1]);
            SleepTime.sleep(sleepTime);
            logger.info(methodName + ": find and click." );
            return true;
        }

        return false;
    }

    public void pressOk() throws Exception {
        pressOnImage(new int[]{0, 1600, 0, 900},
                5000,
                Prop.ROOT_DIR + "Interface\\Ok\\",
                "PressOk");
    }

    public boolean pressOk(BufferedImage screenShot) throws Exception {
        return pressOnImage(screenShot,
                new int[]{0, 1600, 0, 900},
                5000,
                Prop.ROOT_DIR + "Interface\\Ok\\",
                "PressOk");
    }

    public void pressReturnToLastSavepoint() throws Exception {
        pressOnImage(new int[]{0, 1600, 0, 900},
                2000,
                Prop.ROOT_DIR + "Interface\\CheckDie\\",
                "pressReturnToLastSavepoint");
    }

    public void pressOnKafra() throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{0, 1600, 0, 900});
        xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\Kafra\\");
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0] + 20, xy.get()[1] + 80);
            SleepTime.sleep(1000);
            mouse.mouseClick(xy.get()[0] + 20, xy.get()[1] + 80);
            SleepTime.sleep(5000);
            logger.info("pressOnKafra" + ": find and click." );
        }
    }

    public void pressNext() throws Exception{
        pressOnImage(new int[]{0, 1600, 0, 900},
                2000,
                Prop.ROOT_DIR + "Interface\\Next\\",
                "PressNext");
    }

    public boolean pressNext(BufferedImage screenShot) throws Exception{
        return pressOnImage(screenShot,
                new int[]{0, 1600, 0, 900},
                2000,
                Prop.ROOT_DIR + "Interface\\Next\\",
                "PressNext");
    }

    public void openWarehouse() throws Exception{
        pressOnImage(new int[]{0, 1600, 0, 900},
                2000,
                Prop.ROOT_DIR + "Interface\\OpenWarehouse\\",
                "OpenWarehouse");
    }

    public boolean pressClose(BufferedImage screenShot) throws Exception{
        return pressOnImage(screenShot,
                new int[]{0, 1600, 0, 900},
                2000,
                Prop.ROOT_DIR + "Interface\\Close\\",
                "PressClose");
    }

    public void pressClose() throws Exception{
        pressOnImage(new int[]{0, 1600, 0, 900},
                2000,
                Prop.ROOT_DIR + "Interface\\Close\\",
                "PressClose");
    }

    public void pressKafraTeleport() throws Exception{
        pressOnImage(new int[]{0, 1600, 0, 900},
                2000,
                Prop.ROOT_DIR + "Interface\\KafraTeleport\\",
                "KafraTeleport");
    }

    public void pressDownArrow() throws Exception{
        pressOnImage(new int[]{0, 1600, 0, 900},
                2000,
                Prop.ROOT_DIR + "Interface\\DownArrow\\",
                "DownArrow");
    }

    public void pressCmdField07() throws Exception{
        pressOnImage(new int[]{0, 1600, 0, 900},
                2000,
                Prop.ROOT_DIR + "Interface\\TeleportCmdField07\\",
                "TeleportCmdField07");
    }

    public void pressGefField10() throws Exception{
        pressOnImage(new int[]{0, 1600, 0, 900},
                2000,
                Prop.ROOT_DIR + "Interface\\TeleportGefField10\\",
                "TeleportGefField10");
    }

    public void openInventory() throws InterruptedException {
        SleepTime.sleep(500);
        keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_E);
        SleepTime.sleep(1000);
    }

    public void closeInventory() throws InterruptedException {
        openInventory();
    }

    public void putItemToKafra (String dirInventory, String dirLoot) throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{0, 800, 0, 900});
        xy = findFragmentInImage.findImage(dirInventory);
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0] + 5, xy.get()[1] + 5);
        }
        SleepTime.sleep(1000);

        xy = findFragmentInImage.findImage(dirLoot);
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0] + 5, xy.get()[1] + 5);
            mouse.pressLeft();
            moveCoursorToWarehouse();
            mouse.releaseLeft();
            SleepTime.sleep(500);
            keys.keyPress(KeyEvent.VK_ENTER);
            SleepTime.sleep(700);
        }
    }

    public void moveCoursorToWarehouse() throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{0, 800, 0, 900});
        xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\HoneyInKafra\\");
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0], xy.get()[1]);
        }
    }

    public boolean goToCharSelect() throws Exception {
        int cnt = 0;
        while (!pressCharSelect()) {
            cnt++;
            SleepTime.sleep(2000);
            if (cnt > 4) {
                break;
            }
        }
        return checkInCharSelect();
    }

    private boolean checkInCharSelect() throws Exception {
       return pressOnImage(new int[]{0, 1600, 0, 900},
                1000,
                Prop.ROOT_DIR + "Interface\\CheckInCharSelect\\",
                "CheckInCharSelect");
    }

    public boolean pressCharSelect() throws Exception {
        boolean returnValue = false;
        keys.keyPress(KeyEvent.VK_ESCAPE);
        Thread.sleep(2000);
        returnValue = pressOnImage(new int[]{0, 1600, 0, 900},
                1000,
                Prop.ROOT_DIR + "Interface\\CharSelect\\",
                "CharSelect");
        Thread.sleep(4000);
        return returnValue;
    }
}
