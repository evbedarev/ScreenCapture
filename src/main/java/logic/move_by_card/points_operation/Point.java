package logic.move_by_card.points_operation;

public class Point {
    private Point previous;
    private Point next;
    private int[] coordinates;

    public Point(int[] coordinates) {
        this.coordinates = coordinates;
    }
    public int[] getCoordinates() {
        return coordinates;
    }
    public Point getPrevious() {
        return previous;
    }
    public void setPrevious(Point previous) {
        this.previous = previous;
    }
    public Point getNext() {
        return next;
    }
    public void setNext(Point next) {
        this.next = next;
    }
}
