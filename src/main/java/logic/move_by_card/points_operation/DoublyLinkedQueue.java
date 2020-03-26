package logic.move_by_card.points_operation;

public class DoublyLinkedQueue {
    private Point first;
    private Point last;
    IteratorList iterator = new IteratorList(this);

    public void insertLast(int[] coords) {
       Point point = new Point(coords);
       if (last == null) {
           first = point;
       } else {
           last.setPrevious(point);
           point.setNext(last);
       }
       last = point;
    }
    public Point getFirst() {
        return first;
    }

    public IteratorList getIterator() {
        return iterator;
    }

    public boolean isEmpty() {
        return last == null;
    }
}
