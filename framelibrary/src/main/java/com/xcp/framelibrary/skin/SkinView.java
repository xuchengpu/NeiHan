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
    private List<SkinParam> skinParams;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<SkinParam> getSkinParams() {
        return skinParams;
    }

    public void setParams(List<SkinParam> skinParams) {
        this.skinParams = skinParams;
    }

    public SkinView(View view, List<SkinParam> skinParams) {
        this.view = view;
        this.skinParams = skinParams;
    }
}
