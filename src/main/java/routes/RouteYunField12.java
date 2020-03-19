package routes;
import actions.SleepTime;
import checks.LocationCheck;
import checks.location.YunField11;
import checks.location.YunField12;
import logic.screen_shot.ScreenShotStack;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RouteYunField12 extends RouteModel {


    public RouteYunField12() throws AWTException {
        super();
    }

    public void tpUntil() throws Exception {
        LocationCheck locationCheck = new LocationCheck(new YunField12());
        BufferedImage screenshot = Prop.context.getBean(ScreenShotStack.class).pop();
        while (!checkLocationTP(screenshot, new int[] {1495,1502,69,82})) {
            locationCheck.locationCheck();
            actions.teleport();
            SleepTime.sleep(1000);
            screenshot = Prop.context.getBean(ScreenShotStack.class).pop();
            if (checkDie.check()) break;
        }

        System.out.println("on location");
        moveDown(new Route(new int[] {}, new int[] {0,82}));

        System.out.println("on location");
        moveLeft(new Route(new int[] {}, new int[] {1476,0}));

        System.out.println("on location");
        moveDown(new Route(new int[] {}, new int[] {0,95}));

        System.out.println("on location");
        moveLeft(new Route(new int[] {}, new int[] {1465,0}));


        verifyMap = new YunField11();
        while (!verifyMap.onDesiredLocation()) {
            mouse.mouseClick(800, 600);
            SleepTime.sleep(500);
            if (verifyMap.onDesiredLocation()) break;
            if (checkDie.check()) break;
        }
    }

//    LocationCheck locationCheck = new LocationCheck(new YunField01());
//    BufferedImage screenshot = Prop.context.getBean(ScreenShotStack.class).pop();
//        while (!checkLocationTP(screenshot, new int[] {1495,1502,69,82})) {
//        locationCheck.locationCheck();
//        actions.teleport();
//        SleepTime.sleep(1000);
//        screenshot = Prop.context.getBean(ScreenShotStack.class).pop();
//        if (checkDie.check()) break;
//    }
//
//        System.out.println("on location");
//    moveDown(new Route(new int[] {}, new int[] {0,82}));
//
//        System.out.println("on location");
//    moveLeft(new Route(new int[] {}, new int[] {1476,0}));
//
//        System.out.println("on location");
//    moveDown(new Route(new int[] {}, new int[] {0,95}));
//
//        System.out.println("on location");
//    moveLeft(new Route(new int[] {}, new int[] {1466,0}));
//
//    verifyMap = new YunField12();
//        while (!verifyMap.onDesiredLocation()) {
//        mouse.mouseClick(800, 600);
//        SleepTime.sleep(500);
//        if (verifyMap.onDesiredLocation()) break;
//        if (checkDie.check()) break;
//    }
}
