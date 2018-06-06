package com.xcp.baselibrary.dialog;

import android.content.Context;

/**
 * Created by 许成谱 on 2018/6/6 18:41.
 * qq:1550540124
 * 热爱生活每一天！
 */

class AlertController {

    static class AlertParams {

        private final Context mContext;
        private final int mThemeResId;

        public AlertParams(Context context, int themeResId) {
            this.mContext=context;
            this.mThemeResId=themeResId;
        }
    }
}
