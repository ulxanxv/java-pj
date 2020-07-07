package pdoodle;

import java.awt.event.KeyEvent;

class CDoodleX implements Runnable {
    private Doodle cloneDoodle;
    private double inertiaIndex = 0.0;

    CDoodleX(Doodle cloneDoodle) {
        Thread thread = new Thread(this);
        this.cloneDoodle = cloneDoodle;

        thread.start();
    }

    @Override
    public void run() {
        while(true)
            moveDoodleX();
    }

    private void moveDoodleX() {
        if ((int)inertiaIndex > 0) {
            // Движение вправо
            if (cloneDoodle.getIsPressed() && cloneDoodle.getKey().getKeyCode() == KeyEvent.VK_D) {
                cloneDoodle.changeX(1);
                inertiaIndex += 0.25;
            } else if (cloneDoodle.getIsPressed() && cloneDoodle.getKey().getKeyCode() == KeyEvent.VK_A) {
                cloneDoodle.changeX(1);
                inertiaIndex -= 2;
            } else {
                cloneDoodle.changeX(1);
                inertiaIndex -= 1;
            }
        } else if ((int)inertiaIndex < 0) {
            // Движение влево
            if (cloneDoodle.getIsPressed() && cloneDoodle.getKey().getKeyCode() == KeyEvent.VK_A) {
                cloneDoodle.changeX(-1);
                inertiaIndex -= 0.25;
            } else if (cloneDoodle.getIsPressed() && cloneDoodle.getKey().getKeyCode() == KeyEvent.VK_D) {
                cloneDoodle.changeX(-1);
                inertiaIndex += 2;
            } else {
                cloneDoodle.changeX(-1);
                inertiaIndex += 1;
            }
        } else {
            // Безынерционное движение
            if (cloneDoodle.getIsPressed() && cloneDoodle.getKey().getKeyCode() == KeyEvent.VK_D) {
                cloneDoodle.changeX(1);
                inertiaIndex += 0.25;
            } else if (cloneDoodle.getIsPressed() && cloneDoodle.getKey().getKeyCode() == KeyEvent.VK_A) {
                cloneDoodle.changeX(-1);
                inertiaIndex -= 0.25;
            }
        }

        // sleep((long)(Math.sin((sinPeek()) * 0.0157) * 4));
        sleep((long)((Math.sin(1.57 + (inertiaIndex < 0 ? inertiaIndex * - 1 : inertiaIndex) / 10))) * 4);
    }

    private void sleep(long time) {
        if (time < 4) {
            time = 4;
        } else if (time > 7) {
            time = 7;
        }


        long sTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - sTime < time);
    }
}
