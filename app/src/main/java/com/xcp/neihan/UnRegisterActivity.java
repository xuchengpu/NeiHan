package com.xcp.neihan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class UnRegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_register);
        String message = getIntent().getStringExtra("message");
        Toast.makeText(UnRegisterActivity.this, "接收到的消息为："+message, Toast.LENGTH_SHORT).show();
    }
}
