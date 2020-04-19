package logic.move_by_card.points_operation;

public class DoublyLinkedQueue {
    private Point first;
    private Point last;
    IteratorList iterator = new IteratorList(this);

    private void insertLast(int[] coords) {
       Point point = new Point(coords);
       if (last == null) {
           first = point;
       } else {
           last.setNext(point);
           point.setPrevious(last);
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
    public void add(int[] coords) {
         insertLast(coords);
    }
}
