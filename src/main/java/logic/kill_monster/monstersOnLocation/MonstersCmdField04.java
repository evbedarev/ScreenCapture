package logic.kill_monster.monstersOnLocation;

import logic.kill_monster.*;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonstersCmdField04 extends Monster {
    public MonstersCmdField04() throws AWTException {
        List<Monster> monsters = Stream.of(
                new SeaOtterBoss(),
                new SeaOtter(),
                new Galapago()
                ).collect(Collectors.toList());

        for (Monster monster : monsters) {
            rgbParameterList.addAll(monster.getRgbParameterList());
        }
    }
}
