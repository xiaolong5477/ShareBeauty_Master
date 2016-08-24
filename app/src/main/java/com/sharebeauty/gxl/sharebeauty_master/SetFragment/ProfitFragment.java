package com.sharebeauty.gxl.sharebeauty_master.SetFragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.LowerAdapter;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.ProfitAdapter;
import com.sharebeauty.gxl.sharebeauty_master.CustomView.DividerItemDecoration;
import com.sharebeauty.gxl.sharebeauty_master.MainFragment.BaseFragment;
import com.sharebeauty.gxl.sharebeauty_master.R;

import java.util.ArrayList;


public class ProfitFragment extends BaseFragment{

	@ViewInject(R.id.recyclerview)private XRecyclerView recyclerview;
	private ArrayList<Integer> mDatas=new ArrayList<>();
	private ProfitAdapter Adapter;
	private LinearLayoutManager layoutManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.profit_layout,container,false);
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
		Adapter=new ProfitAdapter(getActivity(),mDatas);
		recyclerview.setAdapter(Adapter);
	}
}
