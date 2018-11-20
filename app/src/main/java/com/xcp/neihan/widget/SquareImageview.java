package com.xcp.neihan.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by 许成谱 on 2018/11/20 16:30.
 * qq:1550540124
 * 热爱生活每一天！
 */
public class SquareImageview extends android.support.v7.widget.AppCompatImageView {
    public SquareImageview(Context context) {
        this(context, null);
    }

    public SquareImageview(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareImageview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, width);
    }
}
