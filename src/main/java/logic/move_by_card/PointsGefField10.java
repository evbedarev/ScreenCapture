package logic.move_by_card;
import logic.move_by_card.points_operation.IteratorList;
import logic.move_by_card.points_operation.PointsAbstr;

public class PointsGefField10 extends PointsAbstr {

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
    public IteratorList getIterator() {
        return points.getIterator();
    }

}
