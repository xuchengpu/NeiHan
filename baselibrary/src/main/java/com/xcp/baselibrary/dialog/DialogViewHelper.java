package com.xcp.baselibrary.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by 许成谱 on 2018/6/7 10:22.
 * qq:1550540124
 * 热爱生活每一天！
 * dialog 视图帮助类，用于设置内容、点击事件等
 */

class DialogViewHelper {
    private final Context mContext;
    private final View mContentView;
    private SparseArray<WeakReference<View>> views = new SparseArray<>();//使用若引用，防止内存泄露


    public DialogViewHelper(Context mContext, View contentView) {
        this.mContext = mContext;
        this.mContentView = contentView;
    }

    public DialogViewHelper(Context mContext, int contentViewId) {
        this.mContext = mContext;
        this.mContentView = LayoutInflater.from(mContext).inflate(contentViewId, null);
    }

    /**
     * 设置点击事件
     * @param viewId
     * @param onClickListener
     */
    public void setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        View view = getViewById(viewId);
        view.setOnClickListener(onClickListener);
    }

    <T extends View> T getViewById(int viewId) {
        View view = null;
        WeakReference<View> weakReference = views.get(viewId);
        if (weakReference != null) {
            view = weakReference.get();
        } else {
            view = mContentView.findViewById(viewId);
            views.put(viewId, new WeakReference<View>(view));
        }
        return (T) view;

    }

    /**
     * 设置文本
     * @param viewId
     * @param text
     */
    public void setText(int viewId, String text) {
        TextView view = getViewById(viewId);
        if (view != null) {
            view.setText(text);
        }
    }

    /**
     * @return dialog的布局view
     */
    public View getContentView() {
        return mContentView;
    }
}
