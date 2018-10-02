package find_image;

import actions.Actions;
import actions.SleepTime;
import checks.location.CheckOverweight;
import main.Prop;

public class testFindFragments {

    public static void main(String[] args) throws Exception {
        Actions actions = Actions.instance();
        SleepTime.sleep(5000);
        actions.standUp();
//        if (CheckOverweight.check()) {
////            actions.dropItem(Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
////                    Prop.ROOT_DIR + "Loot\\GreenPlant\\");
////            actions.dropItem(Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
////                    Prop.ROOT_DIR + "Loot\\YellowHerb\\");
//        }
    }
}
