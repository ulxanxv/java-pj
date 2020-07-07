package ru.ulxanxv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoGum implements IState {
    @Override
    public void tossCoin(Machine machine) {
        System.out.println("Сначала заправьте автомат!");
    }

    @Override
    public void returnCoin(Machine machine) {
        System.out.println("Сначала заправьте автомат!");
    }

    @Override
    public void selectGum(Machine machine, int select) {
        System.out.println("Сначала заправьте автомат!");
    }

    @Override
    public void pullLever(Machine machine) {
        System.out.println("Сначала заправьте автомат!");
    }

    @Override
    public void getGum(Machine machine) {
        System.out.println("Сначала заправьте автомат!");
    }

    @Override
    public void refuel(Machine machine, int amountGum) {
        if (amountGum > 0) {
            machine.setAmountGum(amountGum);
            machine.setCurrentState(machine.getNoCoins());

            System.out.println("Вы успешно заправили автомат!");
        } else {
            System.out.println("Вы пытаететесь заправить ничем!");
        }
    }
}
