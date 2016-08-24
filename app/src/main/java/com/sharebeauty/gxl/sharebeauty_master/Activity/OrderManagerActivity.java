package com.sharebeauty.gxl.sharebeauty_master.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView_SlideHead;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.CaseShareAdapter;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.OrderManagerAdapter;
import com.sharebeauty.gxl.sharebeauty_master.BaseActivity;
import com.sharebeauty.gxl.sharebeauty_master.CustomView.DividerItemDecoration;
import com.sharebeauty.gxl.sharebeauty_master.Interface.OnItemClickListener;
import com.sharebeauty.gxl.sharebeauty_master.R;
import com.sharebeauty.gxl.sharebeauty_master.Utils.T;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/9.
 */
public class OrderManagerActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.title)private TextView title;
    @ViewInject(R.id.left_arrow)private ImageView left_arrow;
    @ViewInject(R.id.right_img)private ImageView right_img;
    @ViewInject(R.id.recyclerview)private XRecyclerView_SlideHead recyclerview;
    @ViewInject(R.id.topbar)private RelativeLayout topbar;
    @ViewInject(R.id.order_all)private RelativeLayout order_all;
    @ViewInject(R.id.order_noPay)private RelativeLayout order_noPay;
    @ViewInject(R.id.order_noSend)private RelativeLayout order_noSend;
    @ViewInject(R.id.order_noCollect)private RelativeLayout order_noCollect;
    private ArrayList<Integer> mDatas=new ArrayList<>();
    private OrderManagerAdapter Adapter;

    //导航栏高度
    private int listHeaderHeight;
    private LinearLayoutManager layoutManager;

    //
    private int Eg=0;

    private int index = 0;
    private int currentTabIndex = 0;

    private RelativeLayout[] mTabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manager);
        ViewUtils.inject(this);
        InitData();
        InitView();
    }
    private void InitData() {
        mTabs= new RelativeLayout[]{order_all,order_noPay,order_noSend,order_noCollect};
        mTabs[0].setSelected(true);
        listHeaderHeight=(int) getResources().getDimension(R.dimen.topbar_height);
        for(int i=0;i<20;i++){
            mDatas.add(i);
        }
    }
    private ArrayList<Integer> GetData(int index){
        mDatas.clear();
        for(int i=0;i<index*3;i++){
            mDatas.add(i);
        }
        return mDatas;
    }
    private void InitView() {
        title.setText("订单管理");
        left_arrow.setOnClickListener(this);
        right_img.setVisibility(View.INVISIBLE);
        recyclerview.setHasFixedSize(true);
        recyclerview.addOnScrollListener(scrollListener);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setRefreshProgressStyle(ProgressStyle.LineScalePulseOutRapid);
        recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
        recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);

        //自定义功能
        recyclerview.setSlideHeadHeight(listHeaderHeight);

        recyclerview.setLoadingListener(new XRecyclerView_SlideHead.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mDatas.clear();
                        for (int i = 0; i < 10; i++) {
                            mDatas.add(i);
                        }
                        Adapter.notifyDataSetChanged();
                        recyclerview.refreshComplete();
                    }

                }, 2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Eg++;
                        mDatas.addAll(mDatas);
                        Adapter.notifyDataSetChanged();
                        recyclerview.loadMoreComplete();
                        if (Eg == 2) {
                            recyclerview.setLoadingMoreEnabled(false);
                        }
                    }

                }, 2000);
            }
        });
        Adapter=new OrderManagerAdapter(this,mDatas);
        Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent,View view, int position) {
                startActivity(new Intent(OrderManagerActivity.this,ProjectDetailsActivity.class));
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent,View view, int position) {
                T.showShort(OrderManagerActivity.this, "position=" + position);
                return false;
            }
        });
        recyclerview.setAdapter(Adapter);
        recyclerview.setRefreshing(true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_arrow:
                finish();
                break;
            case R.id.right_img:
                T.showShort(this,"+");
                break;
        }
    }
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.order_all:
                index = 0;
                break;
            case R.id.order_noPay:
                index = 1;
                break;
            case R.id.order_noSend:
                index = 2;
                break;
            case R.id.order_noCollect:
                index = 3;
                break;
        }
        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }


    //监听recyclerview的滚动时间，并且设置topbar随滑动而滑动，且加了动画
    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            View header = layoutManager.findViewByPosition(0);
            float currentTopOffset;
            if (header == null) {
                currentTopOffset = listHeaderHeight;
            } else {
                currentTopOffset = -header.getTop();
            }
            float progress = currentTopOffset / listHeaderHeight;
            float Y = (-listHeaderHeight)*progress;
            Log.e("CurrentTopOffset", "progress=" + progress);
            Log.e("CurrentTopOffset", "Y=" + Y);

            if(layoutManager.findLastVisibleItemPosition() == (recyclerView.getAdapter().getItemCount()-1)) {
                return;
            }
            setTopBarHeight(listHeaderHeight * (1 - progress));
            setAnim(1-progress);
        }
    };


    private void setTopBarHeight(float height){
        ViewGroup.LayoutParams lp;
        lp=topbar.getLayoutParams();
        lp.height= (int)height;
        topbar.setLayoutParams(lp);
    }
    private void setAnim(float progress){
        title.setScaleX(progress);
        title.setScaleY(progress);
        left_arrow.setScaleX(progress);
        left_arrow.setScaleY(progress);
        right_img.setScaleX(progress);
        right_img.setScaleY(progress);
    }

}
