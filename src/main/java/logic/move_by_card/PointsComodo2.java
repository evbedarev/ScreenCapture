package logic.move_by_card;

import logic.move_by_card.points_operation.IteratorList;
import logic.move_by_card.points_operation.PointsAbstr;

import java.util.ArrayList;
import java.util.List;

public class PointsComodo2 extends PointsAbstr {

    public PointsComodo2() {
        points.add(new int[] {1471, 60});
        points.add(new int[] {1474, 62});
        points.add(new int[] {1476, 65});
        points.add(new int[] {1479, 68});
        points.add(new int[] {1482, 71});
        points.add(new int[] {1485, 74});
        points.add(new int[] {1487, 77});
        points.add(new int[] {1488, 81});
        points.add(new int[] {1487, 84});
        points.add(new int[] {1487, 88});
        points.add(new int[] {1489, 91});
        points.add(new int[] {1488, 95});
        points.add(new int[] {1490, 98});
        points.add(new int[] {1487, 101});
        points.add(new int[] {1487, 106});
        points.add(new int[] {1488, 108});
        points.add(new int[] {1489, 112});
        points.add(new int[] {1491, 115});
        points.add(new int[] {1493, 118});
        points.add(new int[] {1496, 121});
        points.add(new int[] {1499, 124});
        points.add(new int[] {1502, 124});
        points.add(new int[] {1507, 124});
        points.add(new int[] {1510, 127});
        points.add(new int[] {1514, 127});
        points.add(new int[] {1517, 129});
        points.add(new int[] {1521, 129});
        points.add(new int[] {1525, 129});
        points.add(new int[] {1527, 129});
        points.add(new int[] {1528, 132});
        points.add(new int[] {1529, 136});
        points.add(new int[] {1532, 138});
        points.add(new int[] {1534, 139});
        points.add(new int[] {1537, 142});
        points.add(new int[] {1540, 145});
        points.add(new int[] {1543, 147});
        points.add(new int[] {1545, 150});
        points.add(new int[] {1549, 149});
        points.add(new int[] {1553, 149});
        points.add(new int[] {1557, 148});
        points.add(new int[] {1561, 148});
        points.add(new int[] {1564, 147});
        points.add(new int[] {1567, 145});
        points.add(new int[] {1570, 142});
        points.add(new int[] {1574, 141});
        points.add(new int[] {1578, 142});
        points.add(new int[] {1578, 142});
        points.add(new int[] {1578, 142});
    }
    @Override
    public IteratorList getIterator() {
        return points.getIterator();
    }
}
