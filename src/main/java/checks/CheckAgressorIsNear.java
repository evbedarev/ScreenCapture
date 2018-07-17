package checks;

import logic.Capture;
import logic.kill_monster.KillMonster;
import java.util.List;

public class CheckAgressorIsNear {
    private static CheckAgressorIsNear instance;
    private List<KillMonster> killMonsterList;

    private CheckAgressorIsNear() {
    }

    static public CheckAgressorIsNear instance() {
        if (instance == null) {
            instance = new CheckAgressorIsNear();
        }
        return instance;
    }

    public void initialize(List<KillMonster> killMonsterList) {
        this.killMonsterList = killMonsterList;
    }

    public boolean check() throws Exception {
        if (killMonsterList != null && killMonsterList.size() > 0)
            for (KillMonster killMonster: killMonsterList) {
                if (killMonster.kill()) {
                    return true;
                }
            }
        return false;
    }
}
