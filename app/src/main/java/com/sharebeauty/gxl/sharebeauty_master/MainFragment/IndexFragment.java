package com.sharebeauty.gxl.sharebeauty_master.MainFragment;


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
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sharebeauty.gxl.sharebeauty_master.Activity.CaseShareActivity;
import com.sharebeauty.gxl.sharebeauty_master.Activity.EquipmentActivity;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.IndexAdapter;
import com.sharebeauty.gxl.sharebeauty_master.R;
import com.sharebeauty.gxl.sharebeauty_master.Utils.T;


public class IndexFragment extends BaseFragment implements View.OnClickListener,IndexAdapter.OnImageClick{
	@ViewInject(R.id.title)private TextView title;
	@ViewInject(R.id.left_arrow)private ImageView left_arrow;
	@ViewInject(R.id.right_img)private ImageView right_img;
	@ViewInject(R.id.recyclerview)private XRecyclerView recyclerview;

	private IndexAdapter mAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.index_layout,container,false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		InitView();
	}

	private void InitView() {
		title.setText("恒丽医疗美容");
		left_arrow.setVisibility(View.GONE);
		right_img.setOnClickListener(this);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerview.setLayoutManager(layoutManager);

		recyclerview.setRefreshProgressStyle(ProgressStyle.LineScalePulseOutRapid);
		recyclerview.setLoadingMoreProgressStyle(ProgressStyle.LineSpinFadeLoader);
		recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);

		View header =   LayoutInflater.from(getActivity()).inflate(R.layout.recyclerview_header, (ViewGroup)getActivity().findViewById(android.R.id.content),false);
		recyclerview.addHeaderView(header);

		recyclerview.setLoadingMoreEnabled(false);
		recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
			@Override
			public void onRefresh() {
				new Handler().postDelayed(new Runnable() {
					public void run() {
						mAdapter.notifyDataSetChanged();
						recyclerview.refreshComplete();
					}

				}, 1000);
			}
			@Override
			public void onLoadMore() {
			}
		});

		mAdapter = new IndexAdapter();
		mAdapter.setOnImageClick(this);
		recyclerview.setAdapter(mAdapter);
	}


	@Override
	public void onResume(){
		super.onResume();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.right_img:
				T.showShort(getActivity(),"+");
				break;
		}
	}

	@Override
	public void ImageClick(int Id) {
		Intent intent;
		switch (Id){
			case R.id.index_equ:
				intent=new Intent(getActivity(), EquipmentActivity.class);
				startActivity(intent);
				break;
			case R.id.index_project:
				T.showShort(getActivity(),"整形项目");
				break;
			case R.id.index_case:
				intent=new Intent(getActivity(), CaseShareActivity.class);
				startActivity(intent);
				break;
			case R.id.index_plastic:
				T.showShort(getActivity(),"微整形");
				break;
			case R.id.index_living:
				T.showShort(getActivity(),"生活美容");
				break;
		}
	}
}
