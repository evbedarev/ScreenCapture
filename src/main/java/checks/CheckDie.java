package checks;

import actions.Actions;
import actions.InterfaceActions;
import checks.afterDeath.AfterDeath;
import find_image.FindFragmentInImage;
import main.Prop;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CheckDie implements AfterDeath {
//    private static volatile CheckDie instance;
    FindFragmentInImage findFragmentInImage = FindFragmentInImage.getInstance();
    public List<KafraLoot> kafraLootList = new ArrayList<>();
    public Actions actions;

    public CheckDie() {
    }

    public class KafraLoot {
        String markerInventoryPath;
        String lootPath;

        public KafraLoot(String markerInventoryPath, String lootPath) {
            this.markerInventoryPath = markerInventoryPath;
            this.lootPath = lootPath;
        }
    }

    public boolean check() throws Exception {
        if (!Prop.CHECK_DIE) return false;
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{600, 1000, 500, 700});
        xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\CheckDie\\");
        if (xy.isPresent()) {
            startActions();
            return true;
        }
        return false;
    }

    public void putItems(List<KafraLoot> kafraLootList) throws Exception {
        actions = Actions.instance();
        InterfaceActions interfaceActions = InterfaceActions.getInstance();
        interfaceActions.pressReturnToLastSavepoint();
        interfaceActions.pressOk();
        goToKafra();
        actions.sitDown();
        interfaceActions.pressOnKafra();
        interfaceActions.pressNext();
        interfaceActions.openWarehouse();
        interfaceActions.pressOk();
        interfaceActions.pressClose();
        for (KafraLoot kafraLoot: kafraLootList) {
            interfaceActions.putItemToKafra(kafraLoot.markerInventoryPath,
                    kafraLoot.lootPath);
        }
        interfaceActions.pressClose();
        interfaceActions.pressOnKafra();
        interfaceActions.pressNext();
        interfaceActions.pressKafraTeleport();
        interfaceActions.pressOk();
        interfaceActions.pressNext();
        interfaceActions.pressDownArrow();
        interfaceActions.pressCmdField07();
        interfaceActions.pressOk();
        Thread.sleep(5000);
    }

    public abstract void startActions() throws Exception;
    public abstract void goToKafra() throws AWTException, InterruptedException;
}
