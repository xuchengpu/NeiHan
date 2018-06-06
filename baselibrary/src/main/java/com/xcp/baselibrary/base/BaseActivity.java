package com.xcp.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xcp.baselibrary.ioc.IOCUtils;

/**
 * Created by 许成谱 on 2018/5/29 10:08.
 * qq:1550540124
 * 热爱生活每一天！
 * 公共基类，把公共的代码都提取到这里。模板设计模式。
 */

public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( getLayoutId());
        IOCUtils.inject(this);

        initView();

        initData();

        initListener();
    }

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * @return 布局layout文件
     */
    public abstract int getLayoutId() ;
}
