import logic.move_by_card.Points;
import logic.move_by_card.PointsFindNearest;
import logic.move_by_card.PointsMocField11;
import main.Prop;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class tst {
    public static  Optional<int[]> xy;
    static String msg;
    public static void main(String[] args) throws Exception {
        long timeBefore = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append(22);
        }
        long timeAfter = System.currentTimeMillis();
        System.out.println("TIme: " + (timeAfter - timeBefore));
    }
}
