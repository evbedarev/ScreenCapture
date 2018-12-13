package logic.kill_monster.monstersOnLocation;

import logic.kill_monster.*;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonstersGlChurch extends Monster {
    public MonstersGlChurch() throws AWTException {
        List<Monster> monsters = Stream.of(
                new WraithDeath(),
                new EvilDruid(),
                new Wraith(),
                new Mimic()
                ).collect(Collectors.toList());

        for (Monster monster : monsters) {
            rgbParameterList.addAll(monster.getRgbParameterList());
        }
    }
}
