import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Client extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client::new);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
    private int side = 0;

    private ImageIcon crossIco, circleIco;

    private TicTacToe mainGraphics;
    private JTextArea log = new JTextArea(2, 15);
    private JPanel menuPanel;
    private JButton start;
    private JMenuBar jmb;
    private JMenu jMenu;

    private JMenuItem circle, cross;
    public Client() {
        setSize(450, 500);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        log.setEditable(false);
        log.setFont(new Font("monospaced", Font.PLAIN, 20));
        log.setBackground(new Color(240, 240, 240));
        log.setText("  ВЫБЕРИТЕ СТОРОНУ");

        menuPanel = new JPanel();
        jmb = new JMenuBar();
        jMenu = new JMenu("Side");

        loadingImage();
        circle = new JMenuItem("Circle", circleIco);
        cross = new JMenuItem("Cross", crossIco);
        start = new JButton("Start!");

        jMenu.add(cross);
        jMenu.add(circle);

        jmb.add(jMenu);
        menuPanel.add(jmb);
        menuPanel.add(start);

        mainGraphics = new TicTacToe(log);
        add(menuPanel, BorderLayout.NORTH);
        add(mainGraphics, BorderLayout.CENTER);
        add(log, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (mainGraphics.getIsStart())
                    if (mainGraphics.getYourStep())
                        inCoord(e.getX() - 16, e.getY() - 72);
                    else
                        log.setText("  НЕ ВАШ ХОД");
                else {
                    log.setText("  НЕ ВСЕ ИГРОКИ ГОТОВЫ ЛИБО ВЫ НЕ ВЫБРАЛИ СТОРОНУ");
                }
            }
        });

        // 1
        cross.addActionListener(e -> {
            if (!mainGraphics.getIsStart()) {
                side = 1;
                log.setText("  КРЕСТИК");
            } else {
                log.setText("  ИГРА ИДЕТ");
            }
        });
        // 2
        circle.addActionListener(e -> {
            if (!mainGraphics.getIsStart()) {
                side = 2;
                log.setText("  НОЛИК");
            } else {
                log.setText("  ИГРА ИДЕТ");
            }
        });

        start.addActionListener(e -> {
            if (!mainGraphics.getIsStart()) {
                mainGraphics.sendRequest("side " + side);

                if (side == 1) mainGraphics.setYourStep(true);
                else mainGraphics.setYourStep(false);
            } else {
                log.setText("  ИГРА ИДЕТ");
            }
        });

        setResizable(false);
        setVisible(true);
    }

    private void inCoord(int x, int y) {
        if ((x > 40 && x < 140) && y > 50 && y < 115) mainGraphics.sendRequest("c 0 0 " + side);
        if ((x > 150 && x < 285) && y > 50 && y < 115) mainGraphics.sendRequest("c 0 1 " + side);
        if ((x > 290 && x < 400) && y > 50 && y < 115) mainGraphics.sendRequest("c 0 2 " + side);

        if ((x > 40 && x < 140) && y > 145 && y < 225) mainGraphics.sendRequest("c 1 0 " + side);
        if ((x > 150 && x < 285) && y > 145 && y < 222) mainGraphics.sendRequest("c 1 1 " + side);
        if ((x > 290 && x < 400) && y > 145 && y < 225) mainGraphics.sendRequest("c 1 2 " + side);

        if ((x > 40 && x < 140) && y > 240 && y < 340) mainGraphics.sendRequest("c 2 0 " + side);
        if ((x > 150 && x < 285) && y > 240 && y < 340) mainGraphics.sendRequest("c 2 1 " + side);
        if ((x > 290 && x < 400) && y > 240 && y < 340) mainGraphics.sendRequest("c 2 2 " + side);
    }

    private void loadingImage() {
        circleIco = new ImageIcon("img/circleIco.png");
        crossIco = new ImageIcon("img/crossIco.png");
    }
}
