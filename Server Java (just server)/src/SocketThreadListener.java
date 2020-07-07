import java.net.Socket;

public interface SocketThreadListener {
    void onSocketThreadStart(SocketThread thread, Socket socket);
    void onSocketThreadStop(SocketThread thread);
    void onSocketThreadReady(SocketThread thread);
    void onReceiveString(SocketThread thread, String value);
    void onSocketThreadException(SocketThread thread, Exception e);
}
