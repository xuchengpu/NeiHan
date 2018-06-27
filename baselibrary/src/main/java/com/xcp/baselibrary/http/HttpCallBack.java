package com.xcp.baselibrary.http;

/**
 * Created by 许成谱 on 2018/6/27 11:57.
 * qq:1550540124
 * 热爱生活每一天！
 * for:网络请求的回调规范接口
 */

public interface HttpCallBack {
    void onFailure(Exception e);

    void onSuccess(String response);
}
