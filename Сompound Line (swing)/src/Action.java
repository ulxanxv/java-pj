import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.*;

public final class Action extends JPanel {

    private final int SIZE = 10;

    private final int SIZE_CIRCLE = 150;


    private ArrayList<Point> points = new ArrayList<>();

    public Action() {
        fillPoints();

        // addMouseMotionListener(new MouseMotionListener() {
        //     @Override
        //     public void mouseDragged(MouseEvent mouseEvent) {
        //     }
        //     @Override
        //     public void mouseMoved(MouseEvent mouseEvent) {
        //         mouseX = mouseEvent.getX() - (SIZE_CIRCLE / 2);
        //         mouseY = mouseEvent.getY() - (SIZE_CIRCLE / 2);
        //     }
        // });
    }

    private void fillPoints() {

        for (int i = 0; i < SIZE; i++) {
            points.add(new Point());
        }

    }

    @Override
    public void paint(Graphics g) {
        long startTime = System.nanoTime();

        render(g);
        repaint();

        System.out.print(System.nanoTime() - startTime);
    }

    private void render(Graphics g) {
        int SIZE_OVAL = 3;

        Collections.sort(points);

        System.out.println();
        
        points.forEach(x -> {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);

            compound(g);

            x.offset();

            g.setColor(Color.BLACK);
            g.drawOval(x.getX(), x.getY(), SIZE_OVAL, SIZE_OVAL);

            // g.drawOval(mouseX, mouseY, SIZE_CIRCLE, SIZE_CIRCLE);
        });
    }

    private int opacity(Point p1, Point p2) {
        int distance = ((int) Point2D.distance(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
        return distance > 200 ? 0 : 200 - distance;
    }

    private void compound(Graphics g) {
        Point p1;
        Point p2;
        
        Object[] array = points.toArray();
        
        for (int i = 0; i < SIZE - 1; i++) {
            p1 = ((Point) array[i]);
            for (int j = i + 1; j < SIZE; j++) {
                p2 = ((Point) array[j]);

                int opacity = opacity(p1, p2);

                if (p1.getX() + 200 < p2.getX()) break;

                if (opacity > 0) {
                    g.setColor(new Color(0, 0, 0, opacity));
                    g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
                }
            }
        }
    }
}
