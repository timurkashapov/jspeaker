import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {

    private enum State {INIT, START, RUNNING, STOP, BUSY, ON_LUNCH};

    private IHandler handler;

    private ServerSocket connection;
    private ExecutorService pool;

    private static int numberOfCpuCores;

    private ArrayList<Object> connections;

    private InputStream  in;
    private OutputStream out;

    int port    = 1234;
    String ip   = "127.0.0.1";

    static {
        Properties properties = System.getProperties();
        numberOfCpuCores = Runtime.getRuntime().availableProcessors();
    }

    public Server() {
        pool = Executors.newFixedThreadPool(numberOfCpuCores);
//        pool.submit();
    }

    @Override
    public void run() {
        super.run();

        while (true) {

            try {

                connection = new ServerSocket(port);
                connection.accept();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                pool.shutdown();
                try { connection.close(); } catch (IOException e) { e.printStackTrace(); }
            }
        }
    }



    public static void main(String[] args) {
        new Server().start();
    }
}
