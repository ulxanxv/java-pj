import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements SocketThreadListener {
    public static void main(String[] args) {
        new Client();
    }

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Client() {
        try (Socket clientSocket = new Socket("localhost", 4040)) {
            User user = new User(this, clientSocket);

            while (true) {
                user.sendMessage(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnect(User user) {
        System.out.println("Вы подключились!");
    }

    @Override
    public void onReceiveMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void onDisconnect(User user) {
        System.out.println("Вы отключились");
    }
}
