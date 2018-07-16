package key_and_mouse;

import java.awt.*;
import java.awt.event.InputEvent;

public class Mouse {
    Robot robot;

    public Mouse() throws AWTException {
        robot = new Robot();
    }

    public synchronized void mouseClick(int x, int y)
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
    public void leftClick() throws InterruptedException {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(50);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void mouseMove(int x, int y) {
        robot.mouseMove(x,y);
    }
}
