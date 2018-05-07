import java.awt.*;

public class PressAndMove {
    Robot robot;

    public void moveMouse(int x, int y) throws AWTException {
       robot = new Robot();
       robot.mouseMove(x,y);
    }


}
