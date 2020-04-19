package logic.move_by_card;

import logic.move_by_card.points_operation.IteratorList;
import logic.move_by_card.points_operation.PointsAbstr;

import java.util.ArrayList;
import java.util.List;

public class PointsCmdField04 extends PointsAbstr {

    public PointsCmdField04() {
        points.add(new int[] {1475, 140});
        points.add(new int[] {1484, 126});
        points.add(new int[] {1499, 132});
        points.add(new int[] {1508, 121});
        points.add(new int[] {1522, 142});
        points.add(new int[] {1543, 135});
        points.add(new int[] {1560, 140});
        points.add(new int[] {1572, 140});
        points.add(new int[] {1555, 128});
        points.add(new int[] {1558, 116});
        points.add(new int[] {1544, 117});
        points.add(new int[] {1534, 126});
        points.add(new int[] {1520, 131});
        points.add(new int[] {1520, 131});
        //climb the mountain
        points.add(new int[] {1494, 118});
        points.add(new int[] {1497, 116});
        points.add(new int[] {1495, 108});
        ///points.add(new int[] {,});
        points.add(new int[] {1502, 105});
        points.add(new int[] {1495, 108});
        points.add(new int[] {1484, 108});
        points.add(new int[] {1489, 100});
        points.add(new int[] {1484, 108});
        points.add(new int[] {1473, 120});
        points.add(new int[] {1484, 108});
        points.add(new int[] {1474, 100});
        points.add(new int[] {1478, 90});
        points.add(new int[] {1470, 83});
        points.add(new int[] {1498, 60});
        //go to tunnel
        points.add(new int[] {1486, 70});
        points.add(new int[] {1492, 68});
        points.add(new int[] {1494, 71});
        points.add(new int[] {1497, 69});
        ///
        points.add(new int[] {1505, 70});
        points.add(new int[] {1507, 66});
        points.add(new int[] {1517, 80});
        points.add(new int[] {1530, 84});
        points.add(new int[] {1542, 70});
        points.add(new int[] {1543, 79});
        points.add(new int[] {1553, 82});
        points.add(new int[] {1556, 78});
        points.add(new int[] {1561, 78});
        points.add(new int[] {1561, 73});
        //reverse
        points.add(new int[] {1561, 78});
        points.add(new int[] {1556, 78});
        points.add(new int[] {1543, 79});

        //go down
        points.add(new int[] {1541, 85});
        points.add(new int[] {1545, 88});
        points.add(new int[] {1541, 93});
        points.add(new int[] {1535, 92});
        points.add(new int[] {1532, 94});
        points.add(new int[] {1527, 94});
        points.add(new int[] {1517, 94});
        points.add(new int[] {1517, 102});
        points.add(new int[] {1521, 96});
        points.add(new int[] {1527, 94});
        points.add(new int[] {1532, 94});
        points.add(new int[] {1535, 92});
        points.add(new int[] {1541, 93});
        points.add(new int[] {1555, 94});
        points.add(new int[] {1552, 90});
        points.add(new int[] {1549, 94});
        points.add(new int[] {1541, 93});
        points.add(new int[] {1545, 88});
        points.add(new int[] {1543, 79});
        ///
        points.add(new int[] {1533, 68});
        points.add(new int[] {1530, 70});
        points.add(new int[] {1523, 63});
        points.add(new int[] {1511, 59});
        points.add(new int[] {1518, 59});
        points.add(new int[] {1531, 59});
        points.add(new int[] {1540, 60});
        points.add(new int[] {1549, 65});
        points.add(new int[] {1562, 60});
        points.add(new int[] {1572, 75});
//        points.add(new int[] {,});
//        points.add(new int[] {,});
    }
    @Override
    public IteratorList getIterator() {
        return points.getIterator();
    }

}
