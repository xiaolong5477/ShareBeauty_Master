package com.sharebeauty.gxl.sharebeauty_master.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharebeauty.gxl.sharebeauty_master.Activity.AppointmentActivity;
import com.sharebeauty.gxl.sharebeauty_master.Interface.OnItemClickListener;
import com.sharebeauty.gxl.sharebeauty_master.Interface.OngetSelectNum;
import com.sharebeauty.gxl.sharebeauty_master.R;
import com.sharebeauty.gxl.sharebeauty_master.Utils.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jianghejie on 15/11/26.
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    public ArrayList<Integer> datas = null;
    public OngetSelectNum mOngetSelectNum;
    public OnItemClickListener mOnItemClickListener;
    public Context context;
    // 实现CheckBox单选
    private Map<Integer, Boolean> isSelected;

    public void setmOngetSelectNum(OngetSelectNum mOngetSelectNum) {
        this.mOngetSelectNum = mOngetSelectNum;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.mOnItemClickListener = onItemClickListener;
    }
    public ShopAdapter(Context context, ArrayList<Integer> datas) {
        this.datas = datas;
        this.context=context;
        InitMapSelect(datas);
    }
    private void InitMapSelect(ArrayList<Integer> datas){
        if(datas!=null&&datas.size()>0){
            if (isSelected != null)
                isSelected = null;
            isSelected = new HashMap<Integer, Boolean>();
            for (int i = 0; i < datas.size(); i++) {
                isSelected.put(i, false);
            }
        }else{
            isSelected = new HashMap<Integer, Boolean>();
        }
    }
    public void AllMapSelect(boolean select){
        if(datas!=null&&datas.size()>0&&isSelected!=null&&isSelected.size()>0){
           if(select){
               for (int i = 0; i < datas.size(); i++) {
                   isSelected.put(i, true);
               }
           }else{
               for (int i = 0; i < datas.size(); i++) {
                   isSelected.put(i, false);
               }
           }
            notifyDataSetChanged();
        }else{
            isSelected = new HashMap<Integer, Boolean>();
        }
        mOngetSelectNum.getSelect(getSelectItem(),getCountPrice());
    }
    public int getSelectItem(){
        int ItemCount=0;
        for(int i=0;i<isSelected.size();i++){
            if(isSelected.get(i)){
                ItemCount++;
            }
        }
        return ItemCount;
    }
    public int getCountPrice(){
        int CountPrice=0;
        for(int i=0;i<isSelected.size();i++){
            if(isSelected.get(i)){
                CountPrice=CountPrice+75;
            }
        }
        return CountPrice;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_item,viewGroup,false);
        return new ViewHolder(viewGroup,view,mOnItemClickListener);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
//        ClickEven(holder,position);
        SelectCheckbox(holder,position);
    }
    //checkbox事件处理
    private void SelectCheckbox(ViewHolder holder,final int position){
        holder.check_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cu = !isSelected.get(position);
                // 再将当前选择CB的实际状态
                isSelected.put(position, cu);
                mOngetSelectNum.getSelect(getSelectItem(),getCountPrice());
                notifyDataSetChanged();
            }
        });
        holder.check_item.setChecked(isSelected.get(position));
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
        private CheckBox check_item;
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
            check_item= (CheckBox) view.findViewById(R.id.check_item);
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
