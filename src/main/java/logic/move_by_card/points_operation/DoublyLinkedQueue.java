package logic.move_by_card.points_operation;

public class DoublyLinkedQueue {
    private Point first;
    private Point last;

    public void insertFirst(int[] coords) {
       Point point = new Point(coords);
       if (first == null) {
           last = point;
       } else {
           point.setNext(first);
           first.setPrevious(point);
       }
       first = point;
    }



}
