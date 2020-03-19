package logic.screen_shot;

import java.awt.image.BufferedImage;

public class ScreenShotStack implements Stack{
    private volatile DoublyLinked doublyLinked = new DoublyLinked();

    @Override
    public synchronized void push(BufferedImage screenShot) {
        doublyLinked.insertFirst(screenShot);
        if (getSize() > 10) {
            for (int i = 0; i < 5; i++) {
                doublyLinked.deleteLast();
            }
        }
    }

    @Override
    public synchronized BufferedImage pop() {
        return doublyLinked.deleteFirst().getdData();
    }

    @Override
    public synchronized BufferedImage peek() {
        return doublyLinked.deleteFirst().getdData();
    }

    @Override
    public int getSize() {
        return doublyLinked.getSize();
    }
}
