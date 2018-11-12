package com.xcp.framelibrary.skin;

import android.content.Context;

/**
 * Created by 许成谱 on 2018/11/12 15:22.
 * qq:1550540124
 * 热爱生活每一天！
 */
public class SkinUtils {
    public static void saveSkinPath(Context context,String path){
        context.getSharedPreferences(SkinConfig.SKIN_RES_NAME,Context.MODE_PRIVATE).edit().putString(SkinConfig.SKIN_RES_PATH,path).commit();
    }
    public static String getSkinPath(Context context){
        return context.getSharedPreferences(SkinConfig.SKIN_RES_NAME,Context.MODE_PRIVATE).getString(SkinConfig.SKIN_RES_PATH,"");
    }
    public static void clearSkinInfo(Context context){
        saveSkinPath(context,"");
    }
}
