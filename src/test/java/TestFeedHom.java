import actions.InterfaceActions;
import actions.SleepTime;
import key_and_mouse.Mouse;
import logic.move_by_card.MoveToLocation;
import main.Prop;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;
import java.util.Random;

public class TestFeedHom {
    MoveToLocation moveToLocation;
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("main");
    Mouse mouse = Mouse.getInstance();

    public TestFeedHom() throws AWTException {
    }

    @Before
    public void before() throws Exception {
        Prop.initialize();
//        Prop.takeScreenShotThread.start();
        Thread.sleep(2000);
    }

    @Test
    public void feedHomFind() throws Exception {
        Random random = new Random();
        SleepTime.sleep(1000);
        InterfaceActions interfaceActions = InterfaceActions.getInstance();
        while (true) {
            interfaceActions.feedHomStatic();
            SleepTime.sleep(random.nextInt(100000));
        }
        //104,309
        //target 107,323
        //colour for feed is: -65536
    }
}
