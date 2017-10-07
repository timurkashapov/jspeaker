import java.io.IOException;
import java.net.Socket;

public class Client {

    private enum State {INIT, START, RUNNING, STOP, BUSY};

    private Socket connection;

    int port         = 1234;
    String localhost = "localhost";

    public Client() {

        new Thread(new Runnable() {
            public void run() {

                try {
                    connection = new Socket(localhost, port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
