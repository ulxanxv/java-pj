import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server implements SocketThreadListener {
    public static void main(String[] args) {
        new Server();
    }

    private Vector<User> allUser = new Vector<>();

    private Server() {
        try (ServerSocket serverSocket = new ServerSocket(4040)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new User(this, clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnect(User user) {
        System.out.println("Пользователь подключен");
        allUser.add(user);
    }

    @Override
    public void onReceiveMessage(String message) {
        System.out.println(message);

        for (User x : allUser)
            x.sendMessage(message);
    }

    @Override
    public void onDisconnect(User user) {
        System.out.println("Пользователь отключился");
        allUser.remove(user);
    }
}
