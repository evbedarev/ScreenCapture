package routes;
import checks.location.YunField11;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RouteYunField12 extends RouteModel {


    public RouteYunField12() throws AWTException {
        super();
    }

    public void tpUntil() throws Exception {
        BufferedImage screenshot = capture.takeScreenShot();
        boolean Location1 = checkLocationTP(screenshot, new int[] {1466,1474,61,76});

        while (!Location1) {
            actions.teleport();
            Thread.sleep(2000);
            screenshot = capture.takeScreenShot();
            Location1 = checkLocationTP(screenshot, new int[] {1466,1475,61,76});
        }

        System.out.println("on location");
        moveUp(new Route(new int[] {800,582},  new int[] {0,62}));

        System.out.println("on location");
        moveLeft(new Route(new int[] {800,582},  new int[] {1466,0}));


        verifyMap = new YunField11();
        while (!verifyMap.onDesiredLocation()) {
            mouse.mouseClick(805, 400);
            Thread.sleep(500);
        }
    }
}
