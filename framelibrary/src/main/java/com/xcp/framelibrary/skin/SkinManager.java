package com.xcp.framelibrary.skin;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 许成谱 on 2018/11/9 11:42.
 * qq:1550540124
 * 热爱生活每一天！
 * 皮肤管理类，设置成单例，全局唯一。用于保存所有需要换肤的view。
 */
public class SkinManager {
    private List<SkinView> views;
    private Context mContext;
    private SkinResource resource;

    private SkinManager() {
        views = new ArrayList<>();
    }

    public void changeSkin(String path) {
        resource = new SkinResource(mContext,path);
        for(int i = 0; i < views.size(); i++) {
            View view = views.get(i).getView();
            List<SkinAttr> skinAttrs = views.get(i).getSkinAttrs();
            for (int j = 0; j < skinAttrs.size(); j++) {
                skinAttrs.get(j).setSkin(view);
            }
        }
    }

    private static class Holder {
        private static final SkinManager manager = new SkinManager();
    }

    public static SkinManager getInstance() {
        return Holder.manager;
    }

    public void addView(SkinView view) {
        views.add(view);
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();
    }
    public SkinResource getSkinResource(){
        return resource;
    }
}
