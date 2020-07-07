import models.UserPassword;
import models.UserUnits;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class Register {

    public Register() {
        startRegister();
    }

    public void startRegister() {
        String userName, password, fullName;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter username — ");
            userName = bufferedReader.readLine();

            System.out.print("Enter password — ");
            password = bufferedReader.readLine();

            System.out.print("Enter fullName — ");
            fullName = bufferedReader.readLine();

            registerUser(userName, password, fullName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean registerUser(String username, String password, String fullName) {
        EntityManagerFactory factory    = Persistence.createEntityManagerFactory("example");
        EntityManager manager           = factory.createEntityManager();
        EntityTransaction transaction   = manager.getTransaction();

        transaction.begin();
        /** START TRANSACTION **/
        UserUnits units             = new UserUnits("This user register in " + new Date().toString(), fullName);
        UserPassword userPassword   = new UserPassword(username, password, units);

        try {
            manager.persist(userPassword);
        } catch (Throwable ex) {
            /** FAIL TRANSACTION **/
            System.out.println("FAIL TRANSACTION!");

            transaction.rollback();

            manager.close();
            factory.close();

            return false;
        }

        /** SUCCESSFULLY END TRANSACTION **/
        transaction.commit();

        manager.close();
        factory.close();

        return true;
    }
}
