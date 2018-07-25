package main;

import logic.*;

public class start {
    static Logic logic;

    public static void main(String[] args) throws Exception {
        Prop.initialize();
        Prop.logic.createThread();
    }
}
