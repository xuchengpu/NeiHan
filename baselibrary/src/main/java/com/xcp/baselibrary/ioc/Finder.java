package com.xcp.baselibrary.ioc;

import android.app.Activity;
import android.view.View;

/**
 * Created by 许成谱 on 2018/5/24 17:18.
 * qq:1550540124
 * 热爱生活每一天！
 * findviewById帮助类
 */

public class Finder {

    private View view;
    private Activity activity;

    public Finder(Activity activity) {
        this.activity = activity;
    }

    public Finder(View view) {
        this.view = view;
    }

    public View findViewById(int id) {
        return activity != null ? activity.findViewById(id) : view.findViewById(id);
    }
}
