package actions;

public final class SleepTime {
    private SleepTime(){
    }

    public static void sleep(double sleepTime) throws InterruptedException {
        SleepTime.sleep((long)(sleepTime + (sleepTime/10) * Math.random()));
    }
}
