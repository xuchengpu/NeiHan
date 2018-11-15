package com.xcp.framelibrary.skin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 许成谱 on 2018/11/9 11:42.
 * qq:1550540124
 * 热爱生活每一天！
 * 皮肤管理类，设置成单例，全局唯一。用于保存所有需要换肤的view。
 */
public class SkinManager {
    private Map<ISkinChangeListener, List<SkinView>> views;
    private Context mContext;
    private SkinResource resource;

    private SkinManager() {
        views = new HashMap<>();
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();
        //查找是否存在皮肤文件
        String skinPath = SkinUtils.getSkinPath(mContext);
        File file = new File(skinPath);
        if (!file.exists()) {
            //皮肤文件不存在就把sp文件置空
            SkinUtils.clearSkinInfo(mContext);
            return;
        }
        //皮肤文件存在 校验包名
        String packageName = mContext.getPackageManager().getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES).packageName;
        if (TextUtils.isEmpty(packageName)) {
            SkinUtils.clearSkinInfo(mContext);
            return;
        }
        //皮肤文件存在 校验签名

        //初始化资源
        resource = new SkinResource(mContext, skinPath);
    }

    public int changeSkin(String path) {
        if (path == null) {
            return SkinConfig.SKIN_RES_NOT_EXIST;
        }
        //查找是否存在皮肤文件
        File file = new File(path);
        if (!file.exists()) {
            return SkinConfig.SKIN_RES_NOT_EXIST;
        }
        //路径是否一致,一致就返回，没必要重复执行代码
        String skinPath = SkinUtils.getSkinPath(mContext);
        if (path.equals(skinPath)) {
            return SkinConfig.SKIN_CHANGE_NOTHING;
        }
        //皮肤文件存在 校验包名
        String packageName = mContext.getPackageManager().getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES).packageName;
        if (TextUtils.isEmpty(packageName)) {
            SkinUtils.clearSkinInfo(mContext);
            return SkinConfig.SKIN_FILE_ERROR;
        }
        //皮肤文件存在 校验签名


        //资源初始化
        resource = new SkinResource(mContext, path);
        //更换皮肤
        executeChange();
        //保存皮肤的状态
        saveSkinStatus(path);

        return SkinConfig.SKIN_CHANGE_SUCCESS;
    }

    private void saveSkinStatus(String path) {
        //用sp存储而不用第三方数据库，就是为了避免嵌套，解耦
        SkinUtils.saveSkinPath(mContext, path);
    }

    private void executeChange() {
        //遍历map,循环取出view，一个一个设置
        Iterator<Map.Entry<ISkinChangeListener, List<SkinView>>> entries = views.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<ISkinChangeListener, List<SkinView>> entry = entries.next();
            for (SkinView skinView : entry.getValue()) {
                change(entry.getKey(),skinView);
            }
        }
    }

    public void checkSkinView(ISkinChangeListener listener,SkinView skinView) {
        //通过判断是否存在皮肤文件路径，来决定是否显示皮肤
        String skinPath = SkinUtils.getSkinPath(mContext);
        if (!TextUtils.isEmpty(skinPath)) {
            change(listener,skinView);
        }
    }

    private void change(ISkinChangeListener listener,SkinView skinView) {
        List<SkinParam> skinParams = skinView.getSkinParams();
        for (int j = 0; j < skinParams.size(); j++) {
            //更换皮肤
            skinParams.get(j).setSkin(skinView.getView());
        }
        listener.onSkinChange(resource,skinView.getView());//回调通知Activity
    }

    /**
     * 恢复默认皮肤
     *
     * @return
     */
    public int reStoreSkin() {
        // 判断当前有没有皮肤，没有皮肤就不要执行任何方法
        String skinPath = SkinUtils.getSkinPath(mContext);
        if (TextUtils.isEmpty(skinPath)) {
            return SkinConfig.SKIN_CHANGE_NOTHING;
        }
        //重置资源，再次遍历改变对应属性
        resource = new SkinResource(mContext, mContext.getPackageResourcePath());
        executeChange();
        //更新sp存储中的路径信息
        SkinUtils.clearSkinInfo(mContext);
        return SkinConfig.SKIN_CHANGE_SUCCESS;
    }

    public void remove(ISkinChangeListener listener) {
        views.remove(listener);
    }


    private static class Holder {
        private static final SkinManager manager = new SkinManager();
    }

    public static SkinManager getInstance() {
        return Holder.manager;
    }

    public void addView(ISkinChangeListener listener, SkinView view) {
        List<SkinView> skinViews = views.get(listener);
        if (skinViews == null) {
            skinViews = new ArrayList<>();
            views.put(listener, skinViews);
        }
        skinViews.add(view);

    }

    public SkinResource getSkinResource() {
        return resource;
    }
}
