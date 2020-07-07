import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class SocketThread extends Thread {

    private SocketThreadListener listener;
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    SocketThread(SocketThreadListener listener, Socket clientSocket) {
        this.listener = listener;
        this.clientSocket = clientSocket;

        start();
    }

    @Override
    public void run() {
        try {
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());

            listener.onSocketThreadReady(this);
            while (!isInterrupted()) {
                String word = in.readUTF();
                listener.onReceiveString(this, word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
