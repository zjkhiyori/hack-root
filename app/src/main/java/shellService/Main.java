package shellService;

import android.os.Looper;

public class Main {
    public static void main(String[] args) {
        Looper.prepareMainLooper();
        System.out.println("-----------shell server running-------------");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------start a thread---------");
                new SocketService(new SocketService.SocketListener() {
                    @Override
                    public String onMessage(String msg) {
                        if (msg.equals("are you ok?")) {
                            return "l'm ok";
                        }
                        return "!@#$%^&*()";
                    }
                }, 10255);
            }
        }).start();
        Looper.loop();
    }
}
