package ru.ulxanxv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Machine {
    private IState currentState;

    private IState noCoins;
    private IState presentCoins;
    private IState noGum;
    private IState gumSold;

    @Value(value = "${machine.gum}")
    private int amountGum;

    @Autowired
    public Machine(@Qualifier("noCoins") IState currentState,
                   @Qualifier("noCoins") IState noCoins,
                   @Qualifier("presentCoins") IState presentCoins,
                   @Qualifier("noGum") IState noGum,
                   @Qualifier("gumSold") IState gumSold) {
        this.currentState = currentState;
        this.noCoins = noCoins;
        this.presentCoins = presentCoins;
        this.noGum = noGum;
        this.gumSold = gumSold;
    }

    public void tossCoin() {
        currentState.tossCoin(this);
    }

    public void returnCoin() {
        currentState.returnCoin(this);
    }

    public void selectGum() {
        System.out.print("Пожалуйста, выберите тип жвачки: 1 — Eclipse, 2 — Orbit. — ");
        currentState.selectGum(this, new Scanner(System.in).nextInt());
    }

    public void pullLever() {
        currentState.pullLever(this);
    }

    public void getGum() {
        currentState.getGum(this);
    }

    public void refuel() {
        System.out.print("Пожалуйста, укажите сколько добавить жвачек — ");
        currentState.refuel(this, new Scanner(System.in).nextInt());
    }

    public IState getNoCoins() {
        return noCoins;
    }

    public IState getPresentCoins() {
        return presentCoins;
    }

    public IState getNoGum() {
        return noGum;
    }

    public IState getGumSold() {
        return gumSold;
    }

    public int getAmountGum() {
        return amountGum;
    }

    public void setAmountGum(int amountGum) {
        this.amountGum = amountGum;
    }

    public void setCurrentState(IState currentState) {
        this.currentState = currentState;
    }
}
