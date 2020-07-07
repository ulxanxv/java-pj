import java.util.Random;

public class Point implements Comparable<Point> {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x;
    private int y;

    private int signed;
    private int denominator;
    private int b;

    private boolean reverse;

    public Point() {
        initialize();
    }

    private void initialize() {
        Random random = new Random();

        this.signed      = random.nextBoolean() ? -1 : 1;
        this.denominator = random.nextInt(5) + 1;
        this.b           = random.nextInt(Window.WIDTH * 2) - 1200;

        this.x           = random.nextInt(Window.WIDTH);
        this.y           = Math.abs(signed * (x / denominator) + b);

        this.reverse     = random.nextBoolean();
    }

    public void offset() {
        if ((x < Window.WIDTH - 20 && y < Window.HEIGHT - 40) && (x > 0 && y > 0)) {
            x = reverse ? x - 1 : x + 1;
        } else {
            initialize();
        }
        y = Math.abs(signed * (x / denominator) + b);
    }

    @Override
    public int compareTo(Point point) {
        return this.x - point.getX();
    }
}
