package logic.move_by_card;

import java.util.List;

public class PointsPrtField08 implements Points{
    private List<int[]> points;

    public PointsPrtField08() {
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
    }

    @Override
    public List<int[]> getPoints() {
        return points;
    }
}
