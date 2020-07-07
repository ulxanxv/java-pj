import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Window extends JFrame {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Snake snakeObject;

    private Window() {
        setTitle("Snake!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation((int)screenSize.getWidth() / 4, (int)screenSize.getHeight() / 6);
        setSize(786, 399);
        setResizable(false);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W)
                    snakeObject.setKey(Key.UP);
                else if (e.getKeyCode() == KeyEvent.VK_D)
                    snakeObject.setKey(Key.RIGHT);
                else if (e.getKeyCode() == KeyEvent.VK_S)
                    snakeObject.setKey(Key.DOWN);
                else if (e.getKeyCode() == KeyEvent.VK_A)
                    snakeObject.setKey(Key.LEFT);
            }
        });

        snakeObject = new Snake();
        add(snakeObject);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Window::new);
    }
}
