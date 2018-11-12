package com.xcp.framelibrary.skin;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 许成谱 on 2018/11/9 10:06.
 * qq:1550540124
 * 热爱生活每一天！
 * 皮肤换装对应的属性。因为我们设定只有这三种类型，故用枚举比较合适。给枚举实例设置两个属性，用于实例类型判断和资源寻找。
 * 类型：
 * 资源名称：
 */
public enum SkinAttr {
    BACKGROUND("background") {
        @Override
        public void setSkin(View view,String resName) {
            SkinResource resouces = getResouces();
            ColorStateList color = resouces.getColorByName(resName);
            //可能是颜色
            if (color != null) {
                view.setBackgroundColor(color.getDefaultColor());
            }
            //可能是drawable
            Drawable drawable = resouces.getDrawableByName(resName);
            if (drawable != null) {
                view.setBackground(drawable);
            }

        }
    }, TEXTCOLOR("textColor") {
        @Override
        public void setSkin(View view,String resName) {
            SkinResource resouces = getResouces();
            ColorStateList color = resouces.getColorByName(resName);
            if (color != null) {
                ((TextView) view).setTextColor(color.getDefaultColor());
            }
        }
    }, SRC("src") {
        @Override
        public void setSkin(View view,String resName) {
            SkinResource resouces = getResouces();
            Drawable drawable = resouces.getDrawableByName(resName);
            if (drawable != null) {
                ((ImageView) view).setImageDrawable(drawable);
            }
        }
    };

    SkinAttr() {
    }

    SkinAttr(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrType() {
        return attrType;
    }

    private String attrType;

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public abstract void setSkin(View view,String resName);

    public SkinResource getResouces() {
        return SkinManager.getInstance().getSkinResource();
    }
}
