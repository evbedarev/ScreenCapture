import logic.move_by_card.Points;
import logic.move_by_card.PointsFindNearest;
import main.Prop;
import org.junit.Test;

import java.util.List;

public class tst {
    public static void main(String[] args) throws Exception {
        PointsFindNearest pnts = new PointsFindNearest();
        List<int[]> pointsList = pnts.findNearestPoint(new int[] {1544,130});
        for (int[] ints : pointsList) {
            System.out.println("coord {" + ints[0] + "," + ints[1] + "}");
        }
    }
}
