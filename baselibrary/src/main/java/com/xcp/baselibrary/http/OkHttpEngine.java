package com.xcp.baselibrary.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.xcp.baselibrary.utils.CacheUtils;
import com.xcp.baselibrary.utils.MD5Encoder;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 许成谱 on 2018/6/27 14:34.
 * qq:1550540124
 * 热爱生活每一天！
 */

public class OkHttpEngine implements IHttpEngine {
    private OkHttpClient mClient;
    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    public OkHttpEngine() {
        //初始化及设置超时等
        mClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                        cookieStore.put(httpUrl.host(), list);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public void get(Context context, String url, Map<String, Object> params, final EngineCallBack callBack, final boolean cache) {

        final String mFinalUrl = HttpUtils.jointParams(url, params);
        Log.e("TAG","Get请求路径："+ url);
        if (cache) {
            String cacheResonse = CacheUtils.getCache(mFinalUrl);
            if (!TextUtils.isEmpty(cacheResonse)) {
                Log.e("TAG", "读取缓存成功");
                callBack.onSuccess(cacheResonse);
            }
        }

        Request request = new Request.Builder()
                .url(mFinalUrl)
                .tag(context)
//               .addHeader("Cookie", "xxx")//可添加Cookie，User-Agent什么的 但在创建OkHttpClient时需要设置管理Cookie的CookieJar：
//                .cacheControl()
                .build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //response的body有很多种输出方法，string()只是其中之一，注意是string()不是toString()。如果是下载文件就是response.body().bytes()。
                //另外可以根据response.code()获取返回的状态码。
                String resultJson = response.body().string();
                if (cache) {
                    String cacheResonse = CacheUtils.getCache(mFinalUrl);
                    if (!TextUtils.isEmpty(cacheResonse)) {
                        //如果缓存与请求结果一致，则不再刷新显示，如果不一致，则先刷新显示，再刷新数据库
                        if (cacheResonse.equals(resultJson)) {
                            Log.e("TAG", "数据和缓存结果一致");
                            return;
                        }
                    }
                    CacheUtils.updataCache(new CacheBean(MD5Encoder.encode(mFinalUrl), resultJson));
                }
                callBack.onSuccess(resultJson);
                Log.e("TAG：", "Get返回结果:"+resultJson);
            }
        });

    }

    @Override
    public void post(Context context, String url, Map<String, Object> params, final EngineCallBack callBack, final boolean cache) {

        StringBuilder sb=new StringBuilder();
        sb.append(url);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sb.append(entry.getKey());
            sb.append(entry.getValue());
        }
        final String key = url + sb.toString();
        if (cache) {
            String cacheResonse = CacheUtils.getCache(key);
            if (!TextUtils.isEmpty(cacheResonse)) {
                Log.e("TAG", "读取缓存成功");
                callBack.onSuccess(cacheResonse);
            }
        }

        RequestBody requestBody = appendBody(params);
        //post与get区别仅仅是需要把params包装一下，然后通过post(requestBody)方法传递进request而已
        Request request = new Request.Builder()
                .url(url)
                .tag(context)
//               .addHeader("Cookie", "xxx")//可添加Cookie，User-Agent什么的 但在创建OkHttpClient时需要设置管理Cookie的CookieJar，在我们封装的这个类中，公共参数更倾向于在HttpCallBack的onPrepare中添加到params,避免在OkHttpEngine（底层）掺入业务，实现解耦
//                .cacheControl()
                .post(requestBody)
                .build();

        mClient.newCall(request).enqueue(new Callback() {//enqueue表示是在异步中执行，回调等都是在异步
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //response的body有很多种输出方法，string()只是其中之一，注意是string()不是toString()。如果是下载文件就是response.body().bytes()。
                //另外可以根据response.code()获取返回的状态码。
                String resultJson = response.body().string();

                if (cache) {
                    String cacheResonse = CacheUtils.getCache(key);
                    if (!TextUtils.isEmpty(cacheResonse)) {
                        //如果缓存与请求结果一致，则不再刷新显示，如果不一致，则先刷新显示，再刷新数据库
                        if (cacheResonse.equals(resultJson)) {
                            Log.e("TAG", "数据和缓存结果一致");
                            return;
                        }
                    }
                    CacheUtils.updataCache(new CacheBean(MD5Encoder.encode(key), resultJson));
                }

                callBack.onSuccess(resultJson);
                Log.e("TAG", "post返回结果："+resultJson);
            }
        });

    }

    /**
     * 组装post请求参数body
     */
    protected RequestBody appendBody(Map<String, Object> params) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        addParams(builder, params);
        return builder.build();
    }

    // 添加参数，此处已做判断，可以是字符串，可以是文件
    private void addParams(MultipartBody.Builder builder, Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, params.get(key) + "");
                Object value = params.get(key);
                if (value instanceof File) {
                    // 处理文件 --> Object File
                    File file = (File) value;
                    builder.addFormDataPart(key, file.getName(), RequestBody
                            .create(MediaType.parse(guessMimeType(file
                                    .getAbsolutePath())), file));
                } else if (value instanceof List) {
                    // 代表提交的是 List集合
                    try {
                        List<File> listFiles = (List<File>) value;
                        for (int i = 0; i < listFiles.size(); i++) {
                            // 获取文件
                            File file = listFiles.get(i);
                            builder.addFormDataPart(key + i, file.getName(), RequestBody
                                    .create(MediaType.parse(guessMimeType(file
                                            .getAbsolutePath())), file));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    builder.addFormDataPart(key, value + "");
                }
            }
        }
    }

    /**
     * 猜测文件类型
     */
    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}
