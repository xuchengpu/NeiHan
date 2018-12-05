package com.xcp.neihan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * 注意此处暂时只能继承Activity，回头再解决继承AppCompatActivity
 *
 */
public class UnRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_register);
        String message = getIntent().getStringExtra("message");
        Toast.makeText(UnRegisterActivity.this, "接收到的消息为："+message, Toast.LENGTH_SHORT).show();
    }
}
