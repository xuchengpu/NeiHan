package com.xcp.baselibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.xcp.baselibrary.R;

/**
 * Created by 许成谱 on 2018/6/6 18:38.
 * qq:1550540124
 * 热爱生活每一天！
 * 自定义万能dialog，参照系统的AlertDialog源码去实现
 */

public class AlertDialog extends Dialog {
    //具体的实现类
    final AlertController mAlert;

    public AlertDialog(@NonNull Context context) {
        this(context, 0);

    }

    public AlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(getContext(), this, getWindow());
    }
    public void setText(int viewId, String text) {
        mAlert.setText(viewId, text);
    }
    public void setOnClickListener(int viewId, View.OnClickListener onClickListener){
        mAlert.setOnClickListener(viewId, onClickListener);
    }
    public <T extends View> T getViewById(int viewId){
        return  mAlert.getViewById(viewId);
    }

    /**
     * 建造者模式的实现
     */
    public static class Builder {
        //容器 用来存储事先设置的参数
        private final AlertController.AlertParams P;

        /**
         * Creates a builder for an alert dialog that uses the default alert
         * dialog theme.
         * <p>
         * The default alert dialog theme is defined by
         * {@link android.R.attr#alertDialogTheme} within the parent
         * {@code context}'s theme.
         *
         * @param context the parent context
         */
        public Builder(Context context) {
            this(context, R.style.dialog);
        }

        /**
         * Creates a builder for an alert dialog that uses an explicit theme
         * resource.
         * <p>
         * The specified theme resource ({@code themeResId}) is applied on top
         * of the parent {@code context}'s theme. It may be specified as a
         * style resource containing a fully-populated theme, such as
         * {@link android.R.style#Theme_Material_Dialog}, to replace all
         * attributes in the parent {@code context}'s theme including primary
         * and accent colors.
         * <p>
         * To preserve attributes such as primary and accent colors, the
         * {@code themeResId} may instead be specified as an overlay theme such
         * as {@link android.R.style#ThemeOverlay_Material_Dialog}. This will
         * override only the window attributes necessary to style the alert
         * window as a dialog.
         * <p>
         * Alternatively, the {@code themeResId} may be specified as {@code 0}
         * to use the parent {@code context}'s resolved value for
         * {@link android.R.attr#alertDialogTheme}.
         *
         * @param context    the parent context
         * @param themeResId the resource ID of the theme against which to inflate
         *                   this dialog, or {@code 0} to use the parent
         *                   {@code context}'s default alert dialog theme
         */
        public Builder(Context context, int themeResId) {
            P = new AlertController.AlertParams(
                    context, themeResId);
        }

        /**
         * 以资源id形式设置布局
         *
         * @param contentViewId
         * @return
         */
        public Builder setContentView(int contentViewId) {
            P.contentViewId = contentViewId;
            return this;
        }

        /**
         * 以view对象形式设置布局
         *
         * @param contentView
         * @return
         */
        public Builder setContentView(View contentView) {
            P.contentView = contentView;
            return this;
        }

        /**
         * 根据控件id来设置文字内容
         *
         * @param viewId
         * @param text
         * @return
         */
        public Builder setText(int viewId, String text) {
            P.texts.put(viewId, text);
            return this;
        }

        /**
         * 根据控件id来设置监听
         *
         * @param viewId
         * @param listener
         * @return
         */
        public Builder setOnClickListener(int viewId, View.OnClickListener listener) {
            P.listeners.put(viewId, listener);
            return this;
        }

        /**
         * Sets whether the dialog is cancelable or not.  Default is true.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        /**
         * Sets the callback that will be called if the dialog is canceled.
         * <p>
         * <p>Even in a cancelable dialog, the dialog may be dismissed for reasons other than
         * being canceled or one of the supplied choices being selected.
         * If you are interested in listening for all cases where the dialog is dismissed
         * and not just when it is canceled, see
         * {@link #setOnDismissListener(android.content.DialogInterface.OnDismissListener)
         * setOnDismissListener}.</p>
         *
         * @return This Builder object to allow for chaining of calls to set methods
         * @see #setCancelable(boolean)
         * @see #setOnDismissListener(android.content.DialogInterface.OnDismissListener)
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        /**
         * Sets the callback that will be called when the dialog is dismissed for any reason.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        /**
         * Sets the callback that will be called if a key is dispatched to the dialog.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }

        /**
         * Creates an {@link AlertDialog} with the arguments supplied to this
         * builder.
         * <p>
         * Calling this method does not display the dialog. If no additional
         * processing is needed, {@link #show()} may be called instead to both
         * create and display the dialog.
         */
        public AlertDialog create() {
            // We can't use Dialog's 3-arg constructor with the createThemeContextWrapper param,
            // so we always have to re-set the theme
            final AlertDialog dialog = new AlertDialog(P.mContext, P.mThemeResId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        /**
         * Creates an {@link AlertDialog} with the arguments supplied to this
         * builder and immediately displays the dialog.
         * <p>
         * Calling this method is functionally identical to:
         * <pre>
         *     AlertDialog dialog = builder.create();
         *     dialog.show();
         * </pre>
         */
        public AlertDialog show() {
            final AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }

        /**
         * 设置全宽
         *
         * @return
         */
        public Builder setFullWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 设置底部弹出动画
         *
         * @return
         */
        public Builder setFromBottomAnimation() {
            P.mAnimation = R.style.dialog_from_bottom_anim;
            return this;
        }

        /**
         * 设置gravity等属性
         * @param gravity
         * @return
         */
        public Builder setGravity(int gravity) {
            P.mGravity = gravity;
            return this;
        }

        /**
         * 设置动画效果
         * @param animation
         * @return
         */
        public Builder setAnimation(int animation) {
            P.mAnimation = animation;
            return this;
        }

        /**
         * 设置布局宽高等参数
         * @param width
         * @param height
         * @return
         */
        public Builder setLayoutParams(int width,int height) {
            P.mWidth = width;
            P.mHeight=height;
            return this;
        }
    }

}
