package logic;

import actions.Actions;
import checks.LocationCheck;
import checks.location.MocField12;
import key_and_mouse.Mouse;
import logic.attacks.AttackGef11;
import logic.hands_rgb.HandYun11;
import logic.kill_monster.*;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsMocField12;
import logic.take_loot.*;
import main.Prop;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicMocField12 extends LogicLocation {
    private static final int COUNT_OF_ATTACKS = 100;
    Mouse mouse;
    private MoveByCard moveByCard;
    Optional<int[]> xy, xy1, mouseClickCoord;

    public LogicMocField12() throws Exception {
        xy1 = Optional.empty();
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackGef11();
        moveByCard = MoveByCard.getInstance(this, new PointsMocField12());
        actions = Actions.instance();
        locationCheck = new LocationCheck(new MocField12());
        lootAround.initialize(new HandYun11());
        checkHP.initialize(true, Prop.checkHitPoints);
        killMonsterList = (List<KillMonster>) Prop.context.getBean("monstersMocField12");
        usefulLoot = (TakeLoot[]) Prop.context.getBean("usefulMocField12");
        loot = (TakeLoot[]) Prop.context.getBean("lootMocField12");
        checkAgressorIsNear.initialize(Stream
                .of(new Goblin())
                .collect(Collectors.toList()));
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
