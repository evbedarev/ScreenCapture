package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.PayField07;
import checks.location.PrtField03;
import logic.attacks.AttackGef05;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.KillMonster;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsPayField07;
import logic.move_by_card.PointsPrtField03;
import logic.take_loot.TakeLoot;
import main.Prop;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LogicPrtField03 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;
    private final static AtomicInteger ATOMIC_GUARD = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_AWAKENING = new AtomicInteger(0);
    private final static AtomicInteger ATOMIC_DEFENDER = new AtomicInteger(0);


    public LogicPrtField03() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackGef05();
        actions = Actions.instance();
        moveByCard = MoveByCard.getInstance(this, new PointsPrtField03());
        locationCheck = new LocationCheck(new PrtField03());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = (List<KillMonster>) Prop.context.getBean("monstersPrtField03");
        usefulLoot = (TakeLoot[]) Prop.context.getBean("usefulPayField07");
        loot = (TakeLoot[]) Prop.context.getBean("lootPayField07");
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