package com.sharebeauty.gxl.sharebeauty_master.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharebeauty.gxl.sharebeauty_master.Interface.OnItemClickListener;
import com.sharebeauty.gxl.sharebeauty_master.R;

import java.util.ArrayList;

/**
 * Created by jianghejie on 15/11/26.
 */
public class ConfirmOrderAdapter extends RecyclerView.Adapter<ConfirmOrderAdapter.ViewHolder> {
    public ArrayList<Integer> datas = null;
    public OnItemClickListener mOnItemClickListener;
    public Context context;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.mOnItemClickListener = onItemClickListener;
    }
    public ConfirmOrderAdapter(Context context, ArrayList<Integer> datas) {
        this.datas = datas;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.confirm_order_item,viewGroup,false);
        return new ViewHolder(viewGroup,view,mOnItemClickListener);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
//        ClickEven(holder,position);
    }
    //点击事件处理
    private void ClickEven(ViewHolder holder,final int position){
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        public View view;
        public ViewGroup viewGroup;
        private OnItemClickListener onItemClickListener;
        private ImageView Icon;
        private TextView Appointment_btn;
        public ViewHolder(ViewGroup viewGroup,View view,OnItemClickListener onItemClickListener){
            super(view);
            this.view = view;
            this.viewGroup = viewGroup;
            this.onItemClickListener=onItemClickListener;
//            FindView(view);
            SetListener();

        }
        //设置控件的监听事件
        private void SetListener() {
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }
        //找到控件的对象
        private void FindView(View view) {

        }
        @Override
        public void onClick(View v) {
            if(onItemClickListener!=null)
                onItemClickListener.onItemClick(viewGroup,v,getAdapterPosition()-3);
        }

        @Override
        public boolean onLongClick(View v) {
            if(onItemClickListener!=null){
                onItemClickListener.onItemLongClick(viewGroup,v,getAdapterPosition()-3);
            }
            return true;
        }
    }
}
