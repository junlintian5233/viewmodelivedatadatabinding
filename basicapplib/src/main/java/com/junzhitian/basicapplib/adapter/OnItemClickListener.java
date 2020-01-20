package com.junzhitian.basicapplib.adapter;

import android.view.View;

/**
 * @作者:junzhitian
 * @时间:2020/1/20
 * @描述:Item 长按、点击事件
 */
public interface OnItemClickListener {

    /**
     * Item 点击事件
     */
    void onItemClick(View view, int position);

    /**
     * Item 长按事件
     *
     */
    boolean onItemLongClick(View view, int position);

}
