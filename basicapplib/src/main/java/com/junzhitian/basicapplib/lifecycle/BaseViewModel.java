package com.junzhitian.basicapplib.lifecycle;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;


import com.junzhitian.basicapplib.bean.DialogInfo;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @作者:junzhitian
 * @时间:2020/1/19
 * @描述:ViewModel基类，管理rxJava发出的请求，ViewModel销毁同时也取消请求
 */
public abstract class BaseViewModel extends ViewModel {

    /**
     * 管理RxJava请求
     */
    private   CompositeDisposable        compositeDisposable;
    /**
     * 用来通知 Activity／Fragment 是否显示等待Dialog
     */
    protected DialogLiveData<DialogInfo> mDialogData = new DialogLiveData<>();
    /**
     * 当ViewModel层出现错误需要通知到Activity／Fragment
     */
    protected MutableLiveData<Object>    mErrorData  = new MutableLiveData<>();

    /**
     * 添加 rxJava 发出的请求
     */
    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void getShowDialog(LifecycleOwner owner, Observer<DialogInfo> observer) {
        mDialogData.observe(owner, observer);
    }

    public void getError(LifecycleOwner owner, Observer<Object> observer) {
        mErrorData.observe(owner, observer);
    }

    /**
     * ViewModel销毁同时也取消请求
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
        mDialogData = null;
        mErrorData = null;
    }
}
