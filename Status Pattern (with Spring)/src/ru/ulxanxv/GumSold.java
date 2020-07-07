package ru.ulxanxv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GumSold implements IState {

    private IGetSelect presentCoins;

    @Autowired
    public GumSold(IGetSelect presentCoins) {
        this.presentCoins = presentCoins;
    }

    @Override
    public void tossCoin(Machine machine) {
        System.out.println("Бросить монету невозомжно! Заберите жвачку!");
    }

    @Override
    public void returnCoin(Machine machine) {
        System.out.println("Вернуть монету невозомжно! Заберите жвачку!");
    }

    @Override
    public void selectGum(Machine machine, int select) {
        System.out.println("Вы уже выбирали жвачку! Заберите жвачку!");
    }

    @Override
    public void pullLever(Machine machine) {
        System.out.println("Вы уже дёргали за рычаг! Нажмите получить жвачку!");
    }

    @Override
    public void getGum(Machine machine) {
        System.out.println("Жвачка " + presentCoins.getSelect() + " выдана! Заберите пожалуйста!");

        if (machine.getAmountGum() > 0) {
            System.out.println("Автомат готов к следующей продаже!");
            machine.setCurrentState(machine.getNoCoins());
        } else {
            System.out.println("В автомате больше нет жвачки!");
            machine.setCurrentState(machine.getNoGum());
        } presentCoins.setSelect("null");
    }

    @Override
    public void refuel(Machine machine, int amountGum) {
        System.out.println("В данный момент невозможно заправить автомат!");
    }
}
