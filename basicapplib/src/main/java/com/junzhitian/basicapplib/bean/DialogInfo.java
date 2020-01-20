package com.junzhitian.basicapplib.bean;

/**
 * @作者:junzhitian
 * @时间:2020/1/19
 * @描述:dialog弹出框信息
 */
public final class DialogInfo {

    private boolean isShow;
    private String  msg;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
