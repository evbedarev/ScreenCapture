package main;

import logic.LogicForNovice;

public class start {

    public static void main(String[] args) throws Exception {
        LogicForNovice logicForNovice = new LogicForNovice(0);
        logicForNovice.createThread();
    }
}
