package logic.move_by_card;

import java.util.ArrayList;
import java.util.List;

public class PointsComodo implements Points{
    private List<int[]> points = new ArrayList<>();

    public PointsComodo() {
        points.add(new int[] {1531, 120});
        points.add(new int[] {1532, 119});
        points.add(new int[] {1532, 115});
        points.add(new int[] {1532, 112});
        points.add(new int[] {1534, 110});
        points.add(new int[] {1541, 108});
        points.add(new int[] {1543, 105});
        points.add(new int[] {1545, 105});
        points.add(new int[] {1548, 105});
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
