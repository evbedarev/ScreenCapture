package logic.move_by_card;

import java.util.ArrayList;
import java.util.List;

public class PointsGefFields05 implements Points{
    private List<int[]> points = new ArrayList<>();

    public PointsGefFields05() {
        points.add(new int[] {1485,152});
        points.add(new int[] {1487,142});
        points.add(new int[] {1489,136});
        points.add(new int[] {1490,126});
        points.add(new int[] {1490,114});
        points.add(new int[] {1502,112});
        points.add(new int[] {1502,112});
        points.add(new int[] {1507,105});
        points.add(new int[] {1514,99});
        points.add(new int[] {1521,94});
        points.add(new int[] {1525,89});
        points.add(new int[] {1535,85});
        points.add(new int[] {1546,86});
        points.add(new int[] {1555,81});
        points.add(new int[] {1559,72});
        points.add(new int[] {1546,65});
        points.add(new int[] {1532,62});
        points.add(new int[] {1516,62});
        points.add(new int[] {1505,60});
        points.add(new int[] {1497,67});
        points.add(new int[] {1489,73});
        points.add(new int[] {1488,97});
        points.add(new int[] {1482,106});
        points.add(new int[] {1496,115});
    }

    @Override
    public List<int[]> getPoints() {
        return points;
    }
}
