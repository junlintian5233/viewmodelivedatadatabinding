package com.junzhitian.basicapplib.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.junzhitian.basicapplib.utils.ActivityUtil;
import com.junzhitian.basicapplib.widget.LoadingDialog;

/**
 * @作者:junzhitian
 * @时间:2020/1/19
 * @描述:不需要ViewModel的页面基类
 */
public abstract class BaseNoModelActivity<DB extends ViewDataBinding> extends AppCompatActivity {

    public    String              TAG = this.getClass().getSimpleName();
    public    BaseNoModelActivity mContext;
    protected DB                  mDataBinding;
    private   LoadingDialog       mLoadingdialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityUtil.getInstance().addActivity(this);
        int layoutId = onSetLayoutRes();
        setContentView(layoutId);

        mDataBinding = initDataBinding(layoutId);
        initView();
        initData();
    }

    /**
     * 初始化要加载的布局资源ID
     */
    protected abstract int onSetLayoutRes();


    /**
     * 初始化DataBinding
     */
    protected DB initDataBinding(@LayoutRes int layoutId) {
        return DataBindingUtil.setContentView(this, layoutId);
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 显示用户等待框
     *
     * @param msg 提示信息
     */
    protected void showDialog(String msg) {
        if (mLoadingdialog != null && mLoadingdialog.isShowing()) {
            mLoadingdialog.setMessage(msg);
        } else {
            mLoadingdialog = new LoadingDialog(mContext);
            mLoadingdialog.setMessage(msg);
            mLoadingdialog.show();
        }
    }

    /**
     * 隐藏等待框
     */
    protected void dismissDialog() {
        if (mLoadingdialog != null && mLoadingdialog.isShowing()) {
            mLoadingdialog.dismiss();
            mLoadingdialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDataBinding != null) {
            mDataBinding.unbind();
        }
        ActivityUtil.getInstance().removeActivity(this);
    }
}
