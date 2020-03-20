package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.YunField12;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.KillMonster;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsYunField12;
import logic.take_loot.*;
import main.Prop;

import java.util.List;

public class LogicYunField12 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicYunField12() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        actions = Actions.instance();
        moveByCard = MoveByCard.getInstance(this, new PointsYunField12());
        locationCheck = new LocationCheck(new YunField12());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = (List<KillMonster>) Prop.context.getBean("monstersYunField12");

        loot = new TakeLoot[] {
        };
        

        usefulLoot = new TakeLoot[] {
                new Card(),
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
