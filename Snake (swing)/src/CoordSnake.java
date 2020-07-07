class CoordSnake {
    private int x, y;
    private Key key;

    CoordSnake(Key key) {
        this.key = key;
        this.x = 100;
        this.y = 100;
    }

    CoordSnake (CoordSnake clone) {
        this.key = clone.getKey();
        this.x = clone.getX();
        this.y = clone.getY();
    }

    CoordSnake (Key key, int x, int y) {
        this.key = key;
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void changeCoord() {
        if (key == Key.UP)
            y -= 10;
        else if (key == Key.RIGHT)
            x += 10;
        else if (key == Key.DOWN)
            y += 10;
        else if (key == Key.LEFT)
            x -= 10;


        if (x > Snake.xPanel - 50 && key == Key.RIGHT)
            x = 30;
        else if (x < 30 && key == Key.LEFT)
            x = Snake.xPanel - 50;

        if (y > Snake.yPanel - 40 && key == Key.DOWN)
            y = 30;
        else if (y < 30 && key == Key.UP)
            y = Snake.yPanel - 40;
    }

    private Key getKey() {
        return key;
    }

    void setKey(Key key) {
        if (key == Key.RIGHT && this.key == Key.LEFT ||
            key == Key.LEFT && this.key == Key.RIGHT ||
            key == Key.UP && this.key == Key.DOWN ||
            key == Key.DOWN && this.key == Key.UP)
            return;

        this.key = key;
    }
}
