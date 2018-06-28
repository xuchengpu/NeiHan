package com.xcp.baselibrary.http;

import android.content.Context;

import java.util.Map;

/**
 * Created by 许成谱 on 2018/6/27 11:57.
 * qq:1550540124
 * 热爱生活每一天！
 * for:网络请求的回调规范接口
 */

public interface EngineCallBack {
    /**
     * 请求前干的工作，比如添加公共的参数
     * @param context
     * @param params
     */
    void onPrepare(Context context, Map<String, Object> params);

    /**失败回调
     * @param e
     */
    void onFailure(Exception e);

    /**成功回调
     * @param response
     */
    void onSuccess(String response);
}
