package main;

import logic.Logic;
//import logic.LogicGefField11;
import logic.LogicIzludDun03;
import logic.LogicPrtField07;
import logic.LogicYunField11;

public class start {
    static Logic logic;

    public static void main(String[] args) throws Exception {
        logic = new LogicPrtField07(0);
        logic.createThread();
    }
}
