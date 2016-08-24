package com.sharebeauty.gxl.sharebeauty_master.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView_SlideHead;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.ConfirmOrderAdapter;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.ShopAdapter;
import com.sharebeauty.gxl.sharebeauty_master.BaseActivity;
import com.sharebeauty.gxl.sharebeauty_master.CustomView.DividerItemDecoration;
import com.sharebeauty.gxl.sharebeauty_master.Interface.OnItemClickListener;
import com.sharebeauty.gxl.sharebeauty_master.R;
import com.sharebeauty.gxl.sharebeauty_master.Utils.T;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/1.
 */
public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.title)private TextView title;
    @ViewInject(R.id.left_arrow)private ImageView left_arrow;
    @ViewInject(R.id.right_img)private ImageView right_img;
    @ViewInject(R.id.recyclerview)private XRecyclerView recyclerview;
    @ViewInject(R.id.Appointment_btn)private TextView Appointment_btn;

    private ArrayList<Integer> mDatas=new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private ConfirmOrderAdapter Adapter;
    //头脚布局
    private View header;
    private View footer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ViewUtils.inject(this);
        InitData();
        InitView();

    }
    private void InitData() {
        for(int i=0;i<2;i++){
            mDatas.add(i);
        }
    }
    private void InitView() {
        title.setText("确认订单");
        left_arrow.setOnClickListener(this);
        Appointment_btn.setOnClickListener(this);
        right_img.setVisibility(View.INVISIBLE);
        recyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setRefreshProgressStyle(ProgressStyle.LineScalePulseOutRapid);
        recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
        recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);


        //添加头布局
        header =   LayoutInflater.from(this).inflate(R.layout.confirm_order_address, (ViewGroup) findViewById(android.R.id.content), false);
        header.setOnClickListener(this);
        recyclerview.addHeaderView(header);
        footer =   LayoutInflater.from(this).inflate(R.layout.confirm_order_price, (ViewGroup) findViewById(android.R.id.content), false);
        recyclerview.addFootView(footer);
//        recyclerview.setLoadingMoreEnabled(false);
        Adapter=new ConfirmOrderAdapter(this,mDatas);
        recyclerview.setAdapter(Adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_arrow:
                finish();
                break;
            case R.id.Order_Address_Box:
                startActivity(new Intent(this,SelectAddressActivity.class));
                break;
            case R.id.Appointment_btn:
                startActivity(new Intent(this, PayActivity.class));
                break;
        }
    }
}
