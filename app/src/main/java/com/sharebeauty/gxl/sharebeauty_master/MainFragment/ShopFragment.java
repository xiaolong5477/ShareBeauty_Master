package com.sharebeauty.gxl.sharebeauty_master.MainFragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView_SlideHead;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sharebeauty.gxl.sharebeauty_master.Activity.ConfirmOrderActivity;
import com.sharebeauty.gxl.sharebeauty_master.Activity.GoodsDetailsActivity;
import com.sharebeauty.gxl.sharebeauty_master.Activity.ProjectDetailsActivity;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.ShopAdapter;
import com.sharebeauty.gxl.sharebeauty_master.CustomView.DividerItemDecoration;
import com.sharebeauty.gxl.sharebeauty_master.Interface.OnItemClickListener;
import com.sharebeauty.gxl.sharebeauty_master.Interface.OngetSelectNum;
import com.sharebeauty.gxl.sharebeauty_master.R;
import com.sharebeauty.gxl.sharebeauty_master.Utils.T;
import com.sharebeauty.gxl.sharebeauty_master.Utils.XLog;

import java.util.ArrayList;


public class ShopFragment extends BaseFragment implements View.OnClickListener{
	@ViewInject(R.id.title)private TextView title;
	@ViewInject(R.id.left_arrow)private ImageView left_arrow;
	@ViewInject(R.id.right_img)private ImageView right_img;
	@ViewInject(R.id.recyclerview)private XRecyclerView_SlideHead recyclerview;
	@ViewInject(R.id.topbar)private RelativeLayout topbar;
	@ViewInject(R.id.FAB)private FloatingActionButton FAB;
	@ViewInject(R.id.Select_All)private LinearLayout Select_All;
	@ViewInject(R.id.check_item)private CheckBox check_item;
	@ViewInject(R.id.Item_Count)private TextView Item_Count;
	@ViewInject(R.id.Price_Count)private TextView Price_Count;

	private ArrayList<Integer> mDatas=new ArrayList<>();
	private ShopAdapter Adapter;
	//上一次滑动是记录的位置
	private int RecycleviewHeight=0;
	//滑行的总量
	private int RecycleviewHeightCount=0;

	//导航栏高度
	private int listHeaderHeight;
	private LinearLayoutManager layoutManager;

	//Test
	private int Eg=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.shop_layout,container,false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		InitData();
		InitView();
	}
	private void InitData() {
		listHeaderHeight=(int) getResources().getDimension(R.dimen.topbar_height);
		for(int i=0;i<20;i++){
			mDatas.add(i);
		}
	}
	private void InitView() {
		title.setText("购物车");
		FAB.setOnClickListener(this);
		Select_All.setOnClickListener(this);
		check_item.setOnClickListener(this);
		left_arrow.setVisibility(View.INVISIBLE);
		right_img.setVisibility(View.INVISIBLE);
		recyclerview.setHasFixedSize(true);
		recyclerview.addOnScrollListener(scrollListener);
		layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerview.setLayoutManager(layoutManager);
		recyclerview.setRefreshProgressStyle(ProgressStyle.LineScalePulseOutRapid);
		recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
		recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);


		//自定义功能
		recyclerview.setSlideHeadHeight(listHeaderHeight);
		recyclerview.setLoadingMoreEnabled(false);
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

				}, 1000);
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
		Adapter=new ShopAdapter(getActivity(),mDatas);
		//recycleview的点击监听
		Adapter.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(ViewGroup parent, View view, int position) {
				startActivity(new Intent(getActivity(), GoodsDetailsActivity.class));
			}

			@Override
			public boolean onItemLongClick(ViewGroup parent, View view, int position) {
				T.showShort(getActivity(), "position=" + position);
				return false;
			}
		});
		Adapter.setmOngetSelectNum(new OngetSelectNum() {
			@Override
			public void getSelect(int itemNum, int countPrice) {
				Item_Count.setText(String.valueOf(itemNum));
				Price_Count.setText("￥" + countPrice);
			}
		});
		recyclerview.setAdapter(Adapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.FAB:
				startActivity(new Intent(getActivity(), ConfirmOrderActivity.class));
				break;
			case R.id.Select_All:
				check_item.setChecked(!check_item.isChecked());
				if(Adapter!=null){
					Adapter.AllMapSelect(check_item.isChecked());
				}
				break;
			case R.id.check_item:
				if(Adapter!=null){
					Adapter.AllMapSelect(check_item.isChecked());
				}
				break;
		}
	}

	//监听recyclerview的滚动时间，并且设置topbar随滑动而滑动，且加了动画
	private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			setFABType();
			View header = layoutManager.findViewByPosition(0);

			float currentTopOffset;
			if (header == null) {
				currentTopOffset = listHeaderHeight;
			} else {
				currentTopOffset = -header.getTop();
			}
			float progress=currentTopOffset/listHeaderHeight;
			Log.e("CurrentTopOffset", "currentTopOffset=" + currentTopOffset);

			if(layoutManager.findLastVisibleItemPosition()==(recyclerView.getAdapter().getItemCount()-1)){
				return;
			}
			setTopBarHeight(listHeaderHeight * (1 - progress));
			setAnim(1 - progress);
		}
	};
	//设置FAB的显示还是隐藏
	private void setFABType(){
		int position = layoutManager.findFirstVisibleItemPosition();
		View firstVisiableChildView = layoutManager.findViewByPosition(position);
		int itemHeight = firstVisiableChildView.getHeight();
		RecycleviewHeightCount=RecycleviewHeightCount+(((position) * itemHeight - firstVisiableChildView.getTop())-RecycleviewHeight);
		if(((position) * itemHeight - firstVisiableChildView.getTop())>RecycleviewHeight){
			//向上滑
			FAB.hide();
		}else {
			//向下滑
			FAB.show();
		}
		RecycleviewHeight=(position) * itemHeight - firstVisiableChildView.getTop();
		XLog.e("Shop","SollD="+((position) * itemHeight - firstVisiableChildView.getTop()));
	}
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
