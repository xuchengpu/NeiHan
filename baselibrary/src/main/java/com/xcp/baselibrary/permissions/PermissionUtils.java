package com.xcp.baselibrary.permissions;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 许成谱 on 2018/11/19 13:40.
 * qq:1550540124
 * 热爱生活每一天！
 * 申请权限的工具类
 */
public class PermissionUtils {

    private PermissionUtils() {
        throw new UnsupportedOperationException();//不支持实例化，因为本类中所有方法都是静态方法
    }


    /**
     * 执行通过反射用注解标记过的方法
     *
     * @param object
     * @param requestCode
     */
    public static void executeResultMethod(Object object, int requestCode, Class permissionAnnotation) {

        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            Annotation annotation = method.getAnnotation(permissionAnnotation);
            if (annotation != null) {
                //因为请求的地方可能不止一个，因此校对请求码
                try {
                    int code;
                    if (annotation instanceof PermissionSucceed) {
                        code = ((PermissionSucceed) annotation).requestCode();
                    } else if (annotation instanceof PermissionFailed) {
                        code = ((PermissionFailed) annotation).requestCode();
                    } else {
                        throw new PermissionAnotationException("注解类型只能为PermissionSucceed或者PermissionFailed中的一个");
                    }
                    if (code == requestCode) {
                        method.setAccessible(true);
                        method.invoke(object, null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Activity getActivity(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        }
        return null;
    }

    /**
     * 获取尚未被授权的权限
     * @param object
     * @param permisssions
     * @return
     */
    public static List<String> getDeniedPermissions(Object object, String[] permisssions) {
        List<String> deniedPermissions = new ArrayList();
        for (String permisssion : permisssions) {
            if (ContextCompat.checkSelfPermission(getActivity(object), permisssion) == android.content.pm.PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permisssion);
            }
        }
        return deniedPermissions;
    }
}
