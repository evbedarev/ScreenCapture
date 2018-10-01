package logic.move_by_card;

import java.util.ArrayList;
import java.util.List;

public class PointsHerbLocation1 implements Points{
    private List<int[]> points = new ArrayList<>();

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
    public List<int[]> getPoints() {
        return points;
    }
}
