package logic.move_by_card;

public class Angle {
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

    public int[] moveMouseDirectly(int x2, int y2) {
        double x, y, x1, y1;
        x1 = 800;
        y1 = 450;
        x2 = 800 + x2;
        y2 = 450 + y2;
        if (x1 > x2)
            x = x2 - 400;
        else
            x = x2 + 400;

        y = ((x - x1)/(x2 - x1))*(y2 - y1) + y1;
        System.out.println(x + "," + y);
        return new int[] {(int)x, (int)y};
    }
}
