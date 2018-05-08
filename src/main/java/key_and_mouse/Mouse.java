package key_and_mouse;

import java.awt.*;
import java.awt.event.InputEvent;

public class Mouse {
    Robot robot;

    public Mouse() throws AWTException {
        robot = new Robot();
    }

    public void mouseClick(int x, int y)
            throws AWTException, InterruptedException {
        mouseClick(x, y, InputEvent.BUTTON1_MASK, 100);
    }

    public void mouseClick(int x, int y, int button_mask, int sleepTime)
            throws AWTException, InterruptedException {
        robot.mouseMove(x,y);
        robot.mousePress(button_mask);
        Thread.sleep(sleepTime);
        robot.mouseRelease(button_mask);
    }
}
