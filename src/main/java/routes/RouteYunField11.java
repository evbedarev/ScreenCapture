package routes;

import actions.SleepTime;
import checks.LocationCheck;
import checks.location.YunField07;
import checks.location.YunField11;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RouteYunField11 extends RouteModel {

    public RouteYunField11() throws AWTException {
        super();
    }

    public void tpUntil() throws Exception {
        LocationCheck locationCheck = new LocationCheck(new YunField11());
        BufferedImage screenshot = capture.takeScreenShot();
        boolean Location1 = checkLocationTP(screenshot, new int[] {1490,1500,93,121});

        while (!Location1) {
            locationCheck.locationCheck();
            actions.teleport();
            SleepTime.sleep(2000);
            screenshot = capture.takeScreenShot();
            Location1 = checkLocationTP(screenshot, new int[] {1490,1500,93,121});
        }

        System.out.println("on location");
        moveLeftSlowly(new Route(new int[] {},  new int[] {1489,0}));

        System.out.println("on location");
        moveUp(new Route(new int[] {},  new int[] {0,50}));

        verifyMap = new YunField07();
        while (!verifyMap.onDesiredLocation()) {
            mouse.mouseClick(600, 461);
            SleepTime.sleep(500);
        }
    }
}
