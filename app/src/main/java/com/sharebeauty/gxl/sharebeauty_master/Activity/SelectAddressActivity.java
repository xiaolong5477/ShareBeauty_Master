package com.sharebeauty.gxl.sharebeauty_master.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.ConfirmOrderAdapter;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.SelectAddressAdapter;
import com.sharebeauty.gxl.sharebeauty_master.BaseActivity;
import com.sharebeauty.gxl.sharebeauty_master.CustomView.DividerItemDecoration;
import com.sharebeauty.gxl.sharebeauty_master.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/2.
 */
public class SelectAddressActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.title)private TextView title;
    @ViewInject(R.id.left_arrow)private ImageView left_arrow;
    @ViewInject(R.id.right_img)private ImageView right_img;
    @ViewInject(R.id.recyclerview)private XRecyclerView recyclerview;

    private ArrayList<Integer> mDatas=new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private SelectAddressAdapter Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
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
        title.setText("选择收货地址");
        left_arrow.setOnClickListener(this);
        right_img.setOnClickListener(this);
        recyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setRefreshProgressStyle(ProgressStyle.LineScalePulseOutRapid);
        recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
        recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerview.setLoadingMoreEnabled(false);
        Adapter=new SelectAddressAdapter(this,mDatas);
        recyclerview.setAdapter(Adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_arrow:
                finish();
                break;
            case R.id.right_img:
                startActivity(new Intent(this,NewAddressActivity.class));
                break;
        }
    }
}
