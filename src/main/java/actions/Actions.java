package actions;

import actions.press_on_image.actions_spring.ActionConfigSpring;
import actions.press_on_image.actions_spring.StepAside;
import checks.CheckMsg;
import checks.LocationCheck;
import find_image.FindFragmentInImage;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.take_loot.TakeLoot;
import main.Prop;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class Actions {
    private static volatile Actions instance;
    Keys keys = Keys.getInstance();
    Mouse mouse;
    CheckMsg checkMsg;
    private TakeLoot[] loot;
    private TakeLoot[] usefulLoot;
    FindFragmentInImage findFragmentInImage = FindFragmentInImage.getInstance();
    private InterfaceActions interfaceActions;
    private static int REPEAT_LOOT = 0;
    private static int REPEAT_USEFUL_LOOT = 0;
    private AnnotationConfigApplicationContext actionContext = new AnnotationConfigApplicationContext(ActionConfigSpring.class);
    private StepAside stepAside = actionContext.getBean(StepAside.class);

    private Actions() throws AWTException {
        mouse = Mouse.getInstance();
        interfaceActions = InterfaceActions.getInstance();
    }

    static public Actions instance() throws AWTException {
        if (instance == null) {
            synchronized (Actions.class) {
                if (instance == null) {
                    instance = new Actions();
                }
            }
        }
        return instance;
    }

    public void initialize(TakeLoot[] loot,
                           TakeLoot[] usefulLoot) {
        this.loot = loot;
        this.usefulLoot = usefulLoot;
    }

    public void teleport(LocationCheck locationCheck) throws Exception {
        checkMsg.check();
        locationCheck.locationCheck();
        keys.keyPress(Prop.TELEPORT_KEY);
        SleepTime.sleep(300);
        keys.keyPress(KeyEvent.VK_ENTER);
        SleepTime.sleep(1000);
        interfaceActions.pressOk();
        SleepTime.sleep(500);
        LoggerSingle.logInfo(this.toString(), "Teleporting with locationCheck");
    }

    public void teleport() throws Exception {
        keys.keyPress(Prop.TELEPORT_KEY);
        SleepTime.sleep(300);
        keys.keyPress(KeyEvent.VK_ENTER);
        SleepTime.sleep(1000);
        interfaceActions.pressOk();
        SleepTime.sleep(500);
        LoggerSingle.logInfo(this.toString(), "Teleporting without locationCheck");
    }

    public void useWing() throws Exception {
        keys.keyPress(Prop.WING_KEY);
        LoggerSingle.logInfo(this.getClass().toString(), " Use Wing Key.");
        SleepTime.sleep(3000);
    }

    public void useWing(LocationCheck locationCheck) throws Exception {
        keys.keyPress(Prop.WING_KEY);
        LoggerSingle.logInfo(this.getClass().toString(), " Use Wing Key.");
        SleepTime.sleep(3000);
        locationCheck.locationCheck();
    }

    public void sitDown () throws  InterruptedException {
        keys.keyPress(KeyEvent.VK_INSERT);
    }

    public void standUp() throws InterruptedException {
        sitDown();
    }

    public void castGuard() throws InterruptedException {
        keys.keyPress(Prop.GUARD);
        SleepTime.sleep(1000);
    }

    public void shieldSpell() throws InterruptedException {
        keys.keyPress(Prop.SHIELD_SPELL);
        SleepTime.sleep(1000);
    }

    public void castReflectShield() throws InterruptedException {
        keys.keyPress(Prop.REFLECT);
        SleepTime.sleep(1000);
    }

    public void drinkAwaikeningPotion() throws InterruptedException {
        keys.keyPress(Prop.AWAKENING_POTION);
    }

    public void stealZeny() throws InterruptedException {
        keys.keyPress(KeyEvent.VK_F4);
    }

    public void useBash() throws InterruptedException {
        keys.keyPress(KeyEvent.VK_F8);
    }

    public void usePreserve() throws InterruptedException {
        keys.keyPress(Prop.PRESERVE);
    }

    public void stepAside(int[] radiuses) throws Exception {
        stepAside.stepAsideSimple();
    }

    public void stepAside(LocationCheck locationCheck, int[] radiuses) throws Exception {
        stepAside.stepAsideSimple();
    }

    public Optional<int[]> stepAside(LocationCheck locationCheck, int[] radiuses, boolean needCoords) throws Exception {
        return stepAside.stepAside(locationCheck, radiuses, needCoords);
    }

    public void pickUpLoot(LocationCheck locationCheck) throws Exception {
        if (Prop.SUPER_PREMIUM) {
            stepAside(locationCheck, new int[] {75, 150} );
            keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_1);
            SleepTime.sleep(1000);
        } else {
            for (TakeLoot takeLoot : loot) {
                takeLoot.pickUp();
            }
//            if (REPEAT_LOOT++ % 4 == 0)
//                stepAside(locationCheck, new int[] {75, 150} );
////                slide();
        }
    }

    public void pickUpCard() throws Exception {
        for (TakeLoot takeLoot: usefulLoot) {
            takeLoot.pickUp();
        }
//        if (REPEAT_USEFUL_LOOT++ % 4 == 0)
//            slide();
    }

    public void pickUpCard(BufferedImage screenShot) throws Exception {
        for (TakeLoot takeLoot: usefulLoot) {
            takeLoot.pickUp(screenShot);
        }
//        if (REPEAT_USEFUL_LOOT++ % 4 == 0)
//            slide();
    }

    public void dropItem(String dirInventory, String dirLoot) throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{0, 800, 0, 900});
        SleepTime.sleep(500);
        keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_E);
        SleepTime.sleep(500);
        xy = findFragmentInImage.findImage(dirInventory);
        if (xy.isPresent()) {
            mouse.mouseClick(xy.get()[0], xy.get()[1]);
        }
        SleepTime.sleep(1000);
        xy = findFragmentInImage.findImage(dirLoot);
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0], xy.get()[1]);
            mouse.pressLeft();
            mouse.mouseMove(1000, 450);
            mouse.releaseLeft();
            SleepTime.sleep(500);
            keys.keyPress(KeyEvent.VK_ENTER);
            SleepTime.sleep(700);
        }

        keys.combinationPress(KeyEvent.VK_ALT, KeyEvent.VK_E);
    }

    public void putItemToCart(String dirLoot, String dirCell) throws Exception {
        Optional<int[]> xy;
        findFragmentInImage.setScreen(new int[]{0, 800, 0, 900});
        SleepTime.sleep(1000);
        xy = findFragmentInImage.findImage(dirLoot);
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0], xy.get()[1]);
            mouse.pressLeft();
        }
        xy = findFragmentInImage.findImage(dirCell);
        if (xy.isPresent()) {
            mouse.mouseMove(xy.get()[0], xy.get()[1]);
            mouse.releaseLeft();
        }

        keys.keyPress(KeyEvent.VK_ENTER);
    }


    public void heal() throws Exception {
        keys.keyPress(Prop.HEAL_KEY);
        SleepTime.sleep(500);
//        mouse.mouseClick(800, 400);
    }

    private void slide() throws InterruptedException {
        int x =(int) (Math.random() * 1600);
        mouse.pressRight();
        SleepTime.sleep(50);
        mouse.mouseMove(x, 540);
        SleepTime.sleep(50);
        mouse.releaseRight();
    }
}
