package logic.move_by_card.points_operation;

import logic.move_by_card.Points;

public abstract class PointsAbstr implements Points {
    public DoublyLinkedQueue points = new DoublyLinkedQueue();

    @Override
    public abstract IteratorList getIterator();
}
