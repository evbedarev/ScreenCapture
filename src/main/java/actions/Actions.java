package actions;

import checks.CheckMsg;
import checks.LocationCheck;
import find_image.FindFragmentInImage;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
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
        Thread.sleep(700);
        keys.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
    }

    public void teleport() throws Exception {
        keys.keyPress(Prop.TELEPORT_KEY);
        Thread.sleep(700);
        keys.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
    }

    public void useWing() throws Exception {
        keys.keyPress(Prop.WING_KEY);
        Thread.sleep(5000);
    }


    public void sitDown () throws  InterruptedException {
        keys.keyPress(KeyEvent.VK_INSERT);
    }

    public void standUp() throws InterruptedException {
        sitDown();
    }

    public void castGuard() throws InterruptedException {
        keys.keyPress(Prop.GUARD);
        Thread.sleep(1000);
    }

    public void castReflectShield() throws InterruptedException {
        keys.keyPress(Prop.REFLECT);
        Thread.sleep(1000);
    }

    public void drinkAwaikeningPotion() throws InterruptedException {
        keys.keyPress(Prop.AWAKENING_POTION);
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
        Thread.sleep(1000);
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
        Thread.sleep(500);
        keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_E);
        Thread.sleep(500);

        xy = findFragmentInImage.findImage(dirInventory);
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0], xy.get()[1]);
        }
        Thread.sleep(1000);

        xy = findFragmentInImage.findImage(dirLoot);
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0], xy.get()[1]);
            mouse.pressLeft();
            mouse.mouseMove(1000, 450);
            mouse.releaseLeft();
            Thread.sleep(500);
            keys.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(700);
        }

        keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_E);
    }


}
