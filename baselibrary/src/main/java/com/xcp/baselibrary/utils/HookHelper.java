package com.xcp.baselibrary.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by 许成谱 on 2018/12/4 15:31.
 * qq:1550540124
 * 热爱生活每一天！
 * 通过动态代理方式hook启动外部apk中的Activity,基于sdk26,版本不同成员变量名称会有所差异
 */
public class HookHelper {
    private static final String EXTRA_ORIGIN_INTENT ="extra_origin_intent";
    private final Class mProxyClass;//代理Activity，没什么具体作用，只是帮助我们目标Activity绕过manifest.xml检测。
    private final Context mContext;

    public HookHelper(Context context, Class proxyClass){
        this.mContext=context.getApplicationContext();
        this.mProxyClass=proxyClass;
    }

    /**原理：1、
     * 在系统检测manifest中是否注册前执行
     * 目标：通过动态代理使系统一准备执行ActivityManager.getService().startActivity()方法时就进入我们设定的代理InvocationHandler中的invoke()方法中，我们在这里可以做一些比如替换intent的操作，来暂时绕过系统检查
     * @throws Exception
     */
    public  void hookStartActivity() throws Exception {

        //1、获取真正执行启动Activity核心方法的实例android.util.Singleton.mInstance
        //获取ActivityManager里面的IActivityManagerSingleton（真正执行启动Activity的核心执行者）
        Field singletonField = ActivityManager.class.getDeclaredField("IActivityManagerSingleton");
        singletonField.setAccessible(true);
        //获取值
        Object singleton = singletonField.get(null);//它是静态的，所以它的参数可以传null
        //获取single中的mInstance
        Class<?> singletonClass = Class.forName("android.util.Singleton");
        Field mInstanceField = singletonClass.getDeclaredField("mInstance");
        mInstanceField.setAccessible(true);
        Object mInstance = mInstanceField.get(singleton);

        //2、动态代理,注意这个模式必须基于接口才能执行
        Object newInstance =Proxy.newProxyInstance(HookHelper.class.getClassLoader(),
                new Class[]{Class.forName("android.app.IActivityManager")},
                new startActivityInvocationHandler(mInstance));

        //3、设置给android.util.Singleton
        mInstanceField.set(singleton,newInstance);

    }

    private  class startActivityInvocationHandler implements InvocationHandler {
        private  Object mObject;//方法的执行者，被代理者

        public startActivityInvocationHandler(Object object) {
            this.mObject=object;
        }

        /**
         * 通过这样一代理，使的所有要执行startactivity、 startService、getActivityOptions等方法都会走这里
         * @param proxy
         * @param method
         * @param args
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 替换Intent,过AndroidManifest.xml检测
//            Log.e("InvocationHandler", method.getName());
            if(method.getName().equals("startActivity")) {
                //在这里做一些替换intent的操作

//            .startActivity(whoThread, who.getBasePackageName(), intent,
//                    intent.resolveTypeIfNeeded(who.getContentResolver()),
//                    token, target != null ? target.mEmbeddedID : null,
//                    requestCode, 0, null, options);
                //可知第二个参数是intent
                Intent originIntent  = (Intent) args[2];//原来的intent
                Intent safeIntent=new Intent(mContext,mProxyClass);//new 一个新的安全的intent
                safeIntent.putExtra(EXTRA_ORIGIN_INTENT,originIntent);//把原有的intent绑定到安全的intent上，方便后期绕过系统检测后再取出来跳转到真正的目标Activity中去
                args[2]=safeIntent;//替换
            }
            return method.invoke(mObject,args);
        }
    }

    /**原理：2、
     * 在已经通过Activity的manifest检测后执行，此时需要将目标Activity恢复过来 即在switch 中执行handleLaunchActivity（）前，要把record中的intent替换掉
     * 这个方法里没有用动态代理，因为根据源码，可以给handler设置一个回调，它就没每次判断后自动执行回调了，没必要用动态代理
     * @throws Exception
     */
    public void hookLunchActivity() throws Exception {
        //1、拿到当前的活动启动线程，实际上它也只有一个
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Field currentActivityThreadFiled = activityThreadClass.getDeclaredField("sCurrentActivityThread");
        currentActivityThreadFiled.setAccessible(true);
        Object currentActivityThread = currentActivityThreadFiled.get(null);
        //2、拿到当前线程中的mh,每次启动Activity时就是通过它来发消息进行的 见scheduleLaunchActivity()
        Field mHField = activityThreadClass.getDeclaredField("mH");
        mHField.setAccessible(true);
        Object mH = mHField.get(currentActivityThread);
        //3、给mh设置一个回调，使他每次执行的时候都能收到通知，以便执行替换intnet这一动作
        Class<?> handlerClass = Class.forName("android.os.Handler");
        Field callbackField = handlerClass.getDeclaredField("mCallback");
        callbackField.setAccessible(true);
        callbackField.set(mH,new HookHandlerCallback());
    }

