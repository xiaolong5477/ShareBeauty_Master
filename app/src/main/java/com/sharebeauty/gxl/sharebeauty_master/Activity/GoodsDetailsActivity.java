package com.sharebeauty.gxl.sharebeauty_master.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.sharebeauty.gxl.sharebeauty_master.Adapter.GoodsDetailsAdapter;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.MyPageAdapter;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.ShopAdapter;
import com.sharebeauty.gxl.sharebeauty_master.BaseActivity;
import com.sharebeauty.gxl.sharebeauty_master.CustomView.DividerItemDecoration;
import com.sharebeauty.gxl.sharebeauty_master.Fragment.GoodsDetailsHeadFragment;
import com.sharebeauty.gxl.sharebeauty_master.Fragment.IndexHeadFragment;
import com.sharebeauty.gxl.sharebeauty_master.Interface.OnItemClickListener;
import com.sharebeauty.gxl.sharebeauty_master.R;
import com.sharebeauty.gxl.sharebeauty_master.Utils.T;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class GoodsDetailsActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.title)private TextView title;
    @ViewInject(R.id.left_arrow)private ImageView left_arrow;
    @ViewInject(R.id.right_img)private ImageView right_img;
    @ViewInject(R.id.recyclerview)private XRecyclerView_SlideHead recyclerview;
    @ViewInject(R.id.topbar)private RelativeLayout topbar;

    //添加的头部的轮播图
    private ViewPager Goods_headImg;
    private CirclePageIndicator DotBox;
    private List<Fragment> Fragment_list = new ArrayList<Fragment>();
    private MyPageAdapter PageAdapter;

    //导航栏高度
    private int listHeaderHeight;
    private LinearLayoutManager layoutManager;

    private ArrayList<Integer> mDatas=new ArrayList<>();
    private GoodsDetailsAdapter Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodsdetails_layout);
        ViewUtils.inject(this);
        InitData();
        InitView();
        InitFragMent();
        setViewPagerAtt();
    }
    //创建FragMnet
    private void InitFragMent(){
        Fragment_list.clear();
        for (int i = 0; i < 5; i++) {
            GoodsDetailsHeadFragment Fragment= GoodsDetailsHeadFragment.MyFragment();
            Fragment_list.add(Fragment);
        }
    }
    private void setViewPagerAtt(){
        PageAdapter=new MyPageAdapter(getSupportFragmentManager(),Fragment_list);
        Goods_headImg.setAdapter(PageAdapter);
        DotBox.setViewPager(Goods_headImg);
    }
    private void FindHeadView(View header) {
        Goods_headImg= (ViewPager)header.findViewById(R.id.Goods_headImg);
        DotBox= (CirclePageIndicator) header.findViewById(R.id.DotBox);
    }
    private void InitData() {
        listHeaderHeight=(int) getResources().getDimension(R.dimen.topbar_height);
        for(int i=0;i<3;i++){
            mDatas.add(i);
        }
    }
    private void InitView() {
        title.setText("商品详情");
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
        //添加的是头部
        View header =   LayoutInflater.from(this).inflate(R.layout.goods_details_head, (ViewGroup)findViewById(android.R.id.content),false);
        recyclerview.addHeaderView(header);
        FindHeadView(header);
        //添加的是底部
        View foot =   LayoutInflater.from(this).inflate(R.layout.goods_details_foot, (ViewGroup)findViewById(android.R.id.content),false);
        recyclerview.addFootView(foot);
        recyclerview.setLoadingListener(new XRecyclerView_SlideHead.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mDatas.clear();
                        for (int i = 0; i < 4; i++) {
                            mDatas.add(i);
                        }
                        Adapter.notifyDataSetChanged();
                        recyclerview.refreshComplete();
                    }

                }, 1000);
            }

            @Override
            public void onLoadMore() {
            }
        });
        Adapter=new GoodsDetailsAdapter(this,mDatas);
        //recycleview的点击监听
        Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent,View view, int position) {

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent,View view, int position) {
                return false;
            }
        });
        recyclerview.setAdapter(Adapter);
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
            float progress=currentTopOffset/listHeaderHeight;
            Log.e("CurrentTopOffset", "progress=" + progress);

            if(layoutManager.findLastVisibleItemPosition()==(recyclerView.getAdapter().getItemCount()-1)){
                return;
            }
            setTopBarHeight(listHeaderHeight * (1 - progress));
            setAnim(1 - progress);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_arrow:
                finish();
                break;
        }
    }
}
