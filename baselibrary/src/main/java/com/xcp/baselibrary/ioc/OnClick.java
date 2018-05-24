package com.xcp.baselibrary.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 许成谱 on 2018/5/24 17:25.
 * qq:1550540124
 * 热爱生活每一天！
 * 绑定view的注解
 *
 * @Retention(RetentionPolicy.RUNTIME) 运行时注解
 * @Target(ElementType.METHOD) 作用在方法上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OnClick {
    int[] value();
}
