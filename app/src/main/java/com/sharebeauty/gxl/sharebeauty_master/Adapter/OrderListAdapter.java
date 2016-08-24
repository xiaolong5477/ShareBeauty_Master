package com.sharebeauty.gxl.sharebeauty_master.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharebeauty.gxl.sharebeauty_master.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/9.
 */
public class OrderListAdapter extends BaseAdapter {
    private Context context;
    public ArrayList<Integer> datas = null;

    public OrderListAdapter(Context context, ArrayList<Integer> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView=View.inflate(context, R.layout.confirm_order_item, null);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private class ViewHolder{
        ImageView Order_Img;
    }
}
