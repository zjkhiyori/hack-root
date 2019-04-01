package shellService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketService {
    private SocketListener listener;


    public SocketService(SocketListener listener, int PORT) {
        this.listener = listener;
        try {
            System.out.println("----------ready start service-----------");
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("server running " + PORT + " port");
            ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                    5,
                    10,
                    5000,
                    TimeUnit.MILLISECONDS,
                    queue
                    );
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("accept request");
                executor.execute(new processMsg(socket));
            }
        } catch (Exception e) {
            System.out.println("SocketServer create Exception:" + e);
        }
    }

    class processMsg implements Runnable {
        Socket socket;

        public processMsg(Socket s) {
            socket = s;
        }

        public void run() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = bufferedReader.readLine();
                System.out.println("server receive: " + line);
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                String repeat = listener.onMessage(line);
                System.out.println("server send: " + repeat);
                printWriter.print(repeat);
                printWriter.flush();
                printWriter.close();
                bufferedReader.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("socket 连接线程发生错误：" + e.toString());
            }
        }
    }

    public interface SocketListener{
        String onMessage(String text);
    }
}
