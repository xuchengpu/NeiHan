package com.xcp.framelibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import com.xcp.baselibrary.base.BaseActivity;
import com.xcp.framelibrary.skin.SkinAppCompatViewInflater;
import com.xcp.framelibrary.skin.SkinAttr;
import com.xcp.framelibrary.skin.SkinAttrSupport;
import com.xcp.framelibrary.skin.SkinManager;
import com.xcp.framelibrary.skin.SkinView;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

/**
 * Created by 许成谱 on 2018/5/29 10:20.
 * qq:1550540124
 * 热爱生活每一天！
 * 为以后换肤框架预留的基类
 */

public abstract class SkinBaseActivity  extends BaseActivity implements  LayoutInflaterFactory {
    private SkinAppCompatViewInflater mAppCompatViewInflater;
    private static final boolean IS_PRE_LOLLIPOP = Build.VERSION.SDK_INT < 21;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //1、更据源码分析可知，在此处设置factory可拦截view的创建
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        if (layoutInflater.getFactory() == null) {
            LayoutInflaterCompat.setFactory(layoutInflater,this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        //2、SkinBaseActivity实现了LayoutInflaterFactory，每次创建都会走这里
        //2.1创建view……拷贝系统创建view的源码，兼容低版本
        View view = createView(parent, name, context, attrs);
        //2.2解析属性 把含有设定类型的attr收集起来放进一个集合里，与对应的view绑定，封装成一个新的实例，再统一交给skinmanager去管理
        Log.e("TAG", "view=="+view);
        if(view!=null) {
            List<SkinAttr> skinAttrs = SkinAttrSupport.getSkinAttrs(context, attrs);
            if(skinAttrs!=null&&skinAttrs.size()>0) {
                SkinView skinView = new SkinView(view, skinAttrs);
                //3、统一交给SkinManager去管理
                SkinManager.getInstance().addView(skinView);
            }
        }
        return view;
    }

    @SuppressLint("RestrictedApi")
    private View createView(View parent, String name, Context context, AttributeSet attrs) {
        //从源码中拷贝，为了兼容低版本
        if (mAppCompatViewInflater == null) {
            mAppCompatViewInflater = new SkinAppCompatViewInflater();
        }

        boolean inheritContext = false;
        if (IS_PRE_LOLLIPOP) {
            inheritContext = (attrs instanceof XmlPullParser)
                    // If we have a XmlPullParser, we can detect where we are in the layout
                    ? ((XmlPullParser) attrs).getDepth() > 1
                    // Otherwise we have to use the old heuristic
                    : shouldInheritContext((ViewParent) parent);
        }

        return mAppCompatViewInflater.createView(parent, name, context, attrs, inheritContext,
                IS_PRE_LOLLIPOP, /* Only read android:theme pre-L (L+ handles this anyway) */
                true, /* Read read app:theme as a fallback at all times for legacy reasons */
                VectorEnabledTintResources.shouldBeUsed() /* Only tint wrap the context if enabled */
        );

    }

    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            // The initial parent is null so just return false
            return false;
        }
        final View windowDecor = getWindow().getDecorView();
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (parent == windowDecor || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }

}
