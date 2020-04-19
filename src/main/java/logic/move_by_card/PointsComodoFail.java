package logic.move_by_card;

import logic.move_by_card.points_operation.IteratorList;
import logic.move_by_card.points_operation.PointsAbstr;

import java.util.ArrayList;
import java.util.List;

public class PointsComodoFail extends PointsAbstr {

    public PointsComodoFail() {
        points.add(new int[] {1471, 60});
        points.add(new int[] {1474, 62});
        points.add(new int[] {1476, 40});
    }

    @Override
    public IteratorList getIterator() {
        return points.getIterator();
    }

}
