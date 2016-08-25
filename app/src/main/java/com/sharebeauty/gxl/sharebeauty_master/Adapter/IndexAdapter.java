package com.sharebeauty.gxl.sharebeauty_master.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sharebeauty.gxl.sharebeauty_master.R;

import java.util.ArrayList;

/**
 * Created by jianghejie on 15/11/26.
 */
public class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public ArrayList<Integer> datas = null;
    public final static int TYPE_LEFT_BIG=10;
    public final static int TYPE_RIGHT_BIG=20;
    public final static int TYPE_STRIP=30;
    private View.OnClickListener onClickListener;
    private OnImageClick onImageClick;

    public OnImageClick getOnImageClick() {
        return onImageClick;
    }

    public void setOnImageClick(OnImageClick onImageClick) {
        this.onImageClick = onImageClick;
    }

    public IndexAdapter() {
        this.datas = new ArrayList<>();
        this.datas.add(TYPE_LEFT_BIG);
        this.datas.add(TYPE_RIGHT_BIG);
        this.datas.add(TYPE_STRIP);
        onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageClick.ImageClick(v.getId());
            }
        };
    }
    //创建新View，被LayoutManager所调用
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;
        if(viewType==TYPE_LEFT_BIG){
            view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_left,viewGroup,false);
            return new ViewHolder_Left(view);
        }else if(viewType==TYPE_RIGHT_BIG){
            view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_right,viewGroup,false);
            return new ViewHolder_Right(view);
        }else {
            view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sprit,viewGroup,false);
            return new ViewHolder_Strip(view);
        }
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder_Left){
            ((ViewHolder_Left) holder).index_equ.setOnClickListener(onClickListener);
            ((ViewHolder_Left) holder).index_project.setOnClickListener(onClickListener);
        }else if(holder instanceof ViewHolder_Right){
            ((ViewHolder_Right) holder).index_case.setOnClickListener(onClickListener);
            ((ViewHolder_Right) holder).index_plastic.setOnClickListener(onClickListener);
        }else{
            ((ViewHolder_Strip) holder).index_living.setOnClickListener(onClickListener);
        }
    }
    @Override
    public int getItemViewType(int position) {
        return datas.get(position);
    }



    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder_Left，持有每个Item的的所有界面元素
    public static class ViewHolder_Left extends RecyclerView.ViewHolder {
        private ImageView index_equ;
        private ImageView index_project;
        public ViewHolder_Left(View view){
            super(view);
            index_equ= (ImageView) view.findViewById(R.id.index_equ);
            index_project= (ImageView) view.findViewById(R.id.index_project);
        }
    }
    //自定义的ViewHolder_Right，持有每个Item的的所有界面元素
    public static class ViewHolder_Right extends RecyclerView.ViewHolder {
        private ImageView index_case;
        private ImageView index_plastic;
        public ViewHolder_Right(View view){
            super(view);
            index_case= (ImageView) view.findViewById(R.id.index_case);
            index_plastic= (ImageView) view.findViewById(R.id.index_plastic);
        }
    }
    //自定义的ViewHolder_Strip，持有每个Item的的所有界面元素
    public static class ViewHolder_Strip extends RecyclerView.ViewHolder {
        private ImageView index_living;
        public ViewHolder_Strip(View view){
            super(view);
            index_living= (ImageView) view.findViewById(R.id.index_living);
        }
    }
    //Item的点击事件
  public  interface OnImageClick{
        void ImageClick(int Id);
    }
}
