package logic.kill_monster;

import logic.RgbParameter;
import logic.move_by_card.MoveByCard;

import java.awt.*;

public class WraithDeath extends Monster {
    public WraithDeath() throws AWTException {
        rgbParameterList.add(new RgbParameter(-11842741,
                new int[] {100,100},
                new int[] {-11908791, -14015686, -9150113, -7046799}));

    }

    @Override
    void action(int x, int y) throws Exception {
        MoveByCard.wingAway();
    }
}
