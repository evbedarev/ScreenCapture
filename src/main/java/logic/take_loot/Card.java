package logic.take_loot;

import logic.RgbParameter;
import org.apache.log4j.Logger;

import java.awt.*;

public class Card extends Loot {
    public Card(Logger logger, LootAround lootAround) throws AWTException {
        this.logger = logger;
        this.lootAround = lootAround;
        rgbParameterList.add(new RgbParameter(-6707798,
                new int[] {50,50},
                new int[] {-8418418, -4999009}));

        rgbParameterList.add(new RgbParameter(-4864314,
                new int[] {50,50},
                new int[] {-4864314}));
    }
}
