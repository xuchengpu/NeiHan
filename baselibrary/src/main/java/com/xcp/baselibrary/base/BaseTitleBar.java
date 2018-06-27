package com.xcp.baselibrary.base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 许成谱 on 2018/6/19 15:59.
 * qq:1550540124
 * 热爱生活每一天！
 * 写一个抽象类，把基本的东西、共同的东西都放进去，方便以后扩展。规范出builder设计模式，方便项目中使用。
 */

public abstract class BaseTitleBar<E extends BaseTitleBar.Params> implements ITitleBar {
    //使用泛型，由实现传递过来类型，规避编译错误检查，这样子类中可直接使用在自己类中定义的BaseTitleBar.Params的子类的类变量
    public E params;
    public View titleBarView;

    public BaseTitleBar(E params) {
        this.params = params;
        createAndBindView();
    }

    private void createAndBindView() {

        int rootId = bindViewId();

        if (params.parent == null) {
            ViewGroup decorView = (ViewGroup) ((Activity) params.mContext).getWindow().getDecorView();
            params.parent = (ViewGroup) decorView.getChildAt(0);
        }
        //此处为考虑国内厂商定制了手机系统，可能该布局不存在
        if (params.parent == null) {
            return;
        }
        titleBarView = LayoutInflater.from(params.mContext).inflate(rootId, params.parent, false);
        params.parent.addView(titleBarView, 0);//添加到第一个位置
        apply();
    }

    protected void setText(int viewId, String text) {
        TextView textView = fbi(viewId);
        if (textView != null) {
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
        }
    }

    protected void setOnClickListener(int viewID, View.OnClickListener listener) {
        fbi(viewID).setOnClickListener(listener);
    }

    private <T extends View> T fbi(int viewId) {

        return titleBarView.findViewById(viewId);
    }


    /**
     * 把公共的params提取出来，方便BaseTitleBar的构造器利用里面的参数创建view，最大程度把代码写进BaseTitleBar，达到提取封装的目的
     */
    public static class Params {

        public Context mContext;
        public ViewGroup parent;

        public Params(Context context, ViewGroup parent) {
            this.mContext = context;
            this.parent = parent;
        }
    }
}
