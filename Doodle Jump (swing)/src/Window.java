import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame {
    private final static int WIDTH = 400, HEIGHT = 800;

    private Window() {
        setTitle("doodle Jump!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)(screenSize.getWidth() / 2 - (WIDTH / 2)), (int)(screenSize.getHeight() / 2 - (HEIGHT / 2)));
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);

        DoodleFrame frameWork = new DoodleFrame();
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent key) {}
            public void keyPressed(KeyEvent key) {
                if (key.getKeyCode() == KeyEvent.VK_A) {
                    frameWork.setKey(key);
                    frameWork.cIsPressed(true);

                } else if (key.getKeyCode() == KeyEvent.VK_D) {
                    frameWork.setKey(key);
                    frameWork.cIsPressed(true);
                }
            }
            public void keyReleased(KeyEvent key) {
                if (key.getKeyCode() == KeyEvent.VK_A || key.getKeyCode() == KeyEvent.VK_D) {
                    key.setKeyCode(KeyEvent.VK_END);
                    frameWork.cIsPressed(false);
                }
            }
        });
        add(frameWork);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Window::new);
    }
}
