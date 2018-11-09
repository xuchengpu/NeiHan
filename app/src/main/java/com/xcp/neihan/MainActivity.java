package com.xcp.neihan;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xcp.baselibrary.database.ISupportDao;
import com.xcp.baselibrary.database.SupportDaoFactory;
import com.xcp.baselibrary.dialog.AlertDialog;
import com.xcp.baselibrary.fix.FixManager;
import com.xcp.baselibrary.http.HttpUtils;
import com.xcp.baselibrary.http.OkHttpEngine;
import com.xcp.baselibrary.ioc.BindView;
import com.xcp.baselibrary.ioc.CheckNet;
import com.xcp.baselibrary.ioc.OnClick;
import com.xcp.baselibrary.utils.UIUtils;
import com.xcp.framelibrary.DefaultTitleBar;
import com.xcp.framelibrary.HttpCallBack;
import com.xcp.framelibrary.SkinBaseActivity;
import com.xcp.framelibrary.skin.SkinManager;
import com.xcp.neihan.bean.HomeBean;
import com.xcp.neihan.bean.Person;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MainActivity extends SkinBaseActivity {

    @BindView(R.id.tv_hello)
    TextView tvHello;
    @BindView(R.id.btn_change_skin)
    Button btnChangeSkin;
    @BindView(R.id.btn_another)
    Button btnAnother;
    @BindView(R.id.btn_default_skin)
    Button btnDefaultSkin;
    @BindView(R.id.iv_skin)
    ImageView ivSkin;
    private Drawable drawable;

    @Override
    protected void initListener() {

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
                Toast.makeText(MainActivity.this, contentView.getText().toString().trim() + "---哈哈哈", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void initData() {
        tvHello.setText("ioc注解");
        //插入数据
        ISupportDao<Person> dao = SupportDaoFactory.getInstance().getDao(Person.class);
//        List<Person> personList = new ArrayList<>();
//        for (int i = 0; i < 10000; i++) {
//            Person person = new Person("doudou", 28+i);
//            personList.add(person);
//        }
//        long pre = System.currentTimeMillis();
//        dao.insert(personList);
//        long end = System.currentTimeMillis();
//        long l = end - pre;
//        Toast.makeText(MainActivity.this, "插入成功，耗时："+l, Toast.LENGTH_SHORT).show();
        getDataFromNet();
//        Log.e("TAG", "删除前数据量为" + dao.query().queryAll().size());
//        dao.delete("age < ?", new String[]{6000 + ""});
//        Log.e("TAG", "删除后数据量为" + dao.query().queryAll().size());
//        List<Person> query = dao.query().selection("age = ?").selectionArgs(new String[]{"" + 7000}).query();
//        Log.e("TAG", "按条件查询数据量为" + query.size());
//        for (Person person : query) {
//            Log.e("TAG", "person==" + person.toString());
//        }
    }

    private void getDataFromNet() {
        HttpUtils.with(this)
//                .url(Constants.HOME_URL)
//                .url("http://is.snssdk.com/2/essay/discovery/v3/")
//                .get()
                .url("https://app.preova.com/api/app-user-login")
                .post()
                .addParams("mobile", "2NwhVvZTbiLupvY3lsZeBw==")
                .addParams("verify_code", "5424")
                .addParams("device_type", "1")
                .setmCache(true)
                .setEngine(new OkHttpEngine())
                .execute(new HttpCallBack<HomeBean>() {
                    @Override
                    public void onPrepare(Context context, Map<String, Object> params) {
                        super.onPrepare(context, params);
                        //此处可重写onPrepare方法，用来在这里放置显示进度条逻辑等
                    }

                    @Override
                    public void onDecorateSuccess(HomeBean resultBean) {
                        //此处可写隐藏进度条逻辑
                        Log.e("TAG", "HttpUtils请求成功了");
                        Log.e("TAG", "resultBean==" + resultBean);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.e("TAG", "HttpUtils请求失败了");
                    }

//                    @Override
//                    public void onSuccess(String response) {
//                        //此处可写隐藏进度条逻辑
//                        Log.e("TAG", "HttpUtils请求成功了");
//                    }


                });
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
        initToolbar();

//        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
//        Log.e("TAG", "maxMemory==" + maxMemory + "M");

    }

    private void initToolbar() {
        new DefaultTitleBar.Builder(this)
                .setTitle("内涵段子")
                .create();
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
                FixManager manager = new FixManager(this);
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

    @OnClick({R.id.btn_default_skin, R.id.btn_another, R.id.tv_hello, R.id.btn_change_skin})
    @CheckNet
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_hello:
                showMyDialog();
                break;
            case R.id.btn_change_skin:
                changeSkin();
                break;
            case R.id.btn_default_skin:
                break;
            case R.id.btn_another:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void changeSkin() {
        String path = getExternalFilesDir(null).getAbsolutePath() + File.separator + "blue.apk";
        SkinManager.getInstance().changeSkin(path);
    }
}
