import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        doChoose();
    }

    public static void doChoose() {
        System.out.print("1. Регистрация\n2. Логин\nВаш выбор — ");

        int choose = new Scanner(System.in).nextInt();
        switch (choose) {
            case 1 -> new Register();
            case 2 -> new Login();
            default -> System.out.println("Нет такого выбора!");
        }
    }
}
