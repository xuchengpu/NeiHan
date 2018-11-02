package com.xcp.baselibrary.http;

/**
 * Created by 许成谱 on 2018/11/2 16:13.
 * qq:1550540124
 * 热爱生活每一天！
 */
public class CacheBean {
    private String mUrlKey;
    private String mJsonResponse;

    public CacheBean() {
    }

    public CacheBean(String mUrlKey, String mJsonResponse) {
        this.mUrlKey = mUrlKey;
        this.mJsonResponse = mJsonResponse;
    }

    public String getmUrlKey() {
        return mUrlKey;
    }

    public void setmUrlKey(String mUrlKey) {
        this.mUrlKey = mUrlKey;
    }

    public String getmJsonResponse() {
        return mJsonResponse;
    }

    public void setmJsonResponse(String mJsonResponse) {
        this.mJsonResponse = mJsonResponse;
    }

    @Override
    public String toString() {
        return "CacheBean{" +
                "mUrlKey='" + mUrlKey + '\'' +
                ", mJsonResponse='" + mJsonResponse + '\'' +
                '}';
    }
}
