package com.junzhitian.basicapplib.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.junzhitian.basicapplib.R;
import com.junzhitian.basicapplib.utils.DisplayUtils;

import java.util.Objects;

/**
 * @作者:junzhitian
 * @时间:2020/1/19
 * @描述:加载等待弹出框
 */
public class LoadingDialog extends Dialog {

    private Context  context;
    private TextView tvContent;

    public LoadingDialog(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
        init();
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
        init();
    }

    private void init() {
        setCanceledOnTouchOutside(false);
        //加载自己定义的布局
        View      view    = LayoutInflater.from(context).inflate(R.layout.layout_loading, null);
        ImageView ivImage = view.findViewById(R.id.iv_image);
        tvContent = view.findViewById(R.id.tv_content);
        //加载XML文件中定义的动画
        Drawable drawable = ivImage.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            animationDrawable.start();
        }
        setContentView(view);
        //获取对话框当前的参数值
        WindowManager.LayoutParams layoutParams = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            layoutParams = Objects.requireNonNull(getWindow()).getAttributes();
            //获取屏幕宽高
            layoutParams.width = DisplayUtils.dip2px(getContext(), 120);
            layoutParams.height = DisplayUtils.dip2px(getContext(), 120);
            getWindow().setAttributes(layoutParams);
        }
    }

    public void setMessage(String msg) {
        if (null != tvContent) {
            tvContent.setText(msg);
        }
    }
}
