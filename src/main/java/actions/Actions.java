package actions;

import key_and_mouse.Keys;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Actions {
    static private Actions instance;
    Keys keys;

    private static final int GUARD = KeyEvent.VK_F5;
    private static final int REFLECT_SHIELD = KeyEvent.VK_F6;
    private static final int AWAKENING_POTION = KeyEvent.VK_F9;
    private static final int DEFENDER = KeyEvent.VK_F7;

    public Actions() throws AWTException {
        keys = new Keys();
    }

    public void teleport() throws InterruptedException {
        keys.keyPress(KeyEvent.VK_F2);
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
        keys.keyPress(GUARD);
        Thread.sleep(1000);
    }

    public void castReflectShield() throws InterruptedException {
        keys.keyPress(REFLECT_SHIELD);
        Thread.sleep(1000);
    }

    public void drinkAwaikeningPotion() throws InterruptedException {
        keys.keyPress(AWAKENING_POTION);
    }

    static public Actions instance() throws AWTException {
        if (instance == null) {
            instance = new Actions();
        }
        return instance;
    }
}
