package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.CmdField02;
import logic.attacks.AttackCmdField02;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.KillMonster;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsCmdField02;
import logic.take_loot.*;
import main.Prop;

import java.util.List;

public class LogicCmdField02 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;


    public LogicCmdField02() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackCmdField02();
        actions = Actions.instance();
        moveByCard = MoveByCard.getInstance(this,  new PointsCmdField02());
        locationCheck = new LocationCheck(Prop.context.getBean(CmdField02.class));
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = (List<KillMonster>) Prop.context.getBean("monstersCmdField02");
        usefulLoot = (TakeLoot[]) Prop.context.getBean("usefulLootCmdField02");
        loot = (TakeLoot[]) Prop.context.getBean("lootCmdField02");
    }

    @Override
    public void createThread() throws Exception {
        start();
    }

    public void mainHandle() throws Exception {
        moveByCard.move(killMonsterList);
    }

    public void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }

    void teleport() throws Exception {
        runFromMonster();
        if (count > Prop.COUNT_TO_TELEPORT) {
            lootAround.takeLootAround();
            sleep(500);
            actions.pickUpCard();
            actions.pickUpLoot(locationCheck);
            count = 0;
            actions.teleport(locationCheck);
            actions.stepAside(locationCheck, new int[] {75, 150} );
        }
    }

    void runFromMonster() throws Exception {
    }
}
