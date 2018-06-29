package actions;

import checks.LocationCheck;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.take_loot.TakeLoot;
import main.Prop;

import java.awt.*;
import java.awt.event.KeyEvent;

import static main.Prop.*;

public class Actions {
    static private Actions instance;
    Keys keys;
    Mouse mouse;

    public Actions() throws AWTException {
        keys = new Keys();
        mouse = new Mouse();
    }

    static public Actions instance() throws AWTException {
        if (instance == null) {
            instance = new Actions();
        }
        return instance;
    }

    public void teleport() throws InterruptedException {
        keys.keyPress(Prop.TELEPORT_KEY);
        Thread.sleep(700);
        keys.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
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

    public void pickUpLoot(TakeLoot[] loot) throws Exception {
        for (TakeLoot takeLoot: loot) {
            takeLoot.pickUp();
        }
    }

    public void pickUpCard(TakeLoot[] usefulLoot) throws Exception {
        for (TakeLoot takeLoot: usefulLoot) {
            takeLoot.pickUp();
        }
    }

}
