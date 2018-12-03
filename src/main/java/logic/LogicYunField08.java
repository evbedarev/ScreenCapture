package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.YunField08;
import checks.location.YunField12;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.monstersOnLocation.MonstersYun12;
import logic.kill_monster.monstersOnLocation.MonstersYunField08;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsYunField08;
import logic.move_by_card.PointsYunField12;
import logic.take_loot.*;
import main.Prop;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicYunField08 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicYunField08() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        actions = Actions.instance();
        moveByCard = MoveByCard.getInstance(this, new PointsYunField08());
        locationCheck = new LocationCheck(new YunField08());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = Stream
                .of(new MonstersYunField08())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
        };
        

        usefulLoot = new TakeLoot[] {
                new Card(),
                new MastellaFruit(),
                new WindOfVerdure(),
                new BlueHerb(),
                new Coupon(),
        };
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
