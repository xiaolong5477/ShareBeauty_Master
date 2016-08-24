package com.sharebeauty.gxl.sharebeauty_master.MainFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;


import com.sharebeauty.gxl.sharebeauty_master.NetWork.APICallBack;
import com.sharebeauty.gxl.sharebeauty_master.R;

import java.util.Timer;
import java.util.TimerTask;

public class BaseFragment extends Fragment implements APICallBack,
		OnKeyListener {

	public Dialog waitDialog;
	private Timer timer;
	// 定义handler，当数据下载完成之后，更新整个UI
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				Toast.makeText(getActivity(), "亲,网路开了个小差,请重试", Toast.LENGTH_SHORT)
						.show();
				releaseScreen();
				timer.cancel();
				break;
			}
		}
	};

	@Override
	public void apiOnFailure(int key, String strMsg) {

	}

	@Override
	public void apiOnSuccess(int key, String strMsg) {

	}

	@Override
	public void apiOnSuccess(int key, String strMsg, int rkey) {

	}

	public void showShortToast(String str) {
		Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
	}

	public void showLongToast(String str) {
		Toast.makeText(getActivity(), str, Toast.LENGTH_LONG).show();
	}

	public void lockScreen(boolean flag) {
		if (null == waitDialog) {
			waitDialog = new Dialog(getActivity(), R.style.AppThemeNoActionBar_dialog);
			waitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			waitDialog.setContentView(R.layout.wait_dialog_content_view);
			if (flag == true) {
				waitDialog.setOnKeyListener(this);
			}
		}
		if (null != waitDialog && !getActivity().isFinishing()
				&& !waitDialog.isShowing()) {
			waitDialog.show();
			// 定义计时器
			timer = new Timer();
			// 定义计划任务，根据参数的不同可以完成以下种类的工作：在固定时间执行某任务，在固定时间开始重复执行某任务，重复时间间隔可控，在延迟多久后执行某任务，在延迟多久后重复执行某任务，重复时间间隔可控
			timer.schedule(new TimerTask() {

				// TimerTask 是个抽象类,实现的是Runable类
				@Override
				public void run() {
					// 定义一个消息传过去
					Message msg = new Message();
					msg.what = 0;
					handler.sendMessage(msg);
				}

			}, 40000, 20000);
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
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			dismissDialog();
		}
		return false;
	}

	public void dismissDialog() {
		if (getActivity().isFinishing()) {
			return;
		}
		if (null != waitDialog && waitDialog.isShowing()) {
			waitDialog.dismiss();
			timer.cancel();
		}
	}

}
