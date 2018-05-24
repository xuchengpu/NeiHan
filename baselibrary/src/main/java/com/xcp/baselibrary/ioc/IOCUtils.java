package com.xcp.baselibrary.ioc;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.xcp.baselibrary.NetUtis;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 许成谱 on 2018/5/24 17:13.
 * qq:1550540124
 * 热爱生活每一天！
 * 注解工具
 * 使用方式：
 *
 *  @BindView(R.id.tv_hello)
    TextView tvHello;

    IOCUtils.inject(this);

     @OnClick({R.id.tv_hello})
     @CheckNet
     private void onClick(View view)

 */

public class IOCUtils {

    /**
     * 反射注入初始化Activity的类
     *
     * @param activity
     */
    public static void inject(Activity activity) {
        inject(new Finder(activity), activity);

    }

    /**
     * 反射注入初始化View的类
     *
     * @param view
     */
    public static void inject(View view) {
        inject(new Finder(view), view);
    }

    /**
     * 供内部调用，相当于兼容方法
     *
     * @param finder
     * @param object
     */
    private static void inject(Finder finder, Object object) {
        //初始化类属性变量
        injectFields(finder, object);
        //初始化类方法
        injectMethods(finder, object);
    }

    private static void injectMethods(Finder finder, Object object) {
        //获取类里面所有的方法
        Class<?> objClass = object.getClass();
        Method[] methods = objClass.getDeclaredMethods();
        //遍历一遍，找出每一个有注解的成员方法
        for (Method method : methods) {
            OnClick onClick = method.getAnnotation(OnClick.class);
            CheckNet checkNet = method.getAnnotation(CheckNet.class);
            //判断有没有声明注解 没有声明注解时表明是普通的成员方法，不用进入反射初始化操作
            if (onClick != null) {
                for (int value : onClick.value()) {
                    View view = finder.findViewById(value);
                    view.setOnClickListener(new DeClearedListener(method, object, checkNet));
                }
            }
        }

    }

    private static void injectFields(Finder finder, Object object) {
        //获取类里面所有的属性
        Class<?> objClass = object.getClass();
        Field[] fields = objClass.getDeclaredFields();
        //遍历一遍，找出每一个有注解的成员变量
        for (Field field : fields) {
            BindView bindView = field.getAnnotation(BindView.class);
            //判断有没有声明注解 没有声明注解时表明是普通的成员，不用进入反射初始化操作
            if (bindView != null) {
                View view = finder.findViewById(bindView.value());
                try {
                    //表明也可对私有的成员变量进行注入
                    field.setAccessible(true);
                    //利用反射类提供的方法 把view赋值给field指向的成员变量
                    field.set(object, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    static class DeClearedListener implements View.OnClickListener {

        private CheckNet checkNet;
        private Method method;
        private Object object;

        public DeClearedListener(Method method, Object object, CheckNet checkNet) {
            this.method = method;
            this.object = object;
            this.checkNet = checkNet;
        }

        @Override
        public void onClick(View v) {
            if (checkNet != null) {
                if (!NetUtis.checkNetState(v.getContext())) {
                    Toast.makeText(v.getContext(), "网络连接不可用", Toast.LENGTH_SHORT).show();
                    return;//网络不可用时方法不再向下执行
                }
            }
            try {
                //表明也可对私有的成员变量进行注入
                method.setAccessible(true);
                //利用反射类提供的方法 把view赋值给field指向的成员变量
                method.invoke(object, v);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }


    }
}
