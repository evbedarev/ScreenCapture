package logic.take_loot;

import checks.CheckHP;
import checks.GefField05;
import checks.VerifyMap;
import email.SendMessage;
import key_and_mouse.Keys;
import key_and_mouse.Mouse;
import logic.kill_monster.Attack;

import java.util.concurrent.atomic.AtomicInteger;

abstract class AbstractLocation extends Thread{
    int count = 0;
    int countForSendMsg = 0;
    int threadId;
    final Mouse mouse = new Mouse();
    final CheckHP checkHP = new CheckHP();
    final static AtomicInteger atomicInt = new AtomicInteger(0);
    VerifyMap verifyMap = new GefField05();
    SendMessage sendMessage = new SendMessage();
    Keys keys;
    Attack attack;

    AbstractLocation (int threadId) throws Exception {
        this.threadId = threadId;
        keys = new Keys();
        attack = new Attack();
    }


}
