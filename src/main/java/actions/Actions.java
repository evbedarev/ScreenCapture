package actions;

import checks.CheckMsg;
import checks.LocationCheck;
import find_image.FindFragmentInImage;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.take_loot.TakeLoot;
import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Optional;

public class Actions {
    private static volatile Actions instance;
    Keys keys = Keys.getInstance();
    Mouse mouse;
    CheckMsg checkMsg;
    Logger logger = Logger.getLogger(this.getClass());
    private TakeLoot[] loot;
    private TakeLoot[] usefulLoot;
    FindFragmentInImage findFragmentInImage = FindFragmentInImage.getInstance();

    private Actions() throws AWTException {
        mouse = Mouse.getInstance();
        checkMsg = new CheckMsg(logger);
    }

    static public Actions instance() throws AWTException {
        if (instance == null) {
            synchronized (Actions.class) {
                if (instance == null) {
                    instance = new Actions();
                }
            }
        }
        return instance;
    }

    public void initialize(TakeLoot[] loot,
                           TakeLoot[] usefulLoot) {
        this.loot = loot;
        this.usefulLoot = usefulLoot;
    }

    public void teleport(LocationCheck locationCheck) throws Exception {
        checkMsg.check();
        locationCheck.locationCheck();
        keys.keyPress(Prop.TELEPORT_KEY);
        SleepTime.sleep(300);
        keys.keyPress(KeyEvent.VK_ENTER);
        SleepTime.sleep(1000);
        LoggerSingle.logInfo(this.toString(), "Teleporting with locationCheck");
    }

    public void teleport() throws Exception {
        keys.keyPress(Prop.TELEPORT_KEY);
        SleepTime.sleep(300);
        keys.keyPress(KeyEvent.VK_ENTER);
        SleepTime.sleep(1000);
        LoggerSingle.logInfo(this.toString(), "Teleporting without locationCheck");
    }

    public void useWing() throws Exception {
        keys.keyPress(Prop.WING_KEY);
        SleepTime.sleep(5000);
    }


    public void sitDown () throws  InterruptedException {
        keys.keyPress(KeyEvent.VK_INSERT);
    }

    public void standUp() throws InterruptedException {
        sitDown();
    }

    public void castGuard() throws InterruptedException {
        keys.keyPress(Prop.GUARD);
        SleepTime.sleep(1000);
    }

    public void shieldSpell() throws InterruptedException {
        keys.keyPress(Prop.SHIELD_SPELL);
        SleepTime.sleep(1000);
    }

    public void castReflectShield() throws InterruptedException {
        keys.keyPress(Prop.REFLECT);
        SleepTime.sleep(1000);
    }

    public void drinkAwaikeningPotion() throws InterruptedException {
        keys.keyPress(Prop.AWAKENING_POTION);
    }

    public void stealZeny() throws InterruptedException {
        keys.keyPress(KeyEvent.VK_F4);
    }

    public void useBash() throws InterruptedException {
        keys.keyPress(KeyEvent.VK_F8);
    }


    public void stepAside(LocationCheck locationCheck, int[] radiuses) throws Exception {
        locationCheck.locationCheck();
        double t = 2 * Math.PI * Math.random();
        double minRadius = radiuses[0];
        double maxRadius = radiuses[1];

        double x = minRadius * Math.cos(t);
        double x1 = maxRadius * Math.cos(t);

        double mediumX = x + Math.random()*(x1 - x);
        double mediumR = mediumX/Math.cos(t);
        double mediumY = mediumR * Math.sin(t);

        mouse.mouseClick(800 + (int) Math.round(mediumX),
                450 + (int) Math.round(mediumY));
        SleepTime.sleep(1000);
        locationCheck.locationCheck();
    }

    public void pickUpLoot() throws Exception {
        for (TakeLoot takeLoot: loot) {
            takeLoot.pickUp();
        }
    }

    public void pickUpCard() throws Exception {
        for (TakeLoot takeLoot: usefulLoot) {
            takeLoot.pickUp();
        }
    }

    public void dropItem(String dirInventory, String dirLoot) throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{0, 800, 0, 900});
        SleepTime.sleep(500);
        keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_E);
        SleepTime.sleep(500);

        xy = findFragmentInImage.findImage(dirInventory);
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0], xy.get()[1]);
        }
        SleepTime.sleep(1000);

        xy = findFragmentInImage.findImage(dirLoot);
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0], xy.get()[1]);
            mouse.pressLeft();
            mouse.mouseMove(1000, 450);
            mouse.releaseLeft();
            SleepTime.sleep(500);
            keys.keyPress(KeyEvent.VK_ENTER);
            SleepTime.sleep(700);
        }

        keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_E);
    }

    public void heal() throws Exception {
        keys.keyPress(Prop.HEAL_KEY);
        SleepTime.sleep(500);
        mouse.mouseClick(800, 400);
    }


}
