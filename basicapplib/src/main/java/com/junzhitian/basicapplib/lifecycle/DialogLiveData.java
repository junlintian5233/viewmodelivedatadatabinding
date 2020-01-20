package com.junzhitian.basicapplib.lifecycle;

import androidx.lifecycle.MutableLiveData;

import com.junzhitian.basicapplib.bean.DialogInfo;

/**
 * @作者:junzhitian
 * @时间:2020/1/19
 * @描述:
 */
public final class DialogLiveData<T> extends MutableLiveData<T> {

    private DialogInfo mDialogInfo = new DialogInfo();

    public void setValue(boolean isShow) {
        mDialogInfo.setShow(isShow);
        mDialogInfo.setMsg("");
        setValue((T) mDialogInfo);
    }

    public void setValue(boolean isShow, String msg) {
        mDialogInfo.setShow(isShow);
        mDialogInfo.setMsg(msg);
        setValue((T) mDialogInfo);
    }
}