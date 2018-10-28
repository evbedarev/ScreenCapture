package logic.move_by_card;

import java.util.ArrayList;
import java.util.List;

public class PointsMocField01 implements Points{
    private List<int[]> points = new ArrayList<>();

    public PointsMocField01() {
        points.add(new int[] {1485,55});
        points.add(new int[] {1491,145});
        points.add(new int[] {1525,107});
        points.add(new int[] {1556,142});
        points.add(new int[] {1561,62});
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
