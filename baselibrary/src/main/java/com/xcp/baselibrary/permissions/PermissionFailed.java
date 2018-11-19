package com.xcp.baselibrary.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 许成谱 on 2018/11/19 14:06.
 * qq:1550540124
 * 热爱生活每一天！
 * 权限申请被拒绝时要执行方法的标记
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionFailed {
    int requestCode();
}
