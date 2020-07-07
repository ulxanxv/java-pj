import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class TicTacToe extends JPanel implements SocketThreadListener {
    private Image background, cross, circle;

    private boolean isStart = false, yourStep;
    private volatile JTextArea log;

    private int x, y;

    private SocketThread socketThread;
    private int field[][];
    TicTacToe(JTextArea log) {
        this.log = log;
        field = new int[3][3];
        try {
            background = ImageIO.read(new File("img/background.png"));
            cross = ImageIO.read(new File("img/cross.png"));
            circle = ImageIO.read(new File("img/circle.png"));

            socketThread = new SocketThread(this, new Socket("localhost", 4040));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        render(graphics);
        update();
    }

    private void render(Graphics graphics) {
        graphics.drawImage(background, 0, 0, 450, 400, this);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                if (field[i][j] == 1) {
                    equivalentCoord(i, j);
                    graphics.drawImage(cross, x, y, 70, 40, this);
                } else if (field[i][j] == 2) {
                    equivalentCoord(i, j);
                    graphics.drawImage(circle, x, y, 60, 40, this);
                }
        }
    }

    private void update() {
        repaint();
    }

    void sendRequest(String msg) {
        socketThread.sendMessage(msg);
    }

    boolean getIsStart() {
        return isStart;
    }

    private void equivalentCoord(int x1, int y1) {
        if (x1 == 0 && y1 == 0) {
            x = 50;
            y = 50;
        } else if (x1 == 0 && y1 == 1) {
            x = 165;
            y = 50;
        } else if (x1 == 0 && y1 == 2) {
            x = 305;
            y = 55;
        } else if (x1 == 1 && y1 == 0) {
            x = 40;
            y = 150;
        } else if (x1 == 1 && y1 == 1) {
            x = 160;
            y = 150;
        } else if (x1 == 1 && y1 == 2) {
            x = 300;
            y = 160;
        } else if (x1 == 2 && y1 == 0) {
            x = 40;
            y = 255;
        } else if (x1 == 2 && y1 == 1) {
            x = 155;
            y = 255;
        } else if (x1 == 2 && y1 == 2) {
            x = 305;
            y = 255;
        }
    }

    void setYourStep(boolean yourStep) {
        this.yourStep = yourStep;
    }

    boolean getYourStep() {
        return yourStep;
    }

    @Override
    public void onSocketThreadReady(SocketThread thread) {

    }

    @Override
    public void onReceiveString(SocketThread thread, String value) {
        if (value.split(" ", 4)[0].equals("c")) {
            int x = Integer.parseInt(value.split(" ", 4)[1]),
                y = Integer.parseInt(value.split(" ", 4)[2]);
            field[x][y] = Integer.parseInt(value.split(" ", 4)[3]);
            yourStep = !yourStep;

            if (yourStep)
                log.setText("  ВАШ ХОД");
            else
                log.setText("  ХОД СОПЕРНИКА");
        } else if (value.equals("strue")) {
            log.setText("  ВЫ УСПЕШНО ВЫБРАЛИ СТОРОНУ");
            socketThread.sendMessage("ready");
        } else if (value.equals("sfalse")) {
            log.setText("  СТОРОНА ЗАНЯТА");
        }

        if (value.equals("Allready")) {
            log.setText("  ИГРА НАЧАЛАСЬ!");
            isStart = true;
        }

        if (value.split(" ", 5)[0].equals("end")) {
            int x = Integer.parseInt(value.split(" ", 5)[2]),
                y = Integer.parseInt(value.split(" ", 5)[3]);
            field[x][y] = Integer.parseInt(value.split(" ", 5)[4]);

            switch (Integer.parseInt(value.split(" ", 5)[1])) {
                case 0:
                    log.setText("  НИЧЬЯ!");
                    break;
                case 1:
                    log.setText("  ПОБЕДА КРЕСТИКА!");
                    break;
                case 2:
                    log.setText("  ПОБЕДА НОЛИКА!");
            }
            isStart = false;
        }
    }

    @Override
    public void onSocketThreadInterrupted(SocketThread thread) {

    }
}
