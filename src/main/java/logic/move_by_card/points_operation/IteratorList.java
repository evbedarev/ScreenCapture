package logic.move_by_card.points_operation;

public class IteratorList {
    private final DoublyLinkedQueue queue;
    private Point current;
    private boolean reversed = false;

    public IteratorList(DoublyLinkedQueue queue) {
        this.queue = queue;
    }
    public int[] next() {
        if(current == null) {
            current = queue.getFirst();
        } else if (!reversed && current.getNext() == null) {
            reversed = true;
        } else if (reversed && current.getPrevious() == null) {
            reversed = false;
        } else if (!reversed) {
            current = current.getNext();
        } else {
            current = current.getPrevious();
        }
        return current.getCoordinates();
    }
    public int[] nextOneWay() {
        if(current == null) {
            current = queue.getFirst();
        } else if (current.getNext() == null) {
            return null;
        } else {
            current = current.getNext();
        }
        return current.getCoordinates();
    }

    public void reset() {
        current = null;
    }

    public int[] findNearest(int[] myPoint) {
        int hypotenuseCurr, hypotenuseNearest;
        current = queue.getFirst();
        Point nearest = current;
        System.out.println(current.getNext());
        while (current.getNext() != null) {
            hypotenuseCurr = calcHypotenuse(myPoint, current);
            hypotenuseNearest = calcHypotenuse(myPoint, nearest);
            if (hypotenuseNearest > hypotenuseCurr)
                nearest = current;
            System.out.println("nearest" + nearest.getCoordinates()[0]);
            next();
        }
        current = nearest;
        return nearest.getCoordinates();
    }
    public int calcHypotenuse(int[] myPoint, Point point){
        int lenX = Math.abs(myPoint[0] - point.getCoordinates()[0]);
        int lenY = Math.abs(myPoint[1] - point.getCoordinates()[1]);
        return (int) Math.sqrt(Math.pow(lenX, 2) + Math.pow(lenY, 2));
    }
}
