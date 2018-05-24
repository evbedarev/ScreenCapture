package main;

import logic.Logic;
import logic.LogicGefField11;
import logic.LogicYunField11;

public class start {
    static Logic logic;

    public static void main(String[] args) throws Exception {
        logic = new LogicYunField11(0);
        logic.createThread();
    }
}
