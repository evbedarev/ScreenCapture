package logic.kill_monster.monstersOnLocation;

import logic.kill_monster.*;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonstersPay07 extends Monster {
    public MonstersPay07() throws AWTException {
        List<Monster> monsters = Stream.of(
                new Bigfoot(),
                new Caramel(),
                new Creamy()
                ).collect(Collectors.toList());

        for (Monster monster : monsters) {
            rgbParameterList.addAll(monster.getRgbParameterList());
        }
    }
}
