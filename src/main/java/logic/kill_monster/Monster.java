package logic.kill_monster;

import actions.SleepTime;
import checks.CheckSP;
import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.Capture;
import logic.RgbParameter;
import main.Prop;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Monster implements KillMonster {
    List<RgbParameter> rgbParameterList = new ArrayList<>();
    Capture capture;
    final Mouse mouse;
    final Keys keys = Keys.getInstance();
    final FindPixels findImageHard;
    private static boolean attackBySpell = false;


    public Monster() throws AWTException {
        capture = Capture.instance();
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
    }

    @Override
    public boolean kill() throws Exception {
        return findAndKill();
    }

    @Override
    public boolean killAround() throws AWTException, InterruptedException {
        return findAndKillAround();
    }

    @Override
    public boolean findMonster() throws Exception {
        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixels3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                LoggerSingle.logInfo(this.toString() + ".findMonster", "Killing monster , coordinates: x=" + x + " y=" + y);
                SleepTime.sleep(1000);
                return true;
            }
        }
        return false;
    }

    /**
     * Ищет пиксели а экране и жмет при нахождении.
     * @return boolean;
     * @throws IOException
     * @throws AWTException
     * @throws InterruptedException
     */

    @Override
    public boolean findAndKill() throws
            Exception {

        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixels3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
//                spellAttack();
//                actions.pickUpLoot();
                mouse.mouseClick(x + 5, y + 20);
                LoggerSingle.logInfo(this.toString() + ".findAndKill", "Killing monster , coordinates: x=" + x + " y=" + y);
                sleepAfterAttack();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findNearAndKill() throws
            Exception {

        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsNear3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb());

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                spellAttack();
//                actions.pickUpLoot();
                mouse.mouseClick(x, y + 5);
                LoggerSingle.logInfo(this.toString() + ".findAndKill", "Killing monster , coordinates: x=" + x + " y=" + y);
                sleepAfterAttack();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findAndKillAround() throws
            AWTException,
            InterruptedException {
        SleepTime.sleep(500);
        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsArround3Times(
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {675, 940, 335, 575});

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                spellAttack();
                mouse.mouseClick(x + 5, y + 10);
                if (attackBySpell)
                    mouse.mouseClick(x + 5, y + 10);

                LoggerSingle.logInfo(this.toString() + ".findAndKillAround", "Killing monster , coordinates: x=" + x + " y=" + y);
                sleepAfterAttack();
                return true;
            }
        }
        return false;
    }

    private void spellAttack() throws
            InterruptedException {
        int rndInt;
        if (Prop.NEED_SPELL_ATTACK && CheckSP.enoughSP) {
            rndInt = (int)(Math.random() * 2000);
            System.out.println(rndInt);
            if (rndInt < 1000) {
                keys.keyPress(Prop.SPELL_ATTACK_KEY);
                SleepTime.sleep(600);
                attackBySpell = true;
            }
        }

    }

    private void sleepAfterAttack() throws InterruptedException {
        if (attackBySpell)
            SleepTime.sleep(100);
        else
            SleepTime.sleep(2000);
        attackBySpell = false;
    }
}
