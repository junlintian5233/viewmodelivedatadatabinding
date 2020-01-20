package com.junzhitian.viewmodelivedatadatabinding.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.junzhitian.basicapplib.lifecycle.BaseViewModel;
import com.junzhitian.viewmodelivedatadatabinding.bean.UserInfo;
import com.junzhitian.viewmodelivedatadatabinding.http.Api;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @作者:TJ
 * @时间:2020/1/19
 * @描述:
 */
public class UserVM extends BaseViewModel {
    private MutableLiveData<UserInfo> mLiveData;

    public UserVM() {
        mLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<UserInfo> getUserInfo() {
        return mLiveData;
    }

    /**
     * 请求网络数据
     */
    public void requestData() {
        mDialogData.setValue(true, "加载中");

        Disposable disposable = Api.getInstance().getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInfo>() {
                    @Override
                    public void accept(UserInfo newsBean) throws Exception {
                        mDialogData.setValue(false);
                        mLiveData.setValue(newsBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mDialogData.setValue(false);
                        /*
                         * 发生了错误，通知UI层
                         */
                        mErrorData.setValue("发生错误了");
                    }
                });
        addDisposable(disposable);
    }
}
