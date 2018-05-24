package com.xcp.neihan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xcp.baselibrary.ioc.BindView;
import com.xcp.baselibrary.ioc.CheckNet;
import com.xcp.baselibrary.ioc.IOCUtils;
import com.xcp.baselibrary.ioc.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_hello)
    TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IOCUtils.inject(this);
        tvHello.setText("ioc注解");
    }

    @OnClick({R.id.tv_hello})
    @CheckNet
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_hello:
                Toast.makeText(MainActivity.this, "注解点击事件生效", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
