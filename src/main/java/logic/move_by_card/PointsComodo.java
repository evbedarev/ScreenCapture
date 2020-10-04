package logic.move_by_card;

import logic.move_by_card.points_operation.IteratorList;
import logic.move_by_card.points_operation.PointsAbstr;

public class PointsComodo extends PointsAbstr {

    public PointsComodo() {
//        points.add(new int[] {1531, 120});
//        points.add(new int[] {1532, 119});
//        points.add(new int[] {1532, 115});
//        points.add(new int[] {1532, 112});
//        points.add(new int[] {1534, 110});
//        points.add(new int[] {1541, 108});
//        points.add(new int[] {1543, 105});
//        points.add(new int[] {1545, 105});
//        points.add(new int[] {1548, 105});
        points.add(new int[] {1551, 105});
        points.add(new int[] {1554, 105});
        points.add(new int[] {1557, 106});
        points.add(new int[] {1559, 108});
        points.add(new int[] {1562, 108});
        points.add(new int[] {1565, 109});
        points.add(new int[] {1568, 109});
        points.add(new int[] {1571, 110});
        points.add(new int[] {1573, 110});
    }
    @Override
    public IteratorList getIterator() {
        return points.getIterator();
    }
}
