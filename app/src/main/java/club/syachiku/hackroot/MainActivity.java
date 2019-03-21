package club.syachiku.hackroot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnProcess = findViewById(R.id.btn_process);
        final EditText editText = findViewById(R.id.cmd_input);
        textView = findViewById(R.id.tv_output);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SocketClient(editText.getText().toString(), new SocketClient.SocketListener() {
                    @Override
                    public void onMessage(String msg) {
                        showOnTextView(msg);
                    }
                });
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
            }
        });
    }
}
