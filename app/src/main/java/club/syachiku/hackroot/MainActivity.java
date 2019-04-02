package club.syachiku.hackroot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import shellService.util.ShellUtil;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ScrollView scrollView;
    private EditText uninsTxtInput;
    private Button btnUnins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnUnins = findViewById(R.id.btn_uninstall);
        uninsTxtInput = findViewById(R.id.pkg_input);
        textView = findViewById(R.id.tv_output);
        scrollView = findViewById(R.id.text_container);
        btnUnins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(uninsTxtInput.getText().toString());
            }
        });
    }

    private void sendMessage(String msg) {
        new SocketClient(msg, new SocketClient.SocketListener() {
            @Override
            public void onMessage(String msg) {
                showOnTextView(msg);
            }
        });
    }

    private void showOnTextView(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String baseText = textView.getText().toString();
                if (baseText != null) {
                    textView.setText(baseText + "\n" + msg);
                } else {
                    textView.setText(msg);
                }
                scrollView.smoothScrollTo(0, scrollView.getHeight());
            }
        });
    }
}
