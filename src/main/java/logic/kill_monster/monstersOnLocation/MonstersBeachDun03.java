package logic.kill_monster.monstersOnLocation;

import logic.kill_monster.*;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonstersBeachDun03 extends Monster {
    public MonstersBeachDun03() throws AWTException {
        List<Monster> monsters = Stream.of(
                new Nereid(),
                new TharaFrog(),
                new Hydra()
                ).collect(Collectors.toList());

        for (Monster monster : monsters) {
            rgbParameterList.addAll(monster.getRgbParameterList());
        }
    }
}
