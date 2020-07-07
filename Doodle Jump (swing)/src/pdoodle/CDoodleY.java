package pdoodle;

public class CDoodleY implements Runnable {
    private Doodle cloneDoodle;
    private int amountPixel;
    private int maxJump;
    private boolean fall = false;

    CDoodleY(Doodle cloneDoodle, int maxJump) {
        Thread thread = new Thread(this);
        this.cloneDoodle = cloneDoodle;
        this.maxJump = maxJump;

        amountPixel = maxJump;
        thread.start();
    }

    // Многопоточность нужна из за метода sleep(), который для X и Y эмулирует плавность движения
    // Не сложно догодаться что будет без многопоточности из за двух методов sleep()
    @Override
    public void run() {
        while (true)
            moveDoodleY();
    }

    // Метод изменят базовую платформу от которой нужно производить отскок
    void changeBasePlatform(int y) {
        if (fall) {
            cloneDoodle.setY(y);
            cloneDoodle.setStartY(y);
            amountPixel = maxJump;
            fall = !fall;
        }
    }

    private void moveDoodleY() {
        if (fall) {
            cloneDoodle.platformMatch();
        }

        if (amountPixel-- == 0) {
            fall = !fall;
            cloneDoodle.setStartY(0);
        }

        if (!fall) {
            cloneDoodle.changeY(-1);
        } else {
            cloneDoodle.changeY(1);
        }

        // Синус нужен для более плавного изменения скорости по Y-координате
        sleep((long)(Math.sin((sinPeek()) * 0.0157) * 4));
    }

    // Метод устанавливает границы значений синуса, так как в некоторых точках он возвращает 0, что делает
    // перемещения сверхбыстрыми
    private int sinPeek() {
        int y = cloneDoodle.getY(), startY = cloneDoodle.getStartY();

        if (startY - y <= 55)
            return 55;
        if (startY - y >= 145)
            return 140;
        return (startY - y > 75 && startY - y < 125) ? 75 : startY - y;
    }

    private void sleep(long time) {
        long sTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - sTime < time);
    }

    boolean getFall() {
        return fall;
    }
}
