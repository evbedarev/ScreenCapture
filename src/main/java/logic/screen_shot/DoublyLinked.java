package logic.screen_shot;

import java.awt.image.BufferedImage;

public class DoublyLinked {
    private Link first;
    private Link last;
    private int size = 0;

    public DoublyLinked() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(BufferedImage dd) {
        Link temLink = new Link(dd);
//        if (isEmpty()) {
//            last = temLink;
//        } else {
//            first.setPrevious(temLink);
//        }
//        temLink.setNext(first);
        first = temLink;
//        size++;
    }

    public void insertLast(BufferedImage dd) {
        Link tempLink = new Link(dd);
        if (isEmpty()) {
            first = tempLink;
        } else {
            last.setNext(tempLink);
            tempLink.setPrevious(last);
        }
        last = tempLink;
        size++;
    }

    public Link deleteFirst() {
        return first;
    }

    public Link deleteLast() {
        Link tempLink = last;
        if (first.getNext() == null) {
            first = null;
        } else
            last.getPrevious().setNext(null);
        last = last.getPrevious();
        size--;
        return tempLink;
    }

    public void displayForward() {
        System.out.println("List (first-->last)");
        Link current = first;
        while (current.getNext() != null) {
            if (current == first) {
                System.out.printf("First : ");
                current.displayLink();
            } else {
                System.out.printf("        ");
                current.displayLink();
            }
            current = current.getNext();
        }
        System.out.printf("Last  : " );
        last.displayLink();
    }

    public void displayBackward() {
        System.out.println("List (last-->first)");
        Link current = last;
        while (current.getPrevious() != null) {
            if (current == last) {
                System.out.printf("Last  : ");
                current.displayLink();
            } else {
                System.out.printf("        ");
                current.displayLink();
            }
            current = current.getPrevious();
        }
        System.out.printf("First : " );
        first.displayLink();
    }
    public int getSize() {
        return size;
    }
}
