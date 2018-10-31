import logic.move_by_card.Points;
import logic.move_by_card.PointsFindNearest;
import logic.move_by_card.PointsMocField11;
import main.Prop;
import org.junit.Test;

import java.util.List;

public class tst {

    public static void main(String[] args) throws Exception {
        PointsFindNearestTest pnts = new PointsFindNearestTest();
        PointsMocField11 pointsMocField11 = new PointsMocField11();
        pnts.setPoints(pointsMocField11.getPoints());

        List<int[]> pointsList = pnts.findNearestPoint(new int[] {1496,116});
        for (int[] ints : pointsList) {
            System.out.println("coord {" + ints[0] + "," + ints[1] + "}");
        }
    }
}
