package logic.screen_shot;

import java.awt.image.BufferedImage;

public class Link {
    private BufferedImage dData;
    private Link next;
    private Link previous;

    public Link(BufferedImage dData) {
        this.dData = dData;
        next = null;
        previous = null;
    }

    public BufferedImage getdData() {
        return dData;
    }

    public void setdData(BufferedImage dData) {
        this.dData = dData;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    public Link getPrevious() {
        return previous;
    }

    public void setPrevious(Link previous) {
        this.previous = previous;
    }

    public void displayLink() {
        if (next == null)
            System.out.println( "{" + dData + "} PreviousLink: " + previous.getdData() + " NextLink: null");
        else if (previous == null)
            System.out.println( "{" + dData + "} PreviousLink: null" + " NextLink: " + next.getdData());
        else if (next == null && previous == null)
            System.out.println("Linked list is empty");
        else
            System.out.println( "{" + dData + "} PreviousLink: " + previous.getdData() + " NextLink: " + next.getdData());
    }

    public String displayLink(int q) {
        return  "{" + dData + "}" + " PreviousLink: ";
    }
}

