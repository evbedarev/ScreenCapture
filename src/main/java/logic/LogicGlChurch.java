package logic;

import checks.LocationCheck;
import checks.location.GlChurch;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun04;
import logic.kill_monster.*;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsGlChurch;
import logic.take_loot.*;
import main.Prop;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicGlChurch extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicGlChurch() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        moveByCard = MoveByCard.getInstance(this, new PointsGlChurch());
        locationCheck = new LocationCheck(new GlChurch());
        lootAround.initialize(new HandYun04());
        killMonsterList = Stream
                .of(    new WraithDeath(),
                        new EvilDruid(),
                        new Wraith(),
                        new Mimic())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
                new Fabric(),
                new YggdrasilLeaf(),
                new WhiteHerb(),
        };

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Coupon(),
        };

        checkAgressorIsNear.initialize(Stream
                .of(new Harpy())
                .collect(Collectors.toList()));
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
        moveByCard.move(killMonsterList);
    }


    void teleport() throws Exception {
    }

    void runFromMonster() throws Exception {
    }

}
