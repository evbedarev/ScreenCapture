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
}
