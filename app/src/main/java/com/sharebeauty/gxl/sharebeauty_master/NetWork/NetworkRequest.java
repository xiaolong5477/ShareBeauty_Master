package com.sharebeauty.gxl.sharebeauty_master.NetWork;

import android.text.TextUtils;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;

//客户端
public class NetworkRequest {
	private APICallBack apiCallBack;//回调


	public NetworkRequest(APICallBack apiCallBack){
		this.apiCallBack = apiCallBack;
	}

	public long getTime() {
		return System.currentTimeMillis();
	}

	/**
	 * Get请求
	 */
	public void apiGetCall(String apiUrl,final int apiKey) {
		HttpUtils http = new HttpUtils();
		http.configSoTimeout(10000 * 10);
		http.configDefaultHttpCacheExpiry(0);
		http.configCurrentHttpCacheExpiry(0*1000);
		Log.e("messi", "最终的get:" + Constants.BASE_HTTP_PATH + apiUrl);
		http.send(HttpRequest.HttpMethod.GET, Constants.BASE_HTTP_PATH + apiUrl,
				new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				if (null != apiCallBack) {
					apiCallBack.apiOnFailure(apiKey, arg1);
				}
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				if (null != apiCallBack) {
					apiCallBack.apiOnSuccess(apiKey, arg0.result);
				}
			}
		});
	}

	/**
	 * Post请求
	 */
	public void apiPostCall(String apiUrl, RequestParams params,
			final int apiKey) {
		HttpUtils http = new HttpUtils();
		http.configSoTimeout(10000 * 10);
		http.configDefaultHttpCacheExpiry(0);
		http.configCurrentHttpCacheExpiry(0 * 1000);
		Log.e("messi", "最终的post:" + Constants.BASE_HTTP_PATH + apiUrl);
		http.send(HttpRequest.HttpMethod.POST, Constants.BASE_HTTP_PATH + apiUrl, params,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						if (null != apiCallBack) {
							apiCallBack.apiOnFailure(apiKey, arg1);
						}
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						if (null != apiCallBack) {
							apiCallBack.apiOnSuccess(apiKey, arg0.result);
						}
					}
				});
	}
	//版本升级
	public void UpVersions(int key) {
		String url = "users/api.php?f=update";
		RequestParams params = new RequestParams();
		params.addBodyParameter("class", "3");
		apiPostCall(url, params, key);
	}
	//-------------------------Start2016.1.7----------------------------------//
	//登录
	public void Login(int key, String Username,String password) {
		String url = "technicians/login.php";
		RequestParams params = new RequestParams();
		params.addBodyParameter("Username", Username);
		params.addBodyParameter("password", password);
		apiPostCall(url, params, key);
	}
	//修改用户名
	public void FixUserName(int key, String Newname,String token,String userId) {
		String url = "technicians/infoMod.php";
		RequestParams params = new RequestParams();
		params.addBodyParameter("Newname", Newname);
		params.addBodyParameter("token", token);
		params.addBodyParameter("userId", userId);
		apiPostCall(url, params, key);
	}
	//修改密码
	public void AlterPassword(int key,String token,String userId,String oldpassword,String newpassword) {
		String url = "technicians/passMod.php";
		RequestParams params = new RequestParams();
		params.addBodyParameter("token", token);
		params.addBodyParameter("userId", userId);
		params.addBodyParameter("oldpassword", oldpassword);
		params.addBodyParameter("newpassword", newpassword);
		apiPostCall(url, params, key);
	}
	//首页数据
	public void IndexData(int key, String token,String userId) {
		String url = "technicians/info.php";
		ArrayList<String> mStringList = new ArrayList<String>();
		mStringList.add("token" + "=" + token + "&");
		mStringList.add("userId" + "=" + userId + "&");
		String zuizhong = mStringList.toString()
				.substring(1, mStringList.toString().length() - 2)
				.replace(",", "").replaceAll(" ", "");
		String Url_final=url+"?"+zuizhong;
		apiGetCall(Url_final, key);
	}
	//获取订单列表
	public void GetOrderList(int key, String token,String userId,String OrderCode,String Type,String Pagenum) {
		String url = "technicians/orderList.php";
		ArrayList<String> mStringList = new ArrayList<String>();
		mStringList.add("token" + "=" + token + "&");
		mStringList.add("userId" + "=" + userId + "&");
		if(!TextUtils.isEmpty(OrderCode)){
			mStringList.add("OrderCode" + "=" + OrderCode + "&");
		}
		if(!TextUtils.isEmpty(Type)) {
			mStringList.add("Type" + "=" + Type + "&");
		}
		if(!TextUtils.isEmpty(Pagenum)&&!"0".equals(Pagenum)) {
			mStringList.add("Pagenum" + "=" + Pagenum + "&");
		}
		String zuizhong = mStringList.toString()
				.substring(1, mStringList.toString().length() - 2)
				.replace(",", "").replaceAll(" ", "");
		String Url_final=url+"?"+zuizhong;
		apiGetCall(Url_final, key);
	}
	//获取收益信息
	public void GetIncome_Statement(int key, String token,String userId) {
		String url = "technicians/income.php";
		ArrayList<String> mStringList = new ArrayList<String>();
		mStringList.add("token" + "=" + token + "&");
		mStringList.add("userId" + "=" + userId + "&");
		String zuizhong = mStringList.toString()
				.substring(1, mStringList.toString().length() - 2)
				.replace(",", "").replaceAll(" ", "");
		String Url_final=url+"?"+zuizhong;
		apiGetCall(Url_final, key);
	}
	//修改技师头像
	public void ModHeadPic(int key, String PicUrl,String token,String userId) {
		String url = "technicians/uploadseva.php";
		RequestParams params = new RequestParams();
		params.addBodyParameter("PicUrl", PicUrl);
		params.addBodyParameter("token", token);
		params.addBodyParameter("userId", userId);
		apiPostCall(url, params, key);
	}
	//获取收益明细
	public void GetInComeList(int key, String token,String userId,String History,String Pagenum) {
		String url = "technicians/incomeList.php";
		ArrayList<String> mStringList = new ArrayList<String>();
		mStringList.add("token" + "=" + token + "&");
		mStringList.add("userId" + "=" + userId + "&");
		if(!TextUtils.isEmpty(History)){
			mStringList.add("History" + "=" + History + "&");
		}
		if(!TextUtils.isEmpty(Pagenum)){
			mStringList.add("Pagenum" + "=" + Pagenum + "&");
		}
		String zuizhong = mStringList.toString()
				.substring(1, mStringList.toString().length() - 2)
				.replace(",", "").replaceAll(" ", "");
		String Url_final=url+"?"+zuizhong;
		apiGetCall(Url_final, key);
	}
	//获取订单详情
	public void GetOrderDetails(int key, String token,String userId,String orderCode) {
		String url = "technicians/orderInfo.php";
		ArrayList<String> mStringList = new ArrayList<String>();
		mStringList.add("token" + "=" + token + "&");
		mStringList.add("userId" + "=" + userId + "&");
		mStringList.add("orderCode" + "=" + orderCode + "&");
		String zuizhong = mStringList.toString()
				.substring(1, mStringList.toString().length() - 2)
				.replace(",", "").replaceAll(" ", "");
		String Url_final=url+"?"+zuizhong;
		apiGetCall(Url_final, key);
	}
	//确认服务
	public void SureServer(int key, String Order,String token,String userId) {
		String url = "technicians/orderConfirm.php";
		RequestParams params = new RequestParams();
		params.addBodyParameter("Order", Order);
		params.addBodyParameter("token", token);
		params.addBodyParameter("userId", userId);
		apiPostCall(url, params, key);
	}
	//-------------------------Start2016.1.7----------------------------------//
}