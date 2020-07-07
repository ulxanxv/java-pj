package pdoodle;

import pplatform.Coordinates;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Doodle {
    private int startY, x, y;
    private volatile boolean isPressed = false;
    private LinkedList<Coordinates> fromDoodleFrame;

    private volatile KeyEvent key;
    private CDoodleY cDoodleY;
    private Image doodleImage;

    public Doodle(Coordinates lowestElement, LinkedList<Coordinates> fromDoodleFrame) {
        this.fromDoodleFrame = fromDoodleFrame;
        x = lowestElement.getX();
        y = lowestElement.getY() - 30;
        startY = lowestElement.getY() - 30;

        try {
            doodleImage = ImageIO.read(new File("img/doodleLeft.png"));
        } catch (IOException fileError) {
            fileError.printStackTrace();
        }

        cDoodleY = new CDoodleY(this, 200);
        new CDoodleX(this);
    }

    // Проверка колизии дудла с какой-либо платформой (если она есть происходит отскок)
    void platformMatch() {
        for (Coordinates list : fromDoodleFrame)
            if ((x >= list.getX() && x <= list.getX() + 40) && (y + 30 >= list.getY() && y + 30 <= list.getY() + 10) ||
                (x + 30 >= list.getX() && x + 30 <= list.getX() + 40) && (y + 30 >= list.getY() && y + 30 <= list.getY() + 10))
                cDoodleY.changeBasePlatform(y);
    }

    public void inWall() {
        if (x > 346) {
            x = -15;
        } else if (x < -15) {
            x = 346;
        }
    }

    public boolean theEnd() {
        if (y > 761) {
            return true;
        }
        return false;
    }

    public Image getDoodleImage() {
        if (isPressed && key.getKeyCode() == KeyEvent.VK_A) {
            try {
                doodleImage = ImageIO.read(new File("img/doodleLeft.png"));
            } catch (IOException fileError) {
                fileError.printStackTrace();
            }

        } else if (isPressed && key.getKeyCode() == KeyEvent.VK_D) {
            try {
                doodleImage = ImageIO.read(new File("img/doodleRight.png"));
            } catch (IOException fileError) {
                fileError.printStackTrace();
            }
        }
        return doodleImage;
    }

    KeyEvent getKey() {
        return this.key;
    }

    public void setKey(KeyEvent key) {
        this.key = key;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    int getStartY() {
        return startY;
    }

    void setY(int y) {
        this.y = y;
    }

    void setStartY(int startY) {
        this.startY = startY;
    }

    public void changeY(int y) {
        this.y += y;
    }

    void changeX(int x) {
        this.x += x;
    }

    boolean getIsPressed() {
        return isPressed;
    }

    public void cIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getFall() {
        return cDoodleY.getFall();
    }
}
