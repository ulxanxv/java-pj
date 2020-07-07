package ru.ulxanxv;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        startMessage();
        startMachine();

        System.out.println("Автомат завершил работу!");
    }

    public static void startMessage() {
        System.out.println("Инструкция к автомату!\n" +
                "Цифра 1 — Бросить монету.\n" +
                "Цифра 2 — Вернуть монету.\n" +
                "Цифра 3 — Выбор типа жвачки.\n" +
                "Цифра 4 — Дёрнуть рычаг.\n" +
                "Цифра 5 — Получить жвачку.\n" +
                "Цифра 6 — Заправить автомат.\n");
    }

    public static void startMachine() {
        var context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Machine machine = context.getBean("machine", Machine.class);

        System.out.print("Ваш выбор — ");
        int digit = scanner.nextInt();
        while (digit != -1) {
            switch (digit) {
                case 1:
                    machine.tossCoin();
                    break;
                case 2:
                    machine.returnCoin();
                    break;
                case 3:
                    machine.selectGum();
                    break;
                case 4:
                    machine.pullLever();
                    break;
                case 5:
                    machine.getGum();
                    break;
                case 6:
                    machine.refuel();
                    break;
                default:
                    System.out.println("Выбрано не существующее действие!");
            }

            System.out.print("\nВаш следующий выбор — ");
            digit = scanner.nextInt();

            context.close();
        }
    }
}
