package com.sharebeauty.gxl.sharebeauty_master.SetFragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView_SlideHead;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sharebeauty.gxl.sharebeauty_master.Activity.GoodsDetailsActivity;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.LowerAdapter;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.ShopAdapter;
import com.sharebeauty.gxl.sharebeauty_master.CustomView.DividerItemDecoration;
import com.sharebeauty.gxl.sharebeauty_master.Interface.OnItemClickListener;
import com.sharebeauty.gxl.sharebeauty_master.Interface.OnScrollTopBarListener;
import com.sharebeauty.gxl.sharebeauty_master.MainFragment.BaseFragment;
import com.sharebeauty.gxl.sharebeauty_master.R;
import com.sharebeauty.gxl.sharebeauty_master.Utils.T;
import com.sharebeauty.gxl.sharebeauty_master.Utils.XLog;

import java.util.ArrayList;


public class LowerFragment extends BaseFragment{

	@ViewInject(R.id.recyclerview)private XRecyclerView recyclerview;
	private ArrayList<Integer> mDatas=new ArrayList<>();
	private LowerAdapter Adapter;
	private LinearLayoutManager layoutManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.lower_layout,container,false);
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
		for(int i=0;i<20;i++){
			mDatas.add(i);
		}
	}
	private void InitView() {
		recyclerview.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		recyclerview.setLayoutManager(layoutManager);
		recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
		recyclerview.setRefreshProgressStyle(ProgressStyle.LineScalePulseOutRapid);
		recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
		recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);

		recyclerview.setLoadingMoreEnabled(false);
		recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
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
			}
		});
		Adapter=new LowerAdapter(getActivity(),mDatas);
		recyclerview.setAdapter(Adapter);
	}
}
