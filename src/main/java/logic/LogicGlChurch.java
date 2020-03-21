package logic;

import checks.LocationCheck;
import checks.location.GlChurch;
import logic.attacks.AttackYun11;
import logic.hands_rgb.HandYun04;
import logic.kill_monster.*;
import logic.move_by_card.MoveByCard;
import logic.move_by_card.PointsGlChurch;
import    logic.take_loot.*;
import main.Prop;

import java.util.List;
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
        killMonsterList = (List<KillMonster>) Prop.context.getBean("monstersGlChurch");
        loot = (TakeLoot[]) Prop.context.getBean("lootGlChurch");
        usefulLoot = (TakeLoot[]) Prop.context.getBean("usefulGlChurch");
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
