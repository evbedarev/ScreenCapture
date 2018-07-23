package routes;

import checks.LocationCheck;
import checks.location.Aldebaran;
import checks.location.YunField01;
import checks.location.YunField07;
import checks.location.YunField11;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RouteAldebaran extends RouteModel {

    public RouteAldebaran() throws AWTException {
        super();
    }

    public void tpUntil() throws Exception {
        LocationCheck locationCheck = new LocationCheck(new Aldebaran());
        BufferedImage screenshot = capture.takeScreenShot();
        boolean Location1 = checkLocationTP(screenshot, new int[] {1507,1517,57,62});

        while (!Location1) {
            locationCheck.locationCheck();
            actions.teleport();
            Thread.sleep(2000);
            screenshot = capture.takeScreenShot();
            Location1 = checkLocationTP(screenshot, new int[] {1490,1500,93,121});
        }


        System.out.println("on location");
        moveUp(new Route(new int[] {},  new int[] {0,57}));

        verifyMap = new YunField01();

        while (!verifyMap.onDesiredLocation()) {
            mouse.mouseClick(1000, 461);
            Thread.sleep(500);
        }
    }
}
