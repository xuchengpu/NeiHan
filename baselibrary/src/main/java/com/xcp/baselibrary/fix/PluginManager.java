package com.xcp.baselibrary.fix;

import android.content.Context;

/**
 * Created by 许成谱 on 2018/12/5 18:29.
 * qq:1550540124
 * 热爱生活每一天！
 */
public class PluginManager {
    public static final void install(Context context, String apkPath) {
        try {
            FixManager fixManager = new FixManager(context);
            fixManager.fixBug(apkPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
