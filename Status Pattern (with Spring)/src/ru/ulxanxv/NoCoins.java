package ru.ulxanxv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoCoins implements IState {

    @Override
    public void tossCoin(Machine machine) {
        if (machine.getAmountGum() > 0) {
            machine.setCurrentState(machine.getPresentCoins());
            System.out.println("Вы успешно бросили монету :)");
        } else {
            machine.setCurrentState(machine.getNoGum());
            System.out.println("Автомат опустел ;( Вам придёться его заправить, чтобы продолжить!");
        }
    }

    @Override
    public void returnCoin(Machine machine) {
        System.out.println("Возвращать нечего ;(");
    }

    @Override
    public void selectGum(Machine machine, int select) {
        System.out.println("Сначала бросьте монету в автомат!");
    }

    @Override
    public void pullLever(Machine machine) {
        System.out.println("Сначала бросьте монету в автомат!");
    }

    @Override
    public void getGum(Machine machine) {
        System.out.println("Сначала бросьте монету в автомат!");
    }

    @Override
    public void refuel(Machine machine, int amountGum) {
        if (amountGum > 0) {
            System.out.println("Вы успешно заправили автомат!");
            machine.setAmountGum(machine.getAmountGum() + amountGum);
        } else {
            System.out.println("Вы пытаететесь заправить ничем!");
        }
    }
}
