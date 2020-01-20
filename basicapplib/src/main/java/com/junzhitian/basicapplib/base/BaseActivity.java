package com.junzhitian.basicapplib.base;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.junzhitian.basicapplib.bean.DialogInfo;
import com.junzhitian.basicapplib.lifecycle.BaseViewModel;

/**
 * @作者:junzhitian
 * @时间:2020/1/19
 * @描述:ViewModel、ViewDataBinding都需要的基类
 */
public abstract class BaseActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends BaseNoModelActivity<DB> {

    protected VM mViewModel;

    @Override
    protected DB initDataBinding(int layoutId) {
        DB db = super.initDataBinding(layoutId);
        mViewModel = initViewModel();
        initObserve();
        return db;
    }

    /**
     * 初始化ViewModel
     */
    protected abstract VM initViewModel();

    /**
     * 监听当前ViewModel中 showDialog和error的值
     */
    private void initObserve() {
        if (mViewModel == null) return;
        mViewModel.getShowDialog(this, new Observer<DialogInfo>() {
            @Override
            public void onChanged(DialogInfo bean) {
                if (bean.isShow()) {
                    showDialog(bean.getMsg());
                } else {
                    dismissDialog();
                }
            }
        });
        mViewModel.getError(this, new Observer<Object>() {
            @Override
            public void onChanged(Object obj) {
                showError(obj);
            }
        });
    }

    /**
     * ViewModel层发生了错误
     */
    protected abstract void showError(Object obj);
}
