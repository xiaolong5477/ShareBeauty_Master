package com.sharebeauty.gxl.sharebeauty_master;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.sharebeauty.gxl.sharebeauty_master.NetWork.APICallBack;

import java.util.Timer;
import java.util.TimerTask;


public class BaseActivity extends FragmentActivity implements APICallBack,OnKeyListener{
	public static AlertDialog.Builder ab;
	public Dialog waitDialog;
	private Timer timer;
	public IntentFilter filter;
	//定义handler，当数据下载完成之后，更新整个UI
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			switch (msg.what){
			case 0:
				if(!isFinishing()){
					Toast.makeText(BaseActivity.this, "您的网络好像不给力哦~", Toast.LENGTH_SHORT).show();
					releaseScreen();
					timer.cancel();
				}else{
					releaseScreen();
					timer.cancel();

				}
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		if (null == waitDialog) {
			waitDialog = new Dialog(this,R.style.AppThemeNoActionBar_dialog);
			waitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			waitDialog.setContentView(R.layout.wait_dialog_content_view);
		}

	}
	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	public void apiOnFailure(int key, String strMsg) {
		showShortToast("您的网络好像不给力哦~");
	}

	@Override
	public void apiOnSuccess(int key, String strMsg) {

	}

	@Override
	public void apiOnSuccess(int key, String strMsg, int rkey) {

	}

	public void showShortToast(String str){
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	public void showLongToast(String str){
		Toast.makeText(this, str, Toast.LENGTH_LONG).show();
	}
	
	public void lockScreen(boolean flag){
		if(flag==true){
			waitDialog.setOnKeyListener(this);
		}
		if (null != waitDialog && !isFinishing() && !waitDialog.isShowing()) {
			waitDialog.show();
			// 定义计时器
			timer=new Timer();
			// 定义计划任务，根据参数的不同可以完成以下种类的工作：在固定时间执行某任务，在固定时间开始重复执行某任务，重复时间间隔可控，在延迟多久后执行某任务，在延迟多久后重复执行某任务，重复时间间隔可控
			timer.schedule(new TimerTask(){
				//TimerTask是个抽象类,实现的是Runable类
				@Override
				public void run(){
					//定义一个消息传过去
					Message msg = new Message();
					msg.what =0;
					handler.sendMessage(msg);
				}
			},100000,20000);
		}
	}

	public void releaseScreen() {
		if (null != waitDialog && waitDialog.isShowing()) {
			waitDialog.dismiss();
			timer.cancel();
		}
	}

	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
		{
			dismissDialog();
		}	
		return false;
	}

	public void dismissDialog() {  
		if (isFinishing()) {  
			return;  
		}  
		if (null != waitDialog && waitDialog.isShowing()) {
			waitDialog.dismiss();
			timer.cancel();
		}
	}  
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (null != waitDialog && waitDialog.isShowing()) {  
			dismissDialog();  
		} else {  
			super.onBackPressed();  
		}  
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		waitDialog.dismiss();

	}
	@Override
	protected void onPause() {
		super.onPause();
	}
}
