package com.xcp.framelibrary.skin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Method;

/**
 * Created by 许成谱 on 2018/11/9 14:51.
 * qq:1550540124
 * 热爱生活每一天！
 * 资源操作相关的封装类。
 */
public class SkinResource {
    private Resources mResources;
    private String packageName;//皮肤apk的包名

    public SkinResource(Context context, String path) {
        AssetManager mAssets = null;
        try {
            mAssets = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            //设置资源文件路径
            method.invoke(mAssets, path);
            //核心方法
            mResources = new Resources(mAssets, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
            // 获取skinPath包名
            packageName = context.getPackageManager().getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES).packageName;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ColorStateList getColorByName(String resName) {
        try {
            int drawableId = mResources.getIdentifier(resName, "color", packageName);
            return mResources.getColorStateList(drawableId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Drawable getDrawableByName(String resName) {
        try {
            int drawableId = mResources.getIdentifier(resName, "drawable", packageName);
            return mResources.getDrawable(drawableId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
