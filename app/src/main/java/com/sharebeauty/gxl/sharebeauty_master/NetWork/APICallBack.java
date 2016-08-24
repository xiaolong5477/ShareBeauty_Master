package com.sharebeauty.gxl.sharebeauty_master.NetWork;


/**
 * api回调
 * @author Cool
 *
 */
public interface APICallBack {
	void apiOnFailure(int key, String strMsg);
	void apiOnSuccess(int key, String strMsg);
	void apiOnSuccess(int key, String strMsg, int rkey);
}
