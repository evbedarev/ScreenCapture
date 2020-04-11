package logic.move_by_card;

import java.util.ArrayList;
import java.util.List;

public class PointsComodoFail implements Points{
    private List<int[]> points = new ArrayList<>();

    public PointsComodoFail() {
        points.add(new int[] {1471, 60});
        points.add(new int[] {1474, 62});
        points.add(new int[] {1476, 40});
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
