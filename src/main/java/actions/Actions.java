package actions;

import checks.CheckMsg;
import checks.LocationCheck;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.take_loot.TakeLoot;
import main.Prop;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Actions {
    private static volatile Actions instance;
    Keys keys;
    Mouse mouse;
    CheckMsg checkMsg;
    Logger logger = Logger.getLogger(this.getClass());
    private TakeLoot[] loot;
    private TakeLoot[] usefulLoot;

    private Actions() throws AWTException {
        keys = new Keys();
        mouse = new Mouse();
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

}
