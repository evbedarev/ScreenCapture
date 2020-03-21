package logic;

import checks.LocationCheck;
import checks.location.GefField08;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun04;
import logic.kill_monster.*;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsGefField08;
import logic.take_loot.*;
import main.Prop;

import java.util.List;
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
        killMonsterList = (List<KillMonster>) Prop.context.getBean("monstersGefField08");
        loot = (TakeLoot[]) Prop.context.getBean("lootGefField08");
        usefulLoot = (TakeLoot[]) Prop.context.getBean("lootGefField08");
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
