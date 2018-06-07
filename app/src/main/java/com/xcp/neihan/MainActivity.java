package com.xcp.neihan;

import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xcp.baselibrary.dialog.AlertDialog;
import com.xcp.baselibrary.fix.FixManager;
import com.xcp.baselibrary.ioc.BindView;
import com.xcp.baselibrary.utils.UIUtils;
import com.xcp.framelibrary.SkinBaseActivity;

import java.io.File;
import java.io.IOException;

public class MainActivity extends SkinBaseActivity {

    @BindView(R.id.tv_hello)
    TextView tvHello;

    @Override
    protected void initListener() {
        tvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showMyDialog();
            }
        });
    }

    private void showMyDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setContentView(R.layout.detail_comment_dialog)
                .setText(R.id.submit_btn, "接收")
                .setOnClickListener(R.id.account_icon_weibo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击了星朗微博", Toast.LENGTH_SHORT).show();
                    }
                })
//                .setFullWidth()
                .setLayoutParams(UIUtils.dp2px(getApplicationContext(), 300), ViewGroup.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.BOTTOM)
//                .setFromBottomAnimation()
                .show();
        final EditText contentView = dialog.getViewById(R.id.comment_editor);
        dialog.setOnClickListener(R.id.submit_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, contentView.getText().toString().trim()+"---哈哈哈", Toast.LENGTH_SHORT).show();
            }
        });


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

//        andFix();

//        customFix();


    }

    /**
     * 自定义热修复
     */
    private void customFix() {
        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = getExternalFilesDir(null).getAbsolutePath();
        } else {
            path = getFilesDir().getAbsolutePath();
        }
        File fixFile = new File(path, "fix.dex");

        if (fixFile.exists()) {

            try {
                FixManager manager=new FixManager(this);
                manager.fixBug(fixFile.getAbsolutePath());
                Toast.makeText(MainActivity.this, "修复成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "修复失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 阿里andFix热修复
     */
    private void andFix() {
        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = getExternalFilesDir(null).getAbsolutePath();
        } else {
            path = getFilesDir().getAbsolutePath();
        }
        File file = new File(path, "fix.apatch");
        if (file.exists()) {
            try {
                ((MyApplication) getApplication()).patchManager.addPatch(file.getAbsolutePath());
                Toast.makeText(MainActivity.this, "修复成功", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "修复失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

//    @OnClick({R.id.tv_hello})
//    @CheckNet
//    private void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_hello:
//                Toast.makeText(MainActivity.this, "注解点击事件生效", Toast.LENGTH_SHORT).show();
//                break;
//        }
//
//    }
}
