package logic.move_by_card;
import java.util.ArrayList;
import java.util.List;

public class PointsMocField11 implements Points{
    private List<int[]> points = new ArrayList<>();

    public PointsMocField11() {
        points.add(new int[] {1480,124});
        points.add(new int[] {1480,145});
        points.add(new int[] {1484,152});
        points.add(new int[] {1502,155});
        points.add(new int[] {1512,149});
        points.add(new int[] {1525,150});
        points.add(new int[] {1536,157});
        points.add(new int[] {1551,152});
        points.add(new int[] {1545,152});
        points.add(new int[] {1546,147});
        points.add(new int[] {1555,118});
        points.add(new int[] {1532,140});
        points.add(new int[] {1491,122});
        points.add(new int[] {1496,108});
        points.add(new int[] {1493,107});
        points.add(new int[] {1493,100});
        points.add(new int[] {1482,69});
        points.add(new int[] {1524,65});
        points.add(new int[] {1549,61});
        points.add(new int[] {1567,79});
        points.add(new int[] {1560,95});
    }

    @Override
    public List<int[]> getPoints() {
        return points;
    }
}
