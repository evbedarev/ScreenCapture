package checks;

import actions.Actions;
import actions.InterfaceActions;
import actions.SleepTime;
import checks.afterDeath.AfterDeath;
import find_image.FindFragmentInImage;
import key_and_mouse.Keys;
import logic.screen_shot.Capture;
import logic.screen_shot.ScreenShotStack;
import main.Prop;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CheckDie implements AfterDeath {
//    private static volatile CheckDie instance;
    FindFragmentInImage findFragmentInImage = FindFragmentInImage.getInstance();
    public List<KafraLoot> kafraLootList = new ArrayList<>();
    public Actions actions;
    private BufferedImage image;
    private Capture capture;
    private Keys keys;
//    private

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

    private boolean checkDeathLabel() throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{600, 1000, 500, 900});
        xy = findFragmentInImage.findImage(Prop.ROOT_DIR + "Interface\\CheckDie\\");
        if (xy.isPresent()) {
            startActions();
            return true;
        }
        return false;
    }

    @Override
    public boolean check() throws Exception {
        capture = Capture.instance();
        keys = Keys.getInstance();
        if (!Prop.CHECK_DIE) return false;
        if (checkDeathLabel()) return true;
        image = Prop.context.getBean(ScreenShotStack.class).pop();
        if (image.getRGB(Prop.X_HP_AFTER_DEATH,Prop.Y_HP) == Prop.RGB_HP_DEATH) {
            while (!checkDeathLabel()) {
                keys.keyPress(KeyEvent.VK_ESCAPE);
                SleepTime.sleep(2000);
                image = Prop.context.getBean(ScreenShotStack.class).pop();
                if (image.getRGB(Prop.X_HP_AFTER_DEATH,Prop.Y_HP) != Prop.RGB_HP_DEATH) return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean check(BufferedImage image) throws Exception {
        keys = Keys.getInstance();
        if (!Prop.CHECK_DIE) return false;
        if (checkDeathLabel()) return true;
        if (image.getRGB(Prop.X_HP_AFTER_DEATH,Prop.Y_HP) == Prop.RGB_HP_DEATH) {
            while (!checkDeathLabel()) {
                keys.keyPress(KeyEvent.VK_ESCAPE);
                SleepTime.sleep(2000);
                image = Prop.context.getBean(ScreenShotStack.class).pop();
                if (image.getRGB(Prop.X_HP_AFTER_DEATH,Prop.Y_HP) != Prop.RGB_HP_DEATH) return false;
            }
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
        interfaceActions.openInventory();
        for (KafraLoot kafraLoot: kafraLootList) {
            interfaceActions.putItemToKafra(kafraLoot.markerInventoryPath,
                    kafraLoot.lootPath);
        }
        interfaceActions.closeInventory();
        interfaceActions.pressClose();
        SleepTime.sleep(5000);
    }

    public abstract void startActions() throws Exception;
    public abstract void goToKafra() throws AWTException, InterruptedException;
}
