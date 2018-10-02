package logic.move_by_card;

import java.util.ArrayList;
import java.util.List;

public class PointsPrtField08 implements Points{
    private List<int[]> points = new ArrayList<>();

    public PointsPrtField08() {
        points.add(new int[] {1484,55});
        points.add(new int[] {1488,114});
        points.add(new int[] {1518,95});
        points.add(new int[] {1518,95});
        points.add(new int[] {1534,117});
        points.add(new int[] {1545,123});
        points.add(new int[] {1535,150});
        points.add(new int[] {1549,148});
        points.add(new int[] {1545,123});
        points.add(new int[] {1534,117});
        points.add(new int[] {1539,56});

    }

    @Override
    public List<int[]> getPoints() {
        return points;
    }
}
