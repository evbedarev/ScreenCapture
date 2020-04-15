package logic;

import logic.kill_monster.KillMonster;

public interface Logic {
    void createThread() throws Exception;
    void findAndKillWithoutTakingLoot();
}
