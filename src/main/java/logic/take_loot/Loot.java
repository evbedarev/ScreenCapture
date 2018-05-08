package logic.take_loot;

import find_image.FindImageHard;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.Capture;

import java.awt.*;
import java.io.IOException;

public class Loot implements TakeLoot {

    final Capture capture;
    final Mouse mouse;
    final Keys keys;
    final FindImageHard findImageHard;

    public Loot() throws AWTException {
        capture = new Capture();
        mouse = new Mouse();
        keys = new Keys();
        findImageHard = new FindImageHard();
    }
    @Override
    public void takeLoot() throws
            IOException,
            AWTException,
            InterruptedException {

    }
}
