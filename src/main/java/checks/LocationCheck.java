package checks;

import actions.Actions;
import checks.location.VerifyMap;
import email.MsgLocationChanged;
import email.SendMessage;
import key_and_mouse.Mouse;
import logic.kill_monster.KillMonster;
import logic.kill_monster.Warp;
import org.apache.log4j.Logger;

import java.awt.*;

public class LocationCheck {
    private VerifyMap verifyMap;
    private Logger logger;
    private final Mouse mouse;
    private Actions actions;
    private int countForSendMsg = 0;
    SendMessage sendMessage = new SendMessage();

    public LocationCheck(VerifyMap verifyMap, Logger logger) throws AWTException {
        this.verifyMap = verifyMap;
        this.logger = logger;
        mouse = new Mouse();
        actions = Actions.instance();
    }

    void findWaprPortal() throws Exception {
        KillMonster goToWarp = new Warp(logger);
        double t = Math.PI/6;
        double radius = 125;
        int i = 0;
        int x;
        int y;
        while (!goToWarp.kill()) {
            i++;
            Thread.sleep(200);
            if (i%20 == 0) {
                t += Math.PI/6;
                x = (int) Math.round(radius * Math.cos(t));
                y = (int) Math.round(radius * Math.sin(t));
                mouse.mouseMove(800 + x, 450 + y);
            }
            if (verifyMap.onDesiredLocation())
                break;
        }
    }

    public void locationCheck() throws Exception {
        while (!verifyMap.onDesiredLocation()) {
            Thread.sleep(5000);
            logger.info("Нахожусь не на карте!!");
            findWaprPortal();
            Thread.sleep(2000);
            if (verifyMap.onDesiredLocation()) {
                actions.teleport();
                Thread.sleep(2000);
            }
            countForSendMsg++;
            if (countForSendMsg == 100) {
                sendMessage.send(new MsgLocationChanged());
            }
        }
        countForSendMsg = 0;
    }
}
