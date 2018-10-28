package logic.move_by_card;
import java.util.ArrayList;
import java.util.List;

public class PointsGefField10 implements Points{
    private List<int[]> points = new ArrayList<>();

    public PointsGefField10() {
        points.add(new int[] {1490,70});
        points.add(new int[] {1484,93});
        points.add(new int[] {1481,97});
        points.add(new int[] {1495,138});
        points.add(new int[] {1510,146});
        points.add(new int[] {1519,141});
        points.add(new int[] {1545,136});
        points.add(new int[] {1557,106});
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
