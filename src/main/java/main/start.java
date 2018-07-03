package main;

import logic.*;
//import logic.LogicGefField11;


public class start {
    static Logic

            logic;

    public static void main(String[] args) throws Exception {
        logic = new LogicYunField11(0);
        logic.createThread();
    }
}
