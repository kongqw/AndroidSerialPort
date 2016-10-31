package qingwei.kong.kqwserialportdemo;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends SerialPortActivity {

    private EditText mEditTextEmission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextEmission = (EditText) findViewById(R.id.editTextEmission);
    }

    public void send(View view) {
        String text = mEditTextEmission.getText().toString();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        Message message = Message.obtain();
        message.obj = text.getBytes();
        sendingHandler.sendMessage(message);
    }

    @Override
    protected void onDataReceived(final byte[] buffer, final int size) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mApplication, "收到消息：" + new String(buffer) + "  size = " + size, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
