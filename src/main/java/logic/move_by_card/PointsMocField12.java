package logic.move_by_card;
import java.util.ArrayList;
import java.util.List;

public class PointsMocField12 implements Points{
    private List<int[]> points = new ArrayList<>();

    public PointsMocField12() {
        points.add(new int[] {1548,89});
        points.add(new int[] {1556,79});
        points.add(new int[] {1556,52});
        points.add(new int[] {1516,52});
        points.add(new int[] {1497,50});
        points.add(new int[] {1490,68});
        points.add(new int[] {1500,84});
        points.add(new int[] {1530,87});
        points.add(new int[] {1524,86});
        points.add(new int[] {1524,94});
        points.add(new int[] {1530,87});
        points.add(new int[] {1499,113});
        points.add(new int[] {1522,118});
        points.add(new int[] {1503,134});
        points.add(new int[] {1550,142});
        points.add(new int[] {1548,108});

    }

    @Override
    public List<int[]> getPoints() {
        return points;
    }
}
