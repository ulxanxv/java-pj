import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Window extends JFrame {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    public Window() {
        // Настройка окна
        this.setTitle                 ("Doodle");
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setLocation              ((int)(screenSize.getWidth() / 2 - (WIDTH / 2)), (int)(screenSize.getHeight() / 2 - (HEIGHT / 2)));
        this.setSize                  (this.WIDTH, this.HEIGHT);
        this.setResizable             (false);
        this.setBackground(Color.WHITE);

        add(new Action());

        // Отрисовка
        this.setVisible               (true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Window::new);
    }
}
