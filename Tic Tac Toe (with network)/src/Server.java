import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Vector;

class Server implements SocketThreadListener {
    public static void main(String[] args) {
        new Server();
    }

    private int field[][];
    private int readyPlayer = 0, side = 0, result = -1, amountStep = 0;

    private static Vector<SocketThread> listClient = new Vector<>();
    private Server() {
        field = new int[3][3];

        int num = 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                field[i][j] = num++;
        }

        try (ServerSocket serverSocket = new ServerSocket(4040)) {
            System.out.println("Server is run...");
            while (true) {
                Socket socket;
                try {
                    socket = serverSocket.accept();
                } catch (SocketTimeoutException e) {
                    continue;
                }
                new SocketThread(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkWinOrDraw() {
        if (field[0][0] == field[0][1] && field[0][1] == field[0][2]) {
            result = field[0][0];
            return true;
        }
        if (field[1][0] == field[1][1] && field[1][1] == field[1][2]) {
            result = field[1][0];
            return true;
        }
        if (field[2][0] == field[2][1] && field[2][1] == field[2][2]) {
            result = field[2][0];
            return true;
        }
        ///////////////////////
        if (field[0][0] == field[1][0] && field[1][0] == field[2][0]) {
            result = field[0][0];
            return true;
        }
        if (field[0][1] == field[1][1] && field[1][1] == field[2][1]) {
            result = field[0][1];
            return true;
        }
        if (field[0][2] == field[1][2] && field[1][2] == field[2][2]) {
            result = field[0][2];
            return true;
        }
        //////////////////////
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
            result = field[0][0];
            return true;
        }
        if (field[2][0] == field[1][1] && field[1][1] == field[0][2]) {
            result = field[2][0];
            return true;
        }

        if (++amountStep == 9) {
            result = 0;
            return true;
        }
        return false;
    }

    @Override
    public synchronized void onSocketThreadReady(SocketThread thread) {
        System.out.println("Пользователь подключен");
        listClient.add(thread);
    }

    @Override
    public synchronized void onReceiveString(SocketThread thread, String value) {
        if (value.split(" ", 4)[0].equals("c")) {
            int x = Integer.parseInt(value.split(" ", 4)[1]),
                y = Integer.parseInt(value.split(" ", 4)[2]);
            field[x][y] = Integer.parseInt(value.split(" ", 4)[3]);


            if (!checkWinOrDraw()) {
                for (SocketThread list : listClient)
                    list.sendMessage("c " + x + " " + y + " " + field[x][y]);
                return;
            } else {
                // 0 - Ничья; 1 - Победа крестика; 2 - Победа нолки
                for (SocketThread list : listClient) {
                    // list.sendMessage("end " + result);
                    list.sendMessage("end " + result + " " + x + " " + y + " " + field[x][y]);
                }
            }
        } else if (value.split(" ", 2)[0].equals("side")) {
            if (side != Integer.parseInt(value.split(" ", 2)[1])) {
                side = Integer.parseInt(value.split(" ", 2)[1]);

                thread.sendMessage("strue");
            } else {
                thread.sendMessage("sfalse");
            }
        } else if (value.equals("ready")) {
            if (++readyPlayer == 2) {
                for (SocketThread list : listClient)
                    list.sendMessage("Allready");
            }
        }
    }

    @Override
    public synchronized void onSocketThreadInterrupted(SocketThread thread) {
        System.out.println("Пользователь отключен");
        listClient.remove(thread);
    }
}
