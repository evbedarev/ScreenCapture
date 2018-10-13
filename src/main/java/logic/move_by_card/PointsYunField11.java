package logic.move_by_card;

import java.util.ArrayList;
import java.util.List;

public class PointsYunField11 implements Points{
    private List<int[]> points = new ArrayList<>();

    public PointsYunField11() {
        //points.add(new int[] {});
        points.add(new int[] {1566, 100});
        points.add(new int[] {1559, 102});
        points.add(new int[] {1553, 102});
        points.add(new int[] {1546, 95});
        points.add(new int[] {1542, 101});
        points.add(new int[] {1530, 106});
        points.add(new int[] {1525, 117});
        points.add(new int[] {1516, 124});
        points.add(new int[] {1522, 131});
        points.add(new int[] {1516, 124});
        points.add(new int[] {1506, 136});
        points.add(new int[] {1500, 119});
        points.add(new int[] {1491, 134});
        points.add(new int[] {1485, 139});
        points.add(new int[] {1475, 132});
        points.add(new int[] {1486, 126});
        points.add(new int[] {1493, 109});


    }

    @Override
    public List<int[]> getPoints() {
        return points;
    }
}
