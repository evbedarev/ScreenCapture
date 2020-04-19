package logic.move_by_card;
import logic.move_by_card.points_operation.IteratorList;
import logic.move_by_card.points_operation.PointsAbstr;

public class PointsPayField07 extends PointsAbstr {

    public PointsPayField07() {
        points.add(new int[] {1487,98});
        points.add(new int[] {1492,106});
        points.add(new int[] {1484,107});
        points.add(new int[] {1487,108});
        points.add(new int[] {1484,137});
        points.add(new int[] {1513,149});
        points.add(new int[] {1531,140});
        points.add(new int[] {1541,136});
        points.add(new int[] {1558,143});
        points.add(new int[] {1560,121});
        points.add(new int[] {1558,125});
        points.add(new int[] {1549,124});
        points.add(new int[] {1499,122});
        points.add(new int[] {1513,95});
        points.add(new int[] {1540,100});
        points.add(new int[] {1524,109});
        points.add(new int[] {1553,125});
        points.add(new int[] {1563,124});
        points.add(new int[] {1559,103});
        points.add(new int[] {1561,84});
        points.add(new int[] {1567,75});
        points.add(new int[] {1560,65});
        points.add(new int[] {1547,62});
        points.add(new int[] {1538,68});
        points.add(new int[] {1532,73});
        points.add(new int[] {1527,80});
        points.add(new int[] {1520,81});
        points.add(new int[] {1512,81});
        points.add(new int[] {1508,72});
        points.add(new int[] {1512,62});
        points.add(new int[] {1478,61});
        points.add(new int[] {1481,74});
        points.add(new int[] {1486,83});
        points.add(new int[] {1480,96});
        points.add(new int[] {1490,99});
        points.add(new int[] {1496,92});
    }

    @Override
    public IteratorList getIterator() {
        return points.getIterator();
    }

}
