package move_to_location;

import logic.move_by_card.points_operation.DoublyLinkedQueue;
import logic.move_by_card.points_operation.IteratorList;
import org.junit.Before;
import org.junit.Test;

public class TestGetNearestPointWithIterator {
    DoublyLinkedQueue points;
    @Before
    public void before() {
        points= new DoublyLinkedQueue();
        points.add(new int[] {1482,143});
        points.add(new int[] {1495,139});
        points.add(new int[] {1510,132});
        points.add(new int[] {1520,127});
        points.add(new int[] {1520,127});
        points.add(new int[] {1520,130});
        points.add(new int[] {1534,134});
        points.add(new int[] {1544,134});
        points.add(new int[] {1550,139});
        points.add(new int[] {1566,142});
    }

    @Test
    public void testCycleRoute() {
        int count = 1;
        while (true) {
            IteratorList iteratorList = points.getIterator();
            if (iteratorList.next()[0] == 1566) {
                System.out.println("came to end 1566, 142");
                count++;
            }
            if (iteratorList.next()[0] == 1482) {
                System.out.println("came to begin 1482,143");
            }
            if (count == 10)
                break;
        }
    }
    @Test
    public void testFindNearestPoint() {
        IteratorList iteratorList = points.getIterator();
        int[] pnt = iteratorList.findNearest(new int[] {1533,132});
        System.out.println(pnt[0] + "," + pnt[1]);

    }
}
