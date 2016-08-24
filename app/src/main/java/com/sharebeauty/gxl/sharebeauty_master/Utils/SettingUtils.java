package com.sharebeauty.gxl.sharebeauty_master.Utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

public class SettingUtils {
	//三方登陆标签
	public static final String LOGIN_FLAG="flag";
	//存储的文件名
	private static final String SETTING_PREF = "Shared_pref";

	//QQ三方登陆存储的数值
	public static final String APP_ID = "appid";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String OPEN_ID = "openid";
	//微博三方登陆存储的数值
	public static final String WEIBO_UID = "weibo_uid";
	public static final String WEIBO_ACCESS_TOKEN = "weibo_access_token";
	public static final String WEIBO_EXPIRES_IN = "weibo_expires_in";
	public static final String WEIBO_REFRESH_TOKEN = "weibo_refresh_token";
	public static final String WEIBO_PHONE_NUM = "weibo_phone_num";

	private static SettingUtils utilInstance;
	private Context mContext;

	public SettingUtils(Context context) {
		this.mContext = context;
	}

	public static SettingUtils getInstance(Context context) {
		if (utilInstance == null) {
			utilInstance = new SettingUtils(context);
		}
		return utilInstance;
	}

	public void saveValue(String key, int value) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public int getValue(String key, int defaultValue) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		return pref.getInt(key, defaultValue);
	}
	public void saveValue(String key, long value) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public long getValue(String key, long defaultValue) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		return pref.getLong(key, defaultValue);
	}

	public void saveValue(String key, String value) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String getValue(String key, String defaultValue) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		return pref.getString(key, defaultValue);
	}

	public void saveValue(String key, boolean value) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public boolean getValue(String key, boolean defaultValue) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		return pref.getBoolean(key, defaultValue);
	}
	
	/**
	 * 清除所有的SharedPreferences文件
	 */
	public void cleanAllRecord(){
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.clear();
		editor.commit();
	}
	//返回微博所需要的bundle来得到特定的ACCESSTOKEN
	public Bundle toWeiBoBundle() {
		Bundle bundle = new Bundle();
		bundle.putString("uid", getValue(WEIBO_UID,""));
		bundle.putString("access_token", getValue(WEIBO_ACCESS_TOKEN,""));
		bundle.putString("refresh_token", getValue(WEIBO_REFRESH_TOKEN,""));
		bundle.putString("expires_in", Long.toString(getValue(WEIBO_EXPIRES_IN,0L)));
		bundle.putString("phone_num", getValue(WEIBO_PHONE_NUM,""));
		return bundle;
	}
}
