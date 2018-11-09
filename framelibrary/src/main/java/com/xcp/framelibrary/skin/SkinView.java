package com.xcp.framelibrary.skin;

import android.view.View;

import java.util.List;

/**
 * Created by 许成谱 on 2018/11/9 9:47.
 * qq:1550540124
 * 热爱生活每一天！
 * 封装需要换装的view与它对应的若干属性类
 */
public class SkinView {
    private View view;
    private List<SkinAttr> skinAttrs;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<SkinAttr> getSkinAttrs() {
        return skinAttrs;
    }

    public void setSkinAttrs(List<SkinAttr> skinAttrs) {
        this.skinAttrs = skinAttrs;
    }

    public SkinView(View view, List<SkinAttr> skinAttrs) {
        this.view = view;
        this.skinAttrs = skinAttrs;
    }
}
