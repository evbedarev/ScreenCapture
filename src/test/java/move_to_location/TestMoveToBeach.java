package move_to_location;

import logic.LogicBeachDun03;
import logic.move_by_card.MoveToLocation;
import logic.move_by_card.PointsComodo;
import org.junit.Test;

import java.awt.*;

public class TestMoveToBeach {
    @Test
    public void moveInComodo() throws Exception {
        MoveToLocation moveToLocation = new MoveToLocation(new LogicBeachDun03(), new PointsComodo());
        moveToLocation.move();
        moveToLocation.lastAction(new int[]{965, 429});
    }
}
