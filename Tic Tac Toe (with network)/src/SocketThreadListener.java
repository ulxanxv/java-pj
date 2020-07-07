import java.net.Socket;

public interface SocketThreadListener {
    void onSocketThreadReady(SocketThread thread);
    void onReceiveString(SocketThread thread, String value);
    void onSocketThreadInterrupted(SocketThread thread);
}
