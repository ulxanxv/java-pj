import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Vector;

class Server implements SocketThreadListener {
    public static void main(String[] args) {
        new Server();
    }

    private static Vector<SocketThread> listClient = new Vector<>();
    private Server() {
        try (ServerSocket serverSocket = new ServerSocket(4040)) {
            System.out.println("Server is run...");
            while (true) {
                Socket socket;
                try {
                    System.out.println("1");
                    socket = serverSocket.accept();

                    System.out.println("2");
                } catch (SocketTimeoutException e) {
                    continue;
                }

                new SocketThread(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSocketThreadStart(SocketThread thread, Socket socket) {

    }

    @Override
    public void onSocketThreadStop(SocketThread thread) {

    }

    @Override
    public void onSocketThreadReady(SocketThread thread) {
        System.out.println("Пользователь подключен");
        listClient.add(thread);
    }

    @Override
    public void onReceiveString(SocketThread thread, String value) {
        System.out.println(value);

        for (SocketThread client : listClient) {
            client.sendMessage(value);
        }
    }

    @Override
    public void onSocketThreadException(SocketThread thread, Exception e) {

    }
}
