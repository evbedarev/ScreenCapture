package move_to_location;

import actions.SleepTime;
import logic.LogicBeachDun03;
import logic.move_by_card.MoveToLocation;
import logic.move_by_card.PointsComodo;
import logic.move_by_card.PointsComodo2;
import logic.move_by_card.PointsPapuchichaForest;
import main.Prop;
import org.junit.Before;
import org.junit.Test;

public class TestMoveToBeach {
    MoveToLocation moveToLocation;
    @Before
    public void before() throws Exception {
        Prop.initialize();
        Prop.takeScreenShotThread.start();
        Thread.sleep(2000);
    }

    @Test
    public void moveInComodo() throws Exception {
        moveToLocation = new MoveToLocation(new LogicBeachDun03(), new PointsComodo());
        moveToLocation.move();
        moveToLocation.lastAction(new int[]{965, 429});
    }

    public void moveInComodoDung() throws Exception {
        moveToLocation = new MoveToLocation(new LogicBeachDun03(), new PointsComodo2());
        moveToLocation.move();
        moveToLocation.lastAction(new int[]{910, 540});
        SleepTime.sleep(1000);
        moveToLocation.lastAction(new int[]{910, 540});
    }

    @Test
    public void movePapuchichaaForest() throws Exception {
        moveToLocation = new MoveToLocation(new LogicBeachDun03(), new PointsPapuchichaForest());
        moveToLocation.move();
        moveToLocation.lastAction(new int[]{660, 560});
        SleepTime.sleep(1000);
        moveToLocation.lastAction(new int[]{660, 560});
    }
}
