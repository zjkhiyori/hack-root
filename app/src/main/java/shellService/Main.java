
package shellService;

import android.os.Looper;

import shellService.util.ShellUtil;

public class Main {
    public static void main(String[] args) {
        Looper.prepareMainLooper();
        System.out.println("*****************hack server starting****************");
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SocketService(new SocketService.SocketListener() {
                    @Override
                    public String onMessage(String msg) {
                        return resolveMsg(msg);
                    }
                });
            }
        }).start();
        Looper.loop();
    }

    private static String resolveMsg(String msg) {
        ShellUtil.ExecResult execResult =
                ShellUtil.execute("pm uninstall " + msg);
        return execResult.getMessage();
    }
}
