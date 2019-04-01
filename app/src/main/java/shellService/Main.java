package shellService;

import android.os.Looper;

import shellService.util.ShellUtil;

public class Main {
    public static void main(String[] args) {
        Looper.prepareMainLooper();
        System.out.println("*****************hack server start****************");
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SocketService(new SocketService.SocketListener() {
                    @Override
                    public String onMessage(String msg) {
                        return resolveMsg(msg);
                    }
                }, 10255);
            }
        }).start();
        Looper.loop();
    }

    private static String resolveMsg(String msg) {
        switch (msg) {
            case "are you ok?":
                return "i'm ok";
            case "uninstall":
                ShellUtil.ExecResult execResult =
                        ShellUtil.execute("pm uninstall com.bindo.paymentapp");
                return execResult.getMessage();
            default:
                return "!@#$%^&*()";
        }
    }
}
