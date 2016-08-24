package com.sharebeauty.gxl.sharebeauty_master.MainFragment;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sharebeauty.gxl.sharebeauty_master.Activity.OrderManagerActivity;
import com.sharebeauty.gxl.sharebeauty_master.Interface.OnScrollTopBarListener;
import com.sharebeauty.gxl.sharebeauty_master.R;
import com.sharebeauty.gxl.sharebeauty_master.SetFragment.InfoFragment;
import com.sharebeauty.gxl.sharebeauty_master.SetFragment.LowerFragment;
import com.sharebeauty.gxl.sharebeauty_master.SetFragment.ProfitFragment;
import com.sharebeauty.gxl.sharebeauty_master.Utils.T;


public class SetFragment extends BaseFragment implements View.OnClickListener{
	@ViewInject(R.id.title)private TextView title;
	@ViewInject(R.id.left_arrow)private ImageView left_arrow;
	@ViewInject(R.id.right_img)private ImageView right_img;
	@ViewInject(R.id.Set_Content_Box)private RelativeLayout Set_Content_Box;
	@ViewInject(R.id.topbar)private RelativeLayout topbar;

	private View MenuView;

	private Fragment[] fragments;
	private LowerFragment lowerFragment;
	private ProfitFragment profitFragment;
	private InfoFragment infoFragment;

	private PopupWindow pop;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.set_layout, container, false);
		ViewUtils.inject(this, view);
		InitView();
		InitPopwindow();
		return view;
	}
	private void InitView() {
		title.setText("我的收益");
		left_arrow.setVisibility(View.GONE);
		right_img.setImageResource(R.drawable.right_menu);
		right_img.setOnClickListener(this);
		lowerFragment=new LowerFragment();
		profitFragment=new ProfitFragment();
		infoFragment=new InfoFragment();
		fragments=new Fragment[]{lowerFragment,profitFragment,infoFragment};
		//设置初始化Fragment
		FragmentTransaction trx = getActivity().getSupportFragmentManager().beginTransaction();
		trx.replace(R.id.Set_Content_Box, fragments[1]);
		trx.commit();
	}
	//初始化popwindows
	private void InitPopwindow(){
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		View view = inflater.inflate(R.layout.set_menu, null);
		MenuView=view.findViewById(R.id.Menu_Box);
		FindMenuItem(MenuView);
		pop = new PopupWindow(view,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,true);
		View viewno = view.findViewById(R.id.view);
		// 需要设置一下此参数，点击外边可消失
		pop.setBackgroundDrawable(new BitmapDrawable());
		// 设置点击窗口外边窗口消失
		pop.setOutsideTouchable(true);
		// 设置此参数获得焦点,否则无法点击
		pop.setFocusable(true);
		viewno.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		pop.setAnimationStyle(R.style.popwin_anim_style);
	}

	private void FindMenuItem(View menuView) {
		TextView Menu_Lower= (TextView) menuView.findViewById(R.id.Menu_Lower);
		TextView Menu_Profit= (TextView) menuView.findViewById(R.id.Menu_Profit);
		TextView Menu_Order= (TextView) menuView.findViewById(R.id.Menu_Order);
		TextView Menu_Info= (TextView) menuView.findViewById(R.id.Menu_Info);
		Menu_Lower.setOnClickListener(this);
		Menu_Profit.setOnClickListener(this);
		Menu_Order.setOnClickListener(this);
		Menu_Info.setOnClickListener(this);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onClick(View v) {
		FragmentTransaction trx = getActivity().getSupportFragmentManager().beginTransaction();
		switch (v.getId()){
			case R.id.right_img:
				pop.showAsDropDown(topbar);
				break;
			case R.id.Menu_Lower:
				title.setText("我的下级");
				trx.replace(R.id.Set_Content_Box, fragments[0]);
				trx.commit();
				pop.dismiss();
				break;
			case R.id.Menu_Profit:
				title.setText("我的收益");
				trx.replace(R.id.Set_Content_Box, fragments[1]);
				trx.commit();
				pop.dismiss();
				break;
			case R.id.Menu_Order:
				startActivity(new Intent(getActivity(), OrderManagerActivity.class));
				break;
			case R.id.Menu_Info:
				title.setText("修改个人信息");
				trx.replace(R.id.Set_Content_Box, fragments[2]);
				trx.commit();
				pop.dismiss();
				break;
		}
	}
}
