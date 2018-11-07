package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.CmdFiled02;
import logic.attacks.AttackCmdField02;
import logic.attacks.AttackGef05;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.Galapago;
import logic.kill_monster.Seal;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsCmdField02;
import logic.take_loot.*;
import main.Prop;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicCmdField02 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;


    public LogicCmdField02() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackCmdField02();
        actions = Actions.instance();
        moveByCard = MoveByCard.getInstance(this,  new PointsCmdField02());
        locationCheck = new LocationCheck(new CmdFiled02());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = Stream
                .of(
                        new Seal(),
                        new Galapago()

                ).collect(Collectors.toList());

        usefulLoot = new TakeLoot[] {
                new Card(),
                new Coupon(),
                new BlueHerb(),
                new Cyfar(),
                new Zargon()
        };

        loot = new TakeLoot[] {
//            new PowderOfButterfly(),
//                new Honey()
        };
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
