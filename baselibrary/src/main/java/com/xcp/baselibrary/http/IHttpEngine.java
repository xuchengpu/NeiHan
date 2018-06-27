package com.xcp.baselibrary.http;

import android.content.Context;

import java.util.Map;

/**
 * Created by 许成谱 on 2018/6/27 11:54.
 * qq:1550540124
 * 热爱生活每一天！
 * for:网络请求规范接口
 */

public interface IHttpEngine {
    //get请求
    void get(Context context, String url, Map<String, Object> params, HttpCallBack callBack);

    //post请求
    void post(Context context, String url, Map<String, Object> params, HttpCallBack callBack);
    //上传文件

    //下载文件

    //https添加证书
}
