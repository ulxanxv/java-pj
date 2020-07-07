package pplatform;

public class Coordinates {
    private int x, y;

    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void changeY(int d) {
        this.y += d;
    }
}
