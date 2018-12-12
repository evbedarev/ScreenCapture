package logic;

import checks.LocationCheck;
import checks.location.YunField04;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun04;
import logic.kill_monster.Harpy;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsYunField04;
import logic.take_loot.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicYunField04 extends LogicLocation {

    private static final int COUNT_OF_ATTACKS = 100;

    public LogicYunField04() throws Exception {
        countOfAttacks = COUNT_OF_ATTACKS;
        attack = new AttackYun11();
        moveByCard = MoveByCard.getInstance(this, new PointsYunField04());
        locationCheck = new LocationCheck(new YunField04());
        lootAround.initialize(new HandYun04());
        killMonsterList = Stream
                .of(new Harpy())
                .collect(Collectors.toList());

        loot = new TakeLoot[] {
                new HarpyFeather(),
                new HarpyTalon()
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

    //RENAME
    public void checkMyHp() throws Exception {
        actions.pickUpCard();
        checkHP.checkHp();
    }

    void teleport() throws Exception {
    }

    void runFromMonster() throws Exception {
    }

}
