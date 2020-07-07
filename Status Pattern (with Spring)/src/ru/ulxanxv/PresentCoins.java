package ru.ulxanxv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PresentCoins implements IState, IGetSelect {
    private String  select;
    private boolean pullAllow;

    public PresentCoins() {
        this.select = "null";
        this.pullAllow = false;
    }

    @Override
    public void tossCoin(Machine machine) {
        System.out.println("Вы уже бросили монету!");
    }

    @Override
    public void returnCoin(Machine machine) {
        System.out.println("Вы успешно вернули монету!");
        machine.setCurrentState(machine.getNoCoins());
    }

    @Override
    public void selectGum(Machine machine, int select) {
        switch (select) {
            case 1:
                this.select = "Eclipse"; pullAllow = true;
                System.out.println("Выбрана первая жвачка!");
                break;
            case 2:
                this.select = "Orbit"; pullAllow = true;
                System.out.println("Выбрана вторая жвачка!");
                break;
            default:
                System.out.println("Вы выбрали не существующую жвачку!");
        }
    }

    @Override
    public void pullLever(Machine machine) {
        if (pullAllow) {
            machine.setCurrentState(machine.getGumSold());
            System.out.println("Автомат готов к выдаче жвачки!");
        } else {
            System.out.println("Сначала выберите жвачку!");
        }
    }

    @Override
    public void getGum(Machine machine) {
        System.out.println("Дёрните за рычаг!");
    }

    @Override
    public void refuel(Machine machine, int amountGum) {
        System.out.println("В данный момент невозможно заправить автомат!");
    }

    @Override
    public String getSelect() {
        return select;
    }

    @Override
    public void setSelect(String select) {
        this.select = select;
    }
}
