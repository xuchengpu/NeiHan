package com.xcp.framelibrary.skin;

import android.view.View;

/**
 * Created by 许成谱 on 2018/11/13 12:01.
 * qq:1550540124
 * 热爱生活每一天！
 * 为第三方控件提供的皮肤改变通知回调,调用者可在此判断是否是对应的第三方控件，从而做出相应的改变
 */
public interface ISkinChangeListener {
    void onSkinChange(SkinResource resources,View view);
}
