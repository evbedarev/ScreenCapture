package logic.kill_monster;

import checks.CheckSP;
import find_image.FindPixels;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logger.LoggerSingle;
import logic.Capture;
import logic.RgbParameter;
import main.Prop;
import java.awt.*;
import java.awt.image.BufferedImage;
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


    public Monster() throws AWTException {
        capture = Capture.instance();
        mouse = Mouse.getInstance();
        findImageHard = new FindPixels();
    }

    @Override
    public boolean kill() throws Exception {
        BufferedImage screenShot = capture.takeScreenShot();
        return findAndKill(screenShot);
    }

    @Override
    public boolean killAround() throws AWTException, InterruptedException {
        BufferedImage screenShot = capture.takeScreenShot();
        return findAndKillAround(screenShot);
    }

    /**
     * Ищет пиксели а экране и жмет при нахождении.
     * @return boolean;
     * @throws IOException
     * @throws AWTException
     * @throws InterruptedException
     */

    @Override
    public boolean findAndKill(BufferedImage screenShot) throws
            Exception {

        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsInImage(
                    screenShot,
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
                Thread.sleep(1000);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findAndKillAround(BufferedImage screenShot) throws
            AWTException,
            InterruptedException {

        LoggerSingle.logDebug(this.toString(), "Finding monster ");
        //It's bad, later change. Need to load in constructor.
        for (RgbParameter parameter: rgbParameterList) {
            Optional<int[]> xy = findImageHard.findPixelsInImageInArea(
                    screenShot,
                    parameter.getMainRgb(),
                    parameter.getSubImageSize(),
                    parameter.getAncillaryRgb(),
                    new int[] {675, 940, 335, 575});

            if (xy.isPresent()) {
                int x = xy.get()[0];
                int y = xy.get()[1];
                spellAttack();
                mouse.mouseClick(x, y + 5);
                LoggerSingle.logInfo(this.toString() + ".findAndKillAround", "Killing monster , coordinates: x=" + x + " y=" + y);
                Thread.sleep(1000);
                return true;
            }
        }
        return false;
    }

    private void spellAttack() throws
            InterruptedException {

        if (Prop.NEED_SPELL_ATTACK && CheckSP.enoughSP) {
            keys.keyPress(Prop.SPELL_ATTACK_KEY);
            Thread.sleep(500);
        }
    }
}
