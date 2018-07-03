package routes;

public class Route {
    int[] coordsToMove;
    int sleepTime;
    int[] checkCoords;

    public Route(int[] coordsToMove, int sleepTime, int[] checkCoords) {
        this.coordsToMove = coordsToMove;
        this.sleepTime = sleepTime;
        this.checkCoords = checkCoords;
    }

    public int[] getCoordsToMove() {
        return coordsToMove;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public int[] getCheckCoords() {
        return checkCoords;
    }
}
