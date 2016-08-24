package com.sharebeauty.gxl.sharebeauty_master.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sharebeauty.gxl.sharebeauty_master.MainFragment.BaseFragment;
import com.sharebeauty.gxl.sharebeauty_master.R;


public class GoodsDetailsHeadFragment extends BaseFragment {
	private ImageView iv;
	public static GoodsDetailsHeadFragment MyFragment()
	{
		GoodsDetailsHeadFragment Myfragment=new GoodsDetailsHeadFragment();
		Bundle bundle = new Bundle();
		bundle.putString("URL","www.baidu.com");
		Myfragment.setArguments(bundle);
		return Myfragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//实例化bitmapUtils
		View view = inflater.inflate(R.layout.index_head_img,container,false);
		iv=(ImageView) view.findViewById(R.id.head_img);
		iv.setImageResource(R.drawable.goods_details_head_img);
		iv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent=new Intent(getActivity(),ServerDetails.class);
//				startActivity(intent);
			}
		});
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
