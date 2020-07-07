import models.UserPassword;
import models.UserUnits;
import org.hibernate.mapping.Collection;
import org.w3c.dom.ls.LSOutput;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@SuppressWarnings(value = "unchecked")
public class Login {

    public Login() {
        startLogin();
    }

    public void startLogin() {
        EntityManagerFactory factory    = Persistence.createEntityManagerFactory("example");
        EntityManager manager           = factory.createEntityManager();

        String fullName;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter fullName — ");
            fullName = "\'" + bufferedReader.readLine() + "\'";

            Query query = manager.createNativeQuery("SELECT usr_description, user_fullname, username, password FROM user_units AS uu JOIN users_passwords AS up ON uu.id = up.user_id WHERE user_fullname = " + fullName);
            List<Object[]> resultList = query.getResultList();

            if (resultList.size() != 0) {
                System.out.println("Такой пользователь найден!");
                loginUser(resultList);
            } else {
                System.out.println("Не найдено такое имя!");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loginUser(List<Object[]> resultList) {
        String username, password;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter username — ");
            username = bufferedReader.readLine();
            System.out.print("Enter password — ");
            password = bufferedReader.readLine();

            for (Object[] x : resultList) {
                if (((String) x[2]).equals(username) && ((String) x[3]).equals(password)) {
                    System.out.println("Вы прошли авторизацию!");
                    return;
                }
            }

            System.out.println("Вы не прошли авторизацию!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
