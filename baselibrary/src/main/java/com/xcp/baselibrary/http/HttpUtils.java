package com.xcp.baselibrary.http;

import android.content.Context;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 许成谱 on 2018/6/27 14:16.
 * qq:1550540124
 * 热爱生活每一天！
 * for:网络请求工具类，链式调用(注意此处并非builder设计模式)
 */

public class HttpUtils {

    private Context mContext;
    private String mUrl;//请求url
    private Map<String, Object> mParams;//请求参数
    //请求类型
    private final static int TYPE_GET = 0x0011;
    private final static int TYPE_POST = 0x0022;
    private int requestType = TYPE_GET;//默认get请求
    private IHttpEngine httpEngine;//网络请求引擎

    private HttpUtils(Context context) {
        this.mContext = context;
        mParams = new HashMap<>();
        httpEngine = new OkHttpEngine();//默认OkHttp网络请求框架;
    }

    public static HttpUtils with(Context context) {
        return new HttpUtils(context);
    }

    public HttpUtils setEngine(IHttpEngine engine) {
        httpEngine = engine;
        return this;
    }

    public HttpUtils url(String url) {
        mUrl = url;
        return this;
    }

    public HttpUtils params(Map<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public HttpUtils addParams(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public HttpUtils get() {
        requestType = TYPE_GET;
        return this;
    }

    public HttpUtils post() {
        requestType = TYPE_POST;
        return this;
    }

    public void execute(EngineCallBack callBack) {
        if (callBack == null) {
            callBack = new DefaultCallBack();//如果用户没有添加，默认添加一个
        }
        callBack.onPrepare(mContext, mParams);//网络请求执行前的回调，用于添加一些公共参数、通知显示进度条等操作
        if (requestType == TYPE_GET) {
            httpEngine.get(mContext, mUrl, mParams, callBack);
        }
        if (requestType == TYPE_POST) {
            httpEngine.post(mContext, mUrl, mParams, callBack);
        }

    }

    public void execute() {
        execute(null);
    }

    public static class DefaultCallBack implements EngineCallBack {
        @Override
        public void onPrepare(Context context, Map<String, Object> params) {

        }

        @Override
        public void onFailure(Exception e) {

        }

        @Override
        public void onSuccess(String response) {

        }
    }

    /**
     * 拼接参数，此方法其他引擎可能也会用到，故放在此
     */
    public static String jointParams(String url, Map<String, Object> params) {
        if (params == null || params.size() <= 0) {
            return url;
        }

        StringBuffer stringBuffer = new StringBuffer(url);
        if (!url.contains("?")) {
            stringBuffer.append("?");
        } else {
            if (!url.endsWith("?")) {
                stringBuffer.append("&");
            }
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "&");
        }

        stringBuffer.deleteCharAt(stringBuffer.length() - 1);

        return stringBuffer.toString();
    }

    /**
     * 解析一个类上面的class信息
     */
    public static Class<?> analysisClazzInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }
}
