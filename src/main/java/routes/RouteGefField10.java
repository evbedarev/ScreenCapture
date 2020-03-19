package routes;
import actions.SleepTime;
import checks.LocationCheck;
import checks.location.GefField10;
import checks.location.GefField11;
import logger.LoggerSingle;
import logic.screen_shot.ScreenShotStack;
import main.Prop;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RouteGefField10 extends RouteModel{

    public RouteGefField10() throws AWTException {
        super();
    }


    public void tpUntil() throws Exception {
        LocationCheck locationCheck = new LocationCheck(new GefField10());
        BufferedImage screenshot = Prop.context.getBean(ScreenShotStack.class).pop();

        while (!checkLocationTP(screenshot, new int[] {1478,1492,134,146})) {
            locationCheck.locationCheck();
            actions.teleport();
            SleepTime.sleep(1000);
            screenshot = Prop.context.getBean(ScreenShotStack.class).pop();
            if (checkDie.check()) break;
        }

        System.out.println("on location");
        moveRightSlowly(new Route(new int[] {}, new int[] {1494,0}));

        System.out.println("on location");
        moveDown( new Route(new int[] {}, new int[] {0,162}));


        verifyMap = new GefField11();
        while (!verifyMap.onDesiredLocation()) {
            mouse.mouseClick(600, 470);
            SleepTime.sleep(500);
            LoggerSingle.logInfo(this.toString(), "Check location GefField11");
            if (verifyMap.onDesiredLocation()) break;
            if (checkDie.check()) break;
        }
        LoggerSingle.logInfo(this.toString(), "Location GefField11");
    }

}
