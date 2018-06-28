package com.xcp.framelibrary;

import android.content.Context;

import com.google.gson.Gson;
import com.xcp.baselibrary.http.EngineCallBack;
import com.xcp.baselibrary.http.HttpUtils;

import java.util.Map;

/**
 * Created by 许成谱 on 2018/6/28 10:40.
 * qq:1550540124
 * 热爱生活每一天！
 */

public abstract class HttpCallBack<T> implements EngineCallBack {
    @Override
    public void onPrepare(Context context, Map<String, Object> params) {
        //在此处实现自己添加一些公共参数等功能
    }

    @Override
    public void onSuccess(String response) {
        T resultBean = (T) new Gson().fromJson(response, HttpUtils.analysisClazzInfo(this));
        onDecorateSuccess(resultBean);
    }

    /**
     * 修饰过后的成功回调，将字符串转化为bean对象
     * @param resultBean
     */
    public abstract void onDecorateSuccess(T resultBean);
}
