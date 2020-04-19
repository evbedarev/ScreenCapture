package logic.move_by_card;

import logic.move_by_card.points_operation.IteratorList;
import logic.move_by_card.points_operation.PointsAbstr;

public class PointsPrtField07 extends PointsAbstr {

    public PointsPrtField07() {
        points.add(new int[] {1471,150});
        points.add(new int[] {1492,150});
        points.add(new int[] {1504,151});
        points.add(new int[] {1516,151});
        points.add(new int[] {1525,154});
        points.add(new int[] {1551,146});
        points.add(new int[] {1562,136});
        points.add(new int[] {1554,127});
        points.add(new int[] {1566,116});
        points.add(new int[] {1561,96});
        points.add(new int[] {1564,77});
        points.add(new int[] {1551,73});
        points.add(new int[] {1544,83});
        points.add(new int[] {1526,75});
        points.add(new int[] {1520,83});
        points.add(new int[] {1512,84});
        points.add(new int[] {1499,105});
    }

    @Override
    public IteratorList getIterator() {
        return points.getIterator();
    }

}
