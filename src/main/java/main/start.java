package main;

import actions.SleepTime;
import logic.*;

public class start {
    static Logic logic;

    public static void main(String[] args) throws Exception {
        SleepTime.sleep(5000);
        Prop.initialize();
        Prop.logic.createThread();
    }
}
