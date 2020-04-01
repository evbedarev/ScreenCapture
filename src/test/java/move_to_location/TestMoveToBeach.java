package move_to_location;

import actions.SleepTime;
import checks.LocationCheck;
import checks.location.VerifyMap;
import key_and_mouse.Mouse;
import logic.LogicBeachDun03;
import logic.move_by_card.MoveToLocation;
import logic.move_by_card.PointsComodo;
import logic.move_by_card.PointsComodo2;
import logic.move_by_card.PointsPapuchichaForest;
import main.Prop;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;

public class TestMoveToBeach {
    MoveToLocation moveToLocation;
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("main");
    Mouse mouse = Mouse.getInstance();

    public TestMoveToBeach() throws AWTException {
    }

    @Before
    public void before() throws Exception {
        Prop.initialize();
        Prop.takeScreenShotThread.start();
        Thread.sleep(2000);

    }

//    @Test
    public void testCmdField01() throws Exception {
        SleepTime.sleep(2000);
        LocationCheck locationCheck = new LocationCheck((VerifyMap) context.getBean("cmdField01"));
        locationCheck.locationCheck();
    }

    @Test
    public void moveInComodo() throws Exception {
        moveToLocation = new MoveToLocation(new LogicBeachDun03(), new PointsComodo());
        moveToLocation.move();
        mouse.mouseClick(1240,790);
        Thread.sleep(2000);
        mouse.mouseClick(1340,120);
        Thread.sleep(4000);
        moveToLocation.lastAction(new int[]{965, 429}, (VerifyMap) context.getBean("beachDun03"));
        Thread.sleep(2000);
        moveInComodoDung();
        Thread.sleep(4000);
        movePapuchichaaForest();
    }
//    @Test
    public void moveInComodoDung() throws Exception {
        moveToLocation = new MoveToLocation(new LogicBeachDun03(), new PointsComodo2());
        moveToLocation.move();
        mouse.mouseClick(1090,850);
        Thread.sleep(2000);
        mouse.mouseClick(1200,260);
        Thread.sleep(4000);
        moveToLocation.lastAction(new int[]{910, 540}, (VerifyMap) context.getBean("cmdField01"));
        SleepTime.sleep(1000);
    }

//    @Test
    public void movePapuchichaaForest() throws Exception {
        moveToLocation = new MoveToLocation(new LogicBeachDun03(), new PointsPapuchichaForest());
        moveToLocation.move();
        mouse.mouseClick(1090,600);
        Thread.sleep(2000);
        mouse.mouseClick(470,700);
        Thread.sleep(4000);
        moveToLocation.lastAction(new int[]{660, 560}, (VerifyMap) context.getBean("cmdField02"));

    }
}