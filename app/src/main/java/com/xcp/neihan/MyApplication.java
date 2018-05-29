package com.xcp.neihan;

import android.app.Application;

import com.xcp.baselibrary.CrashHandler;

/**
 * Created by 许成谱 on 2018/5/29 18:48.
 * qq:1550540124
 * 热爱生活每一天！
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);//初始化崩溃收集类

    }
}
