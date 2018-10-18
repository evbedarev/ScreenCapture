package logic.kill_monster.monstersOnLocation;

import logic.kill_monster.*;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonstersInSphinx3 extends Monster {
    public MonstersInSphinx3() throws AWTException {
        List<Monster> monsters = Stream.of(
//                new Pasana(),
//                new Marduk()
//                new Mimic(),
                new Matyr()
                ).collect(Collectors.toList());

        for (Monster monster : monsters) {
            rgbParameterList.addAll(monster.getRgbParameterList());
        }
    }
}
