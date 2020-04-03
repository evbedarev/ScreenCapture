package actions.kafra_actions.get_resources;

import actions.InterfaceActions;
import actions.SleepTime;
import checks.Check;
import find_image.FindFragmentInImage;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.screen_shot.ScreenShot;
import main.Prop;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class GetResourcesGunslinger implements GetResources{
    private Check check = Check.getInstance();
    private InterfaceActions interfaceActions = InterfaceActions.getInstance();
    private FindFragmentInImage findFragmentInImage = FindFragmentInImage.getInstance();
    private Mouse mouse = Mouse.getInstance();
    private Keys keys = Keys.getInstance();
    boolean wasScrolled;

    public GetResourcesGunslinger() throws AWTException {
    }

    private boolean haveBullets(BufferedImage image) {
        if (image.getRGB(1370,880) == -1) {
            LoggerSingle.logInfo(this.getClass().toString(), "No bullets for shot, sleeping");
            return false;
        }
        return true;
    }

    @Override
    public void get() throws Exception {
        BufferedImage screenShot = Prop.context.getBean(ScreenShot.class).pop();
        wasScrolled = false;
        interfaceActions.pressOnKafra();
        interfaceActions.pressNext();
        interfaceActions.openWarehouse();
        interfaceActions.pressOk();
        interfaceActions.pressClose();
        interfaceActions.openInventory();
        findFragmentInImage.setScreen(new int[]{0,1600, 0, 900});
        Optional<int[]> xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\MarkerInventory\\2\\");
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0] + 5, xy.get()[1] + 5);
        }

        if (!check.havePotion(screenShot)) {
            getWhitePotions();
        }
        if (!check.haveButWings(screenShot)) {
            System.out.println("No butterfly wings");
            getButWings();
        }

        if (!check.haveWings(screenShot)) {
            getWings();
        }
        if (!haveBullets(screenShot)) {
            getBullets();
        }
        interfaceActions.closeInventory();
        interfaceActions.pressClose();
        SleepTime.sleep(5000);
    }

    private void getBullets() throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{0,1600, 0, 900});
        xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\activAmmunition\\");
        if (!xy.isPresent()) {
            xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\ammunition\\");
            if (xy.isPresent()) {
                mouse.mouseClick(xy.get()[0] + 5, xy.get()[1] + 5);
            }
        }
        SleepTime.sleep(1000);
        xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\Bullets\\");
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0] + 5, xy.get()[1] + 5);
            mouse.pressLeft();
            SleepTime.sleep(500);
            interfaceActions.moveCoursorToInventory();
            SleepTime.sleep(500);
            mouse.releaseLeft();
            SleepTime.sleep(500);
            keys.keyPress(KeyEvent.VK_3);
            SleepTime.sleep(200);
            keys.keyPress(KeyEvent.VK_0);
            SleepTime.sleep(200);
            keys.keyPress(KeyEvent.VK_0);
            SleepTime.sleep(200);
            keys.keyPress(KeyEvent.VK_0);
            SleepTime.sleep(200);
            keys.keyPress(KeyEvent.VK_ENTER);
            SleepTime.sleep(700);
            keys.keyPress(KeyEvent.VK_F2);
        } else {
            SleepTime.loopSleep();
        }
    }
    private void scrollDownInKafra() throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{800,1600, 0, 900});
        for (int i = 0; i < 8; i++) {
            xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\downArrowKafra\\");
            if (xy.isPresent()) {
                mouse.mouseClick(xy.get()[0] + 5, xy.get()[1] + 5);
            }
            SleepTime.sleep(500);
        }
    }
    public void getButWings() throws Exception {
        if (!wasScrolled) {
            scrollDownInKafra();
            wasScrolled = true;
        }
        findFragmentInImage.setScreen(new int[]{0,1600, 0, 900});
        Optional<int[]> xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\ButterflyWing\\");
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0] + 5, xy.get()[1] + 5);
            mouse.pressLeft();
            SleepTime.sleep(500);
            interfaceActions.moveCoursorToInventory();
            SleepTime.sleep(500);
            mouse.releaseLeft();
            SleepTime.sleep(500);
            keys.keyPress(KeyEvent.VK_7);
            SleepTime.sleep(200);
            keys.keyPress(KeyEvent.VK_ENTER);
            SleepTime.sleep(700);
            keys.keyPress(KeyEvent.VK_F2);
        } else {
            SleepTime.loopSleep();
        }
    }
    public void getWings() throws Exception {
        if (!wasScrolled)
            scrollDownInKafra();
        findFragmentInImage.setScreen(new int[]{0,1600, 0, 900});
        Optional<int[]> xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\Wing\\");
        SleepTime.sleep(1000);
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0] + 5, xy.get()[1] + 5);
            mouse.pressLeft();
            SleepTime.sleep(500);
            interfaceActions.moveCoursorToInventory();
            SleepTime.sleep(500);
            mouse.releaseLeft();
            SleepTime.sleep(500);
            keys.keyPress(KeyEvent.VK_4);
            SleepTime.sleep(200);
            keys.keyPress(KeyEvent.VK_0);
            SleepTime.sleep(200);
            keys.keyPress(KeyEvent.VK_ENTER);
        } else {
            System.out.println("not find");
            SleepTime.loopSleep();
        }
    }

    public void getWhitePotions() throws Exception {
        Optional<int[]> xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\WhitePotion\\");
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0] + 5, xy.get()[1] + 5);
            mouse.pressLeft();
            SleepTime.sleep(500);
            interfaceActions.moveCoursorToInventory();
            SleepTime.sleep(500);
            mouse.releaseLeft();
            SleepTime.sleep(500);
            keys.keyPress(KeyEvent.VK_1);
            SleepTime.sleep(200);
            keys.keyPress(KeyEvent.VK_0);
            SleepTime.sleep(200);
            keys.keyPress(KeyEvent.VK_ENTER);
        } else {
            SleepTime.loopSleep();
        }
    }
}
