package logic.move_by_card;

import java.util.ArrayList;
import java.util.List;

public class PointsPrtField07 implements Points{
    private List<int[]> points = new ArrayList<>();

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
    public List<int[]> getPoints() {
        return points;
    }
}