    private class HookHandlerCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            // 每发一个消息都会走一次这个CallBack发放
            //阅读源码可知，我们在启动Activity的时候才需要拦截处理，即msg.what==100时
            if(msg.what==100) {
                handleLunchActivity(msg);
            }
            return false;
        }

        /**
         * 真正执行把intent改回来的方法
         * @param msg
         */
        private void handleLunchActivity(Message msg)  {
            //阅读scheduleLaunchActivity()方法可知，msg中封装了intent
            Object record = msg.obj;
            try {
                //拿到我们设置的安全的intent
                Field safeIntentField = record.getClass().getDeclaredField("intent");
                safeIntentField.setAccessible(true);
                Intent safeIntent = (Intent) safeIntentField.get(record);
                //从中拿到我们原本的intent
                Intent originIntent=(Intent)safeIntent.getParcelableExtra(EXTRA_ORIGIN_INTENT);
                if(originIntent!=null) {
                    //重新设置回去
                    safeIntentField.set(record,originIntent);
                }

                // 兼容目标Activity继承AppCompatActivity报错问题
                // 原理：从报错信息上看，是在android.support.v4.app.NavUtils.getParentActivityName(android.app.Activity)中抛出找不到名字的异常，经分析是它又走了一次从packagemManager 中获取信息的过程，相当于又
                //做了一次校验。因此我们需要先以反射方式拿到packageManager对象，以hook方式动态代理packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)方法的执行，把其中的componentName替换掉即可。
                //1、拿到当前的活动启动线程，实际上它也只有一个
                Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
                Field currentActivityThreadFiled = activityThreadClass.getDeclaredField("sCurrentActivityThread");
                currentActivityThreadFiled.setAccessible(true);
                Object currentActivityThread = currentActivityThreadFiled.get(null);
                //2、拿到sPackageManager对象
                Field sPackageManagerField = activityThreadClass.getDeclaredField("sPackageManager");
                sPackageManagerField.setAccessible(true);
                Object sPackageManager = sPackageManagerField.get(currentActivityThread);
                //3、动态代理sPackageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)方法的执行
                Object proxy=Proxy.newProxyInstance(HookHelper.class.getClassLoader(),
                new Class[]{Class.forName("android.content.pm.IPackageManager")},
                        new sPackageManagerHandler(sPackageManager));
                //4、把代理设置给currentActivityThread，代替原有的sPackageManager，使它每次执行getActivityInfo方法时都能被加工一下
                sPackageManagerField.set(currentActivityThread,proxy);



//                Class<?> forName = Class.forName("android.app.ActivityThread");
//                Field field = forName.getDeclaredField("sCurrentActivityThread");
//                field.setAccessible(true);
//                Object activityThread = field.get(null);
//                // 我自己执行一次那么就会创建PackageManager，系统再获取的时候就是下面的iPackageManager
//                Method getPackageManager = activityThread.getClass().getDeclaredMethod("getPackageManager");
//                Object iPackageManager = getPackageManager.invoke(activityThread);
//
//                PackageManagerHandler handler = new PackageManagerHandler(iPackageManager);
//                Class<?> iPackageManagerIntercept = Class.forName("android.content.pm.IPackageManager");
//                Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
//                        new Class<?>[]{iPackageManagerIntercept}, handler);
//
//                // 获取 sPackageManager 属性
//                Field iPackageManagerField = activityThread.getClass().getDeclaredField("sPackageManager");
//                iPackageManagerField.setAccessible(true);
//                iPackageManagerField.set(activityThread, proxy);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        class sPackageManagerHandler implements InvocationHandler{
            private  Object target;

            public sPackageManagerHandler(Object target) {
                this.target=target;
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().startsWith("getActivityInfo")) {
                    ComponentName componentName=new ComponentName(mContext,mProxyClass);
                    args[0]=componentName;
                }
                return method.invoke(target,args);
            }
        }
    }
}
