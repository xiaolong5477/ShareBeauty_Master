package com.sharebeauty.gxl.sharebeauty_master.Interface;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/7/26.
 */
public interface OnItemClickListener<T>
{
    void onItemClick(ViewGroup parent,View view,int position);
    boolean onItemLongClick(ViewGroup parent ,View view,int position);
}
