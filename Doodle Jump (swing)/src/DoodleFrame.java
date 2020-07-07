import pdoodle.Doodle;
import pplatform.Coordinates;
import pplatform.Platform;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class DoodleFrame extends JPanel {
    private LinkedList<Coordinates> coordPlatform = new LinkedList<>();
    private Platform platform = new Platform();
    private Doodle doodle;

    private Image background;
    DoodleFrame() {
        try {
            background = ImageIO.read(new File("img/background.png"));
        } catch (IOException fileError) {
            fileError.printStackTrace();
        }
        // Первичная генерация платформ
        platform.coordGeneration(coordPlatform, 20);
        doodle = new Doodle(platform.lowestElement(coordPlatform), coordPlatform);
    }

    public void paintComponent(Graphics graphics) {
        // Камера
        if (((doodle.getY() < getWidth() / 2) && !doodle.getFall())) {
            platform.coordGeneration(coordPlatform);
            doodle.changeY(1);
        }

        doodle.inWall();
        drawing(graphics);
        sleep();
        repaint();
    }

    void setKey(KeyEvent key) {
        doodle.setKey(key);
    }

    void cIsPressed(boolean isPressed) {
        doodle.cIsPressed(isPressed);
    }

    private void drawing(Graphics graphics) {
        if (!doodle.theEnd()) {
            Image bPlatformImage = platform.getPlatformImage();
            graphics.drawImage(background, 0, 0, 500, 800, this);
            // Отрисовка всех элементов списка с координатами платформ
            for (Coordinates list : coordPlatform)
                graphics.drawImage(bPlatformImage, list.getX(), list.getY(), 40, 10, this);

            graphics.drawImage(doodle.getDoodleImage(), doodle.getX(), doodle.getY(), this);
        }
        else {
            try {
                background = ImageIO.read(new File("img/backgroundEnd.png"));
            } catch (IOException fileError) {
                fileError.printStackTrace();
            }
            graphics.drawImage(background, 0, 0, 500, 800, this);
        }
    }

    private void sleep() {
        long sTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - sTime < 2);
    }
}
