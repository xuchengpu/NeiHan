package com.xcp.neihan;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;
import com.xcp.baselibrary.CrashHandler;
import com.xcp.baselibrary.database.SupportDaoFactory;
import com.xcp.framelibrary.skin.SkinManager;

/**
 * Created by 许成谱 on 2018/5/29 18:48.
 * qq:1550540124
 * 热爱生活每一天！
 */

public class MyApplication extends Application {

    public PatchManager patchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);//初始化崩溃收集类
        SupportDaoFactory.getInstance().init(this);//初始化自定义数据库
        SkinManager.getInstance().init(this); //换肤
        /*try {
            HookHelper helper=new HookHelper(this,ProxyActivity.class);
            helper.hookStartActivity();//修改为代理类对应的安全intent绕过manifest.xml注册检查
            helper.hookLunchActivity();//改回目标intent,使可以启动外部的apk中的Activity
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //阿里热修复AndFix初始化
       /* patchManager = new PatchManager(this);

        try {
            patchManager.init(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);//current version
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        patchManager.loadPatch();*/
        //自己的热修复,初始化用于把以前的补丁包全部修复
        /*try {
            FixManager manager=new FixManager(this);
            manager.loadFixDexs();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "e"+e.getMessage());
        }*/

    }
}
