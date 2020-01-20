package com.junzhitian.viewmodelivedatadatabinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.junzhitian.basicapplib.base.BaseActivity;
import com.junzhitian.viewmodelivedatadatabinding.bean.UserInfo;
import com.junzhitian.viewmodelivedatadatabinding.databinding.ActivityMainBinding;
import com.junzhitian.viewmodelivedatadatabinding.viewmodel.UserVM;

public class MainActivity extends BaseActivity<UserVM, ActivityMainBinding> {

    @Override
    protected int onSetLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mViewModel.requestData();
        mViewModel.getUserInfo().observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo userInfo) {
                mDataBinding.setInfo(userInfo);
            }
        });
    }

    @Override
    protected UserVM initViewModel() {
        return ViewModelProviders.of(this).get(UserVM.class);
    }

    @Override
    protected void showError(Object obj) {

    }
}
