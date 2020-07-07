package ru.ulxanxv;

public interface IState {
    void tossCoin(Machine machine);
    void returnCoin(Machine machine);
    void selectGum(Machine machine, int select);
    void pullLever(Machine machine);
    void getGum(Machine machine);
    void refuel(Machine machine, int amountGum);
}
