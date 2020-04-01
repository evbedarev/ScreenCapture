package logic.move_by_card;

import java.util.ArrayList;
import java.util.List;

public class PointsComodo implements Points{
    private List<int[]> points = new ArrayList<>();

    public PointsComodo() {
        points.add(new int[] {1531, 120});
        points.add(new int[] {1531, 118});
        points.add(new int[] {1531, 115});
        points.add(new int[] {1533, 113});
        points.add(new int[] {1533, 112});
        points.add(new int[] {1535, 110});
        points.add(new int[] {1541, 107});
        points.add(new int[] {1543, 105});
        points.add(new int[] {1546, 103});
        points.add(new int[] {1549, 104});
        points.add(new int[] {1551, 105});
        points.add(new int[] {1555, 105});
        points.add(new int[] {1558, 106});
        points.add(new int[] {1560, 108});
        points.add(new int[] {1562, 109});
        points.add(new int[] {1565, 110});
        points.add(new int[] {1568, 110});
        points.add(new int[] {1571, 110});
}


    @Override
    public void setPoints(List<int[]> points) {
        this.points = points;
    }

    @Override
    public List<int[]> getPoints() {
        return points;
    }
}
//        points.add(new int[] {1531, 120});
//        points.add(new int[] {1532, 119});
//        points.add(new int[] {1534, 117});
//        points.add(new int[] {1540, 110});
//        points.add(new int[] {1542, 108});
//        points.add(new int[] {1545, 106});
//        points.add(new int[] {1547, 104});
//        points.add(new int[] {1550, 105});
//        points.add(new int[] {1553, 104});
//        points.add(new int[] {1556, 105});
//        points.add(new int[] {1558, 106});
//        points.add(new int[] {1561, 107});
//        points.add(new int[] {1563, 108});
//        points.add(new int[] {1566, 110});
//        points.add(new int[] {1569, 110});
//        points.add(new int[] {1571, 110});
//        points.add(new int[] {1573, 110});
