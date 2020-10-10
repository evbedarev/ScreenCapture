import actions.SleepTime;
import key_and_mouse.Mouse;
import logic.screen_shot.ScreenShot;
import main.Prop;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TestAttackArround {
    Mouse mouse = Mouse.getInstance();
    List<int[]> coordsArround = new ArrayList<>();

    public TestAttackArround() throws AWTException {
        }
        @Before
        public void before() throws Exception {
            Prop.initialize();
            Thread.sleep(2000);
            coordsArround.add(new int[] {810,410});
            coordsArround.add(new int[] {810,367});
            coordsArround.add(new int[] {845,390});
            coordsArround.add(new int[] {855,445});
            coordsArround.add(new int[] {845,495});
            coordsArround.add(new int[] {810,492});
            coordsArround.add(new int[] {770,482});
            coordsArround.add(new int[] {760,450});
            coordsArround.add(new int[] {770,412});
    }
    @Test
    public void moveMouseToPoint() throws InterruptedException, AWTException {
        SleepTime.sleep(1000);
        for (int[] ints : coordsArround) {
            mouse.mouseMove(ints[0], ints[1]);
            checkToAttack(ints[0], ints[1]);
        }
    }

    public void checkToAttack(int x, int y) throws InterruptedException, AWTException {
        boolean isAttackC = true;
        SleepTime.sleep(100);
        while (isAttackC) {
            BufferedImage image = Prop.context.getBean(ScreenShot.class).pop();
            int rgb = image.getRGB(x + 21, y + 16);
            if (rgb > -9500000 && rgb < -9200000) {
                mouse.leftClick();
                SleepTime.sleep(1500);
            } else {
                isAttackC = false;
            }
            SleepTime.sleep(100);
        }
    }
}
//        //rgb 831,426 = -9345150, -9214335,
//        -9280128
//rgb 821,419 = -2171407, -2302736
