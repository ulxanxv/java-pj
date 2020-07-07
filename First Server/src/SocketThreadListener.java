public interface SocketThreadListener {

    void onConnect(User user);
    void onReceiveMessage(String message);
    void onDisconnect(User user);
}
