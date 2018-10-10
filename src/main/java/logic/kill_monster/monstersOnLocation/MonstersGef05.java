package logic.kill_monster.monstersOnLocation;

import logic.kill_monster.*;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonstersGef05 extends Monster {
    public MonstersGef05() throws AWTException {
        List<Monster> monsters = Stream.of(
                new ThiefBug(),
                new Creamy(),
                new Smokie()
                ).collect(Collectors.toList());

        for (Monster monster : monsters) {
            rgbParameterList.addAll(monster.getRgbParameterList());
        }
    }
}
