package com.xcp.neihan.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by 许成谱 on 2018/12/7 19:15.
 * qq:1550540124
 * 热爱生活每一天！
 */
public class ApkUtil {
    /**
     * 安装APP
     *
     * @param context 上下文
     * @param filePath 文件路径
     */
    public static void installApp(Context context, String filePath) {

        File apkFile=new File(filePath );

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {//判读版本是否在7.0以上
            String authority="com.xcp.neihan.fileprovider";
            Uri apkUri = FileProvider.getUriForFile(context, authority, apkFile);
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            context.startActivity(install);
        } else{
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(install);
        }

    }

}
