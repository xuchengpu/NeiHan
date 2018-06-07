package com.xcp.baselibrary.utils;

import android.content.Context;

/**
 * Created by 许成谱 on 2018/6/7 14:56.
 * qq:1550540124
 * 热爱生活每一天！
 * 与屏幕分辨率相关的工具类
 */

public class UIUtils {

    public static int dp2px(Context context,int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);

    }

    public static int px2dp(Context context,int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }
}
