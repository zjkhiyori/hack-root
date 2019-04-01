package club.syachiku.hackroot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import android.util.Log;

public class SocketClient {
    private final String TAG = "HackRoot SocketClient";
    private SocketListener listener;
    private PrintWriter printWriter;

    public SocketClient(final String cmd, SocketListener listener) {
        this.listener = listener;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = new Socket();
                try {
                    socket.connect(new InetSocketAddress("127.0.0.1", 10255), 3000);
                    socket.setSoTimeout(3000);
                    printWriter = new PrintWriter(socket.getOutputStream(), true);
                    Log.d(TAG, "client send: " + cmd);
                    printWriter.println(cmd);
                    printWriter.flush();
                    readServerData(socket);
                } catch (IOException e) {
                    Log.d(TAG, "client send fail: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void readServerData(final Socket socket) {
        try {
            InputStreamReader ipsReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bfReader = new BufferedReader(ipsReader);
            String line = null;
            while ((line = bfReader.readLine()) != null) {
                Log.d(TAG, "client receive: " + line);
                listener.onMessage(line);
            }
            ipsReader.close();
            bfReader.close();
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    interface SocketListener {
        void onMessage(String msg);
    }
}
