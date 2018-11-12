package com.xcp.framelibrary.skin;

import android.view.View;

/**
 * Created by 许成谱 on 2018/11/12 17:36.
 * qq:1550540124
 * 热爱生活每一天！
 * 封装资源名称与资源类别的类
 */
public class SkinParam {
    private String resName;
    private SkinAttr attr;

    public SkinParam(String resName, SkinAttr attr) {
        this.resName = resName;
        this.attr = attr;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public SkinAttr getAttr() {
        return attr;
    }

    public void setAttr(SkinAttr attr) {
        this.attr = attr;
    }
    public void setSkin(View view){
        attr.setSkin(view,resName);
    }
}
