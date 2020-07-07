import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements SocketThreadListener {
    public static void main(String[] args) {
        new Client();
    }

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private SocketThread socketThread;
    private Client() {
        try {
            Socket socket = new Socket("localhost", 4040);
            socketThread = new SocketThread(this, socket);

            while (true) {
                socketThread.sendMessage(reader.readLine());
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

    }

    @Override
    public void onReceiveString(SocketThread thread, String value) {
        System.out.println(value);
    }

    @Override
    public void onSocketThreadException(SocketThread thread, Exception e) {

    }
}
