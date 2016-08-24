package com.sharebeauty.gxl.sharebeauty_master.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sharebeauty.gxl.sharebeauty_master.CustomView.MyListView;
import com.sharebeauty.gxl.sharebeauty_master.Interface.OnItemClickListener;
import com.sharebeauty.gxl.sharebeauty_master.R;
import com.sharebeauty.gxl.sharebeauty_master.Utils.T;

import java.util.ArrayList;

/**
 * Created by jianghejie on 15/11/26.
 */
public class OrderManagerAdapter extends RecyclerView.Adapter<OrderManagerAdapter.ViewHolder> {
    public ArrayList<Integer> datas = null;
    public OnItemClickListener mOnItemClickListener;
    public Context context;
    ArrayList<Integer> Eg_Date=new ArrayList<>();
    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.mOnItemClickListener = onItemClickListener;
    }
    public OrderManagerAdapter(Context context, ArrayList<Integer> datas) {
        this.datas = datas;
        this.context=context;
        for(int i=0;i<2;i++){
            Eg_Date.add(i);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_manager_item,viewGroup,false);
        return new ViewHolder(viewGroup,view,mOnItemClickListener);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        OrderListAdapter adapter=new OrderListAdapter(context,Eg_Date);
        holder.Order_List.setAdapter(adapter);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        public MyListView Order_List;
        public View view;
        public ViewGroup viewGroup;
        private OnItemClickListener onItemClickListener;
        public ViewHolder(ViewGroup viewGroup,View view,OnItemClickListener onItemClickListener){
            super(view);
            this.view = view;
            this.viewGroup = viewGroup;
            this.onItemClickListener=onItemClickListener;
            FindView(view);
            SetListener();


        }
        //设置控件的监听事件
        private void SetListener() {
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }
        //找到控件的对象
        private void FindView(View view) {
            Order_List= (MyListView) view.findViewById(R.id.Order_List);
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
