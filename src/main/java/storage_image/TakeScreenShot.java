package storage_image;

import actions.SleepTime;
import logic.screen_shot.Capture;

import java.awt.*;

public class TakeScreenShot {
    public static void main(String[] args) throws InterruptedException, AWTException {
        SleepTime.sleep(1000);
        takeScreenShot();
    }

    private static void takeScreenShot() throws AWTException {
        Capture capture = new Capture();
        for (int i = 1; i < 20; i++)
            capture.takeScreenShotToFile("somefile" + i);
    }
}
