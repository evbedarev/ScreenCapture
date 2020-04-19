package logic.move_by_card;

import logic.move_by_card.points_operation.IteratorList;
import logic.move_by_card.points_operation.PointsAbstr;

public class PointsPapuchichaForestFail extends PointsAbstr {

    public PointsPapuchichaForestFail() {
        points.add(new int[] {1468, 67});
        points.add(new int[] {1571, 67});
    }

    @Override
    public IteratorList getIterator() {
        return points.getIterator();
    }
}
