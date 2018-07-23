package checks;

import actions.Actions;
import actions.InterfaceActions;
import find_image.FindFragmentInImage;
import key_and_mouse.Mouse;
import logic.Capture;
import main.Prop;
import java.awt.*;
import java.util.Optional;

public class CheckDie {
    private Capture capture;
    private static volatile CheckDie instance;
    FindFragmentInImage findFragmentInImage = FindFragmentInImage.getInstance();


    private CheckDie() {
    }

    static public CheckDie instance() {
        if (instance == null) {
            synchronized (CheckDie.class) {
                if (instance == null) {
                    instance = new CheckDie();
                }
            }
        }
        return instance;
    }

    public void initialize() throws AWTException {
        capture = Capture.instance();
    }

    public boolean check() throws Exception {
        Optional<int[]> xy;


        findFragmentInImage.setScreen(new int[]{600, 1000, 500, 700});
        xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\CheckDie\\");
        if (xy.isPresent()) {
            cmdField();
            return true;
        }

        return false;
    }

    public void cmdField() throws Exception {
        Actions actions = Actions.instance();
        InterfaceActions interfaceActions = InterfaceActions.getInstance();
        interfaceActions.pressReturnToLastSavepoint();
        interfaceActions.pressOk();
        goToKafraMorroc();
        actions.sitDown();
        interfaceActions.pressOnKafra();
        interfaceActions.pressNext();
        interfaceActions.openWarehouse();
        interfaceActions.pressOk();
        interfaceActions.pressClose();
        interfaceActions.putItemToKafra(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\3\\",
                Prop.ROOT_DIR + "Loot\\Cyfar\\");
        interfaceActions.putItemToKafra(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\3\\",
                Prop.ROOT_DIR + "Loot\\WindOfVerdure\\");
        interfaceActions.putItemToKafra(
                Prop.ROOT_DIR + "Interface\\MarkerInventory\\3\\",
                Prop.ROOT_DIR + "Loot\\FeathreOfBirds\\");

        interfaceActions.pressClose();
        Thread.sleep(15*1000*60);
        interfaceActions.pressOnKafra();
        interfaceActions.pressNext();
        interfaceActions.pressKafraTeleport();
        interfaceActions.pressOk();
        interfaceActions.pressNext();
        interfaceActions.pressDownArrow();
        interfaceActions.pressCmdField07();
        interfaceActions.pressOk();
    }

    private void goToKafraMorroc() throws AWTException, InterruptedException {
        Mouse mouse = Mouse.getInstance();
        mouse.mouseClick(1410,70);
        Thread.sleep(5000);
    }
}
