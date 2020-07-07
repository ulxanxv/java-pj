import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

class Snake extends JPanel {
    static int xPanel = 780, yPanel = 360;
    private LinkedList<CoordSnake> coordSnakes =  new LinkedList<>();

    private Image bodySnake, eatTexure, backGroundImage, gameOver;
    private boolean theEnd = false;
    private Eat eat;

    Snake() {
        eat = new Eat();
        try {
            bodySnake = ImageIO.read(new File("img/bodySnake.png"));
            eatTexure = ImageIO.read(new File("img/eatTexture.png"));
            backGroundImage = ImageIO.read(new File("img/backGroundImage.png"));
            gameOver = ImageIO.read(new File("img/gameOver.png"));
        } catch (IOException e) {
            System.out.println(e);
        }

        coordSnakes.add(new CoordSnake(Key.RIGHT));
        coordSnakes.add(new CoordSnake(Key.RIGHT, 90, 100));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateWindow(g);
        drawingElement(g);
    }

    private void updateWindow(Graphics g) {
        updateEat();
        updateLinkedList();
        sleep(g);
    }

    private void updateLinkedList() {
        // Если в методе updateEat() был выполнен второй условный блок, то
        // выполниться первое условие
        if (eat.getFoodExist()) {
            // Основной процесс алгоритма перемещения змейки описан в этом цикле
            // (не нужно выполнять его, если была съедена еда и добавился новый элемент, потому что в таком случае всё и так обработается отлично)
            for (int i = coordSnakes.size() - 1; i > 1 && coordSnakes.size() > 1; i--) {
                coordSnakes.add(i, new CoordSnake(coordSnakes.get(i - 1)));
                coordSnakes.remove(i + 1);
            }
            coordSnakes.remove(1);
        }
        // Иначе голова не будет удалена, здесь просто сгенирируется новая еда
        else {
            while (correctPositionOfFood())
                eat.generateEat();
        }
    }

    private void updateEat() {
        // Если координаты еды совпали с координатами головы змейки, то
        // производится создание новой голове без её последущего удаления
        if (coordSnakes.get(0).getX() == eat.getX() && coordSnakes.get(0).getY() == eat.getY()) {
            coordSnakes.add(0, new CoordSnake(coordSnakes.get(0)));
            coordSnakes.get(0).changeCoord();

            eat.setFoodExist();
        }
        // Иначе создаем дубликат головы с последующим удалением оригинала (необходимо для функционирования алгоритма)
        else {
            coordSnakes.add(0, new CoordSnake(coordSnakes.get(0)));
            coordSnakes.get(0).changeCoord();
        }
    }

    private void drawingElement(Graphics g) {
        g.drawImage(backGroundImage, 0, 0, this);

        if (!theEnd) {
            // Отрисовка элементов змейки
            for (CoordSnake x : coordSnakes)
                g.drawImage(bodySnake, x.getX(), x.getY(), 10, 10, this);

            // Если еда есть, то нарисовать её
            if (eat.getFoodExist())
                g.drawImage(eatTexure, eat.getX(), eat.getY(), 10, 10, this);
        }
        else
            g.drawImage(gameOver, 180, 150, 420, 50, this);
    }

    // Проверка случайной генерации еды внутри змеи
    private boolean correctPositionOfFood() {
        for (CoordSnake x : coordSnakes) {
            if (x.getX() == eat.getX() && x.getY() == eat.getY())
                return true;
        }
        return false;
    }

    // Проверка на столкновение с самим собой или победу в игре
    private synchronized int theEnd() {
        for (int i = 0; i < coordSnakes.size(); i++)
            for (int j = i + 1; j < coordSnakes.size(); j++) {
                if ((coordSnakes.get(i).getX() == coordSnakes.get(j).getX()) && (coordSnakes.get(i).getY() == coordSnakes.get(j).getY()))
                    return 1;
            }
        return 0;
    }

    private void sleep(Graphics g) {
        long sTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - sTime < 100);

        if (theEnd() == 0)
            repaint();
        else {
            theEnd = true;
            repaint();
        }
    }

    // Метод взаимодействует с KeyListener из класса Window
    void setKey(Key value) {
        coordSnakes.get(0).setKey(value);
    }
}
