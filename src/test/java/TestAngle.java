import java.awt.*;
import java.util.Optional;

public class TestAngle {

    public double calculateAngle(double x1, double y1, double x2, double y2)
    {
        double angle = Math.toDegrees(Math.atan2(x2 - x1, y2 - y1));
        if (x1 > x2 && y1 < y2)
            angle = angle + 180;

        if (x1 > x2 && y1 > y2)
            angle = angle + 360;

        if (x1 < x2 && y1 > y2)
            angle = angle + 180;

        return angle;
    }


}
