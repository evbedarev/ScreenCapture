package actions;

import key_and_mouse.Keys;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Actions {
    static private Actions instance;
    Keys keys;
    public Actions() throws AWTException {
        keys = new Keys();
    }

    public void teleport() throws InterruptedException {
        keys.keyPress(KeyEvent.VK_F2);
        Thread.sleep(700);
        keys.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
    }

    public void sitDown (){

    }

    static public Actions instance() throws AWTException {
        if (instance == null) {
            instance = new Actions();
        }
        return instance;
    }
}
