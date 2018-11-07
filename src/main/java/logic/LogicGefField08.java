package logic;

import checks.LocationCheck;
import checks.location.GefField08;
import checks.location.GlChurch;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun04;
import logic.kill_monster.*;
import logic.kill_monster.monstersOnLocation.MonstersGef08;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsGefField08;
import logic.move_by_card.PointsGlChurch;
import logic.take_loot.*;
import main.Prop;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicGefField08 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicGefField08() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        moveByCard = MoveByCard.getInstance(this, new PointsGefField08());
        locationCheck = new LocationCheck(new GefField08());
        lootAround.initialize(new HandYun04());
        killMonsterList = Stream
                .of(new MonstersGef08())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
                new WhiteHerb(),
                new DragonTail(),
                new Zargon()
        };

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Elunium(),
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

//    public void checkMyHp() throws Exception {
//        actions.pickUpCard();
//        checkHP.checkHp();
//    }

    void teleport() throws Exception {
    }

    void runFromMonster() throws Exception {
    }

}
