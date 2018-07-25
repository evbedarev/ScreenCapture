package routes;
import checks.LocationCheck;
import checks.location.GefField10;
import checks.location.GefField11;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RouteGefField10 extends RouteModel{

    public RouteGefField10() throws AWTException {
        super();
    }


    public void tpUntil() throws Exception {
        LocationCheck locationCheck = new LocationCheck(new GefField10());
        BufferedImage screenshot = capture.takeScreenShot();

        while (!checkLocationTP(screenshot, new int[] {1478,1492,134,146})) {
            locationCheck.locationCheck();
            actions.teleport();
            Thread.sleep(1000);
            screenshot = capture.takeScreenShot();
            if (checkDie.check()) break;
        }

        System.out.println("on location");
        moveRightSlowly(new Route(new int[] {}, new int[] {1494,0}));

        System.out.println("on location");
        moveDown( new Route(new int[] {}, new int[] {0,162}));


        verifyMap = new GefField11();
        while (!verifyMap.onDesiredLocation()) {
            mouse.mouseClick(600, 470);
            Thread.sleep(500);
            if (verifyMap.onDesiredLocation()) break;
            if (checkDie.check()) break;
        }
    }

}
