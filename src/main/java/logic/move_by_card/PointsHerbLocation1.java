package logic.move_by_card;

import logic.move_by_card.points_operation.IteratorList;
import logic.move_by_card.points_operation.PointsAbstr;

public class PointsHerbLocation1 extends PointsAbstr {

    public PointsHerbLocation1() {
        points.add(new int[] {1482,143});
        points.add(new int[] {1495,139});
        points.add(new int[] {1510,132});
        points.add(new int[] {1520,127});
        points.add(new int[] {1520,127});
        points.add(new int[] {1520,130});
        points.add(new int[] {1534,134});
        points.add(new int[] {1544,134});
        points.add(new int[] {1550,139});
        points.add(new int[] {1566,142});
    }

    @Override
    public IteratorList getIterator() {
        return points.getIterator();
    }
}
