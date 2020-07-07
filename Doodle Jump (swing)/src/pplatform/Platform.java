package pplatform;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class Platform {
    private Image platformImage;

    public Platform() {
        try {
            platformImage = ImageIO.read(new File("img/platform.png"));
        } catch (IOException fileError) {
            fileError.printStackTrace();
        }
    }

    public Image getPlatformImage() {
        return platformImage;
    }

    // Метод отвечающий за генерацию платформ во время выполнения
    // (я не смог объеденить метод с нижним сделав что-то универсальное, скорее всего это возможно)
    public void coordGeneration(LinkedList<Coordinates> fromMain) {

        for (int i = 0; i < fromMain.size(); i++) {
            if (fromMain.get(i).getY() - 1 >= 701) {
                int x, y;
                fromMain.remove(i);
                do {
                    x = (int)(Math.random() * 294) + 30;
                    y = (int)(Math.random() * 50) - 50;
                } while (collisionCheck(fromMain, x, y));
                fromMain.add(new Coordinates(x, y));
            }
            fromMain.get(i).changeY(1);
        }
    }

    // Метод служит для ПЕРВИЧНОЙ генерации платформ
    public void coordGeneration(LinkedList<Coordinates> fromMain, int startSize) {
        int x, y;

        for (int i = 0; i < startSize; i++) {
            do {
                x = (int)(Math.random() * 294) + 30;
                y = (int)(Math.random() * 701) + 20;
            } while (collisionCheck(fromMain, x, y));
            fromMain.add(new Coordinates(x, y));
        }
    }

    // При генерации платформ метод проверяет наличии коллизии прежде, чем происходит добавление
    // новой платформы
    private boolean collisionCheck(LinkedList<Coordinates> fromDoodleFrame, int x, int y) {
        for (Coordinates list : fromDoodleFrame)
            if ((x >= list.getX() && x <= list.getX() + 40) && (y >= list.getY() && y <= list.getY() + 10) ||
                (x + 40 >= list.getX() && x + 40 <= list.getX() + 40) && (y >= list.getY() && y <= list.getY() + 10) ||
                (x + 40 >= list.getX() && x + 40 <= list.getX() + 40) && (y + 10 >= list.getY() && y + 10 <= list.getY() + 10) ||
                (x >= list.getX() && x <= list.getX() + 40) && (y + 10 >= list.getY() && y + 10 <= list.getY() + 10))
                return true;
        return false;
    }

    // При начальной генерации метод опеределяет куда вставать дудлу - находит самую нижнию платформу
    public Coordinates lowestElement(LinkedList<Coordinates> fromDoodleFrame) {
        int index = 0;
        for (int i = 0; i < fromDoodleFrame.size(); i++)
            if (fromDoodleFrame.get(index).getY() < fromDoodleFrame.get(i).getY())
                index = i;

        return fromDoodleFrame.get(index);
    }
}
