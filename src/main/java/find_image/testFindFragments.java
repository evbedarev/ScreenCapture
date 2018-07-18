package find_image;

import actions.Actions;
import checks.location.CheckOverweight;
import main.Prop;

public class testFindFragments {

    public static void main(String[] args) throws Exception {
        Actions actions = Actions.instance();
        if (CheckOverweight.check()) {
            actions.dropItem(Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                    Prop.ROOT_DIR + "Loot\\GreenHerb\\");
            actions.dropItem(Prop.ROOT_DIR + "Interface\\MarkerInventory\\1\\",
                    Prop.ROOT_DIR + "Loot\\YellowHerb\\");
        }
    }
}
