package logic.move_by_card.points_operation;

public class IteratorList {
    DoublyLinkedQueue queue;
    Point current;
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
}
