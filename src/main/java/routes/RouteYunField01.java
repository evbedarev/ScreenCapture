package routes;
import actions.SleepTime;
import checks.LocationCheck;
import checks.location.YunField01;
import checks.location.YunField12;
import logic.screen_shot.ScreenShotStack;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RouteYunField01 extends RouteModel{

    public RouteYunField01() throws AWTException {
        super();
    }


    public void tpUntil() throws Exception {
        LocationCheck locationCheck = new LocationCheck(new YunField01());
        BufferedImage screenshot = Prop.context.getBean(ScreenShotStack.class).pop();
            while (!checkLocationTP(screenshot, new int[] {1473,1486,78,89})) {
            locationCheck.locationCheck();
            actions.teleport();
            SleepTime.sleep(1000);
            screenshot = Prop.context.getBean(ScreenShotStack.class).pop();
            if (checkDie.check()) break;
        }

            System.out.println("on location");
        moveDownSlowly( new Route(new int[] {800,582}, new int[] {1525,90}));

            System.out.println("on location");

        verifyMap = new YunField12();
            while (!verifyMap.onDesiredLocation()) {
            mouse.mouseClick(480, 470);
            SleepTime.sleep(500);
            if (verifyMap.onDesiredLocation()) break;
            if (checkDie.check()) break;
        }
    }
}
