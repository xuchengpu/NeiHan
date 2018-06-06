package com.xcp.baselibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 许成谱 on 2018/5/24 18:41.
 * qq:1550540124
 * 热爱生活每一天！
 * 网络相关的工具类
 */

public class NetUtis {
    /**
     * 检查网络状态
     *
     * @param context
     * @return
     */
    public static boolean checkNetState(Context context) {
        try {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
