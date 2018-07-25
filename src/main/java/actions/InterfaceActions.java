package actions;

import checks.CheckMsg;
import find_image.FindFragmentInImage;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
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

    private void pressOnImage(int[] area,
                              int sleepTime,
                              String pathFragment,
                              String methodName) throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(area);
        xy = findFragmentInImage.findImage(pathFragment);
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0], xy.get()[1]);
            Thread.sleep(sleepTime);
            logger.info(methodName + ": find and click." );
        }
    }

    public void pressOk() throws Exception {
        pressOnImage(new int[]{0, 1600, 0, 900},
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
            Thread.sleep(1000);
            mouse.mouseClick(xy.get()[0] + 20, xy.get()[1] + 80);
            Thread.sleep(5000);
            logger.info("pressOnKafra" + ": find and click." );
        }
    }

    public void pressNext() throws Exception{
        pressOnImage(new int[]{0, 1600, 0, 900},
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
        Thread.sleep(500);
        keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_E);
        Thread.sleep(1000);
    }

    public void putItemToKafra (String dirInventory, String dirLoot) throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{0, 800, 0, 900});
        Thread.sleep(500);
        keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_E);
        Thread.sleep(1000);

        xy = findFragmentInImage.findImage(dirInventory);
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0] + 5, xy.get()[1] + 5);
        }
        Thread.sleep(1000);

        xy = findFragmentInImage.findImage(dirLoot);
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0] + 5, xy.get()[1] + 5);
            mouse.pressLeft();
            moveCoursorToWarehouse();
            mouse.releaseLeft();
            Thread.sleep(500);
            keys.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(700);
        }

        keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_E);
    }

    public void moveCoursorToWarehouse() throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{0, 800, 0, 900});
        xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\HoneyInKafra\\");
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0], xy.get()[1]);
        }

    }
}
