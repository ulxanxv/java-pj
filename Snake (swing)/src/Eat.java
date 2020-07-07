class Eat {
    private int x, y;
    private boolean foodExist;

    Eat() {
        x = (((int)(Math.random() * ((Snake.xPanel - 60) / 10))) * 10) + 30;
        y = (((int)(Math.random() * ((Snake.yPanel - 70) / 10))) * 10) + 40;

        foodExist = true;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setFoodExist() {
        foodExist = !foodExist;
    }

    boolean getFoodExist() {
        return foodExist;
    }

    void generateEat() {
        x = (((int)(Math.random() * ((Snake.xPanel - 60) / 10))) * 10) + 30;
        y = (((int)(Math.random() * ((Snake.yPanel - 70) / 10))) * 10) + 30;

        foodExist = true;
    }
}
