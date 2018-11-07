package actions;

public final class SleepTime {
    private SleepTime(){
    }

    public static void sleep(double sleepTime) {
        try {
            Thread.sleep((long)(sleepTime + (sleepTime/10) * Math.random()));
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public static void loopSleep() {
        while (true) {
            sleep(5000);
        }
    }
}
