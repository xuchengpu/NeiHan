package com.xcp.neihan;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xcp.baselibrary.ioc.BindView;
import com.xcp.baselibrary.ioc.CheckNet;
import com.xcp.baselibrary.ioc.OnClick;
import com.xcp.framelibrary.SkinBaseActivity;

public class MainActivity extends SkinBaseActivity {

    @BindView(R.id.tv_hello)
    TextView tvHello;


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        tvHello.setText("ioc注解");
    }

    @Override
    protected void initView() {
        // 获取上次的崩溃信息上传到服务器
        //File crashFile = ExceptionCrashHandler.getInstance().getCrashFile();

        //if (crashFile.exists()) {
        // 上传到服务器, 后面再讲，只是做一个演示。 Bug  发布新的版本供用户去下载    11.10    11.11  阿里热修复的解决方案


        //}

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
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
