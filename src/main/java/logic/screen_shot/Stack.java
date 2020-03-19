package logic.screen_shot;

import java.awt.image.BufferedImage;

public interface Stack {
    void push(BufferedImage screenShot);
    BufferedImage pop();
    BufferedImage peek();
    int getSize();
}
