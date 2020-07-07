import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class User extends Thread {

    private DataInputStream in;
    private DataOutputStream out;
    private SocketThreadListener listener;
    private Socket clientSocket;

    User(SocketThreadListener listener, Socket clientSocket) {
        this.listener = listener;
        this.clientSocket = clientSocket;

        try {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        try {
            listener.onConnect(this);
            while (!isInterrupted()) {
                String word = in.readUTF();
                listener.onReceiveMessage(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            listener.onDisconnect(this);
            interrupt();
        }
    }

    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
