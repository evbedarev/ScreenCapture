package main;

import logic.Logic;
import logic.LogicGefField11;

public class start {
    static Logic logic;

    public static void main(String[] args) throws Exception {
        logic = new LogicGefField11(0);
        logic.createThread();
    }
}
