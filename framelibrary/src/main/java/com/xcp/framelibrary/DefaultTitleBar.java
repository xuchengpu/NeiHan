package com.xcp.framelibrary;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.xcp.baselibrary.base.BaseTitleBar;

/**
 * Created by 许成谱 on 2018/6/20 18:55.
 * qq:1550540124
 * 热爱生活每一天！
 * 具体的标题实现类，传入参数到params中保存，由父类拿着这个params中的参数去进行具体的创建标题栏view及应用我们传入的配置
 * 使用方式：
 * DefaultTitleBar navigationBar = new DefaultTitleBar.Builder(this,(ViewGroup) findViewById(R.id.rl_titlebar))
 * .create();
 * 扩展方式：
 * 1、更换bindViewId（）方法中的布局
 * 2、更加链式调用的原理，直接在builder中添加相应的方法，然后对应调整DefaultTitleBar.params类中的方法
 * 3、在apply()方法中取出params中的新增参数，到BaseTitleBar中根据titleBarView去调整
 */

public class DefaultTitleBar extends BaseTitleBar<DefaultTitleBar.Builder.Params> {

    public DefaultTitleBar(Builder.Params params) {
        super(params);
    }

    @Override
    public int bindViewId() {
        return R.layout.title_bar;
    }

    /**
     * 此处为扩展的东西添加进来的地方
     */
    @Override
    public void apply() {
        //设置标题
        if (!TextUtils.isEmpty(params.mTitle)) {
            setText(R.id.title, params.mTitle);
        }
        //设置监听
        SparseArray<View.OnClickListener> listeners = params.listeners;
        for (int i = 0; i < listeners.size(); i++) {
            setOnClickListener(listeners.keyAt(i), listeners.valueAt(i));
        }
        //设置文本
        SparseArray<String> contents = params.contents;
        for (int i = 0; i < contents.size(); i++) {
            setText(contents.keyAt(i), contents.valueAt(i));
        }
        //自动添加返回键点击事件
        setOnClickListener(R.id.back, new BackOnClickListener());
    }

    // 仿照系统写的 有三个要素 DefaultTitleBar 、Builder 、Params
    //Builder起规范代码作用，Params起容器容纳参数作用，DefaultTitleBar起具体执行操作作用
    public static class Builder {
        Params p;

        public Builder(Context context, ViewGroup parent) {
            p = new Params(context, parent);
        }

        public Builder(Context context) {
            p = new Params(context, null);
        }

        /**
         * 这一步是最终调用的步骤，真正实现先前传的参数等效果
         *
         * @return
         */
        public DefaultTitleBar create() {
            return new DefaultTitleBar(p);
        }

        /**
         * 设置标题
         *
         * @param title
         * @return
         */
        public DefaultTitleBar.Builder setTitle(String title) {
            p.mTitle = title;
            return this;
        }

        public DefaultTitleBar.Builder setViewOnClickListener(int viewID, View.OnClickListener listener) {
            p.listeners.put(viewID, listener);
            return this;
        }

        class Params extends BaseTitleBar.Params {

            public String mTitle;
            private SparseArray<View.OnClickListener> listeners;
            private SparseArray<String> contents;

            public Params(Context context, ViewGroup parent) {
                super(context, parent);
                listeners = new SparseArray<>();
                contents = new SparseArray<>();
            }
        }
    }

    private class BackOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //关闭当前Activity
            ((Activity) params.mContext).finish();
        }
    }
}
