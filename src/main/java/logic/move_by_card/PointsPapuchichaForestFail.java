package logic.move_by_card;

import java.util.ArrayList;
import java.util.List;

public class PointsPapuchichaForestFail implements Points{
    private List<int[]> points = new ArrayList<>();

    public PointsPapuchichaForestFail() {
        points.add(new int[] {1468, 67});
        points.add(new int[] {1571, 67});
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
