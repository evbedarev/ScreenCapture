package logic.move_by_card;

import logic.move_by_card.points_operation.IteratorList;
import logic.move_by_card.points_operation.PointsAbstr;

public class PointsMocField01 extends PointsAbstr {

    public PointsMocField01() {
        points.add(new int[] {1485,55});
        points.add(new int[] {1491,145});
        points.add(new int[] {1525,107});
        points.add(new int[] {1556,142});
        points.add(new int[] {1561,62});
    }
    @Override
    public IteratorList getIterator() {
        return points.getIterator();
    }

}
