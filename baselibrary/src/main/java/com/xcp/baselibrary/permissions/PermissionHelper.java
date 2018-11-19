package com.xcp.baselibrary.permissions;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by 许成谱 on 2018/11/19 11:23.
 * qq:1550540124
 * 热爱生活每一天！
 * 申请权限的帮助类
 */
public class PermissionHelper {
    private Object object;//activity或者fragment
    private String[] permisssions;
    private int requestCode;

    public PermissionHelper(Object object) {
        this.object = object;
    }

    public static void requestPermissions(Activity activity, String[] permisssions, int requestCode) {
        PermissionHelper.with(activity).permissions(permisssions).requestCode(requestCode).request();
    }

    public static void requestPermissions(Fragment fragment, String[] permisssions, int requestCode) {
        PermissionHelper.with(fragment).permissions(permisssions).requestCode(requestCode).request();
    }

    /**
     * 在调用with时进行初始化，实现链式调用
     *
     * @param activity
     * @return
     */
    public static PermissionHelper with(Activity activity) {
        return new PermissionHelper(activity);
    }

    public static PermissionHelper with(Fragment fragment) {
        return new PermissionHelper(fragment);
    }

    public PermissionHelper permissions(String[] permisssions) {
        this.permisssions = permisssions;
        return this;
    }

    public PermissionHelper requestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    /**
     * 真正执行权限申请的方法
     */
    public void request() {
        if (Build.VERSION.SDK_INT >= 23) {
            //6.0以上就去动态申请
            //拿到权限里面尚未获得授权的权限
            List<String> deniedPermissions = PermissionUtils.getDeniedPermissions(object, permisssions);
            if (deniedPermissions.size() == 0) {//说明此时所申请的权限都被同意了
                PermissionUtils.executeResultMethod(object, requestCode, PermissionSucceed.class);
            } else {
                //申请权限
                ActivityCompat.requestPermissions(PermissionUtils.getActivity(object), deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            }
        } else {
            //6.0以下就直接执行申请成功将要执行的目标方法
            PermissionUtils.executeResultMethod(object, requestCode, PermissionSucceed.class);
        }
    }

    /**
     * 处理权限申请的回调
     *
     * @param activity
     * @param requestCode
     * @param permissions
     */
    public static void onRequestResult(Activity activity, int requestCode, String[] permissions) {
        List<String> deniedPermissions = PermissionUtils.getDeniedPermissions(activity, permissions);
        if (deniedPermissions.size() == 0) {
            PermissionUtils.executeResultMethod(activity, requestCode, PermissionSucceed.class);
        } else {
            PermissionUtils.executeResultMethod(activity, requestCode, PermissionFailed.class);
        }
    }
}
