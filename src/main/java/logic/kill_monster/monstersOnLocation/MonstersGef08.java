package logic.kill_monster.monstersOnLocation;

import logic.kill_monster.*;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonstersGef08 extends Monster {
    public MonstersGef08() throws AWTException {
        List<Monster> monsters = Stream.of(
                new Petite(),
                new SwiftPetite(),
                new Mantis()
                ).collect(Collectors.toList());

        for (Monster monster : monsters) {
            rgbParameterList.addAll(monster.getRgbParameterList());
        }
    }
}
