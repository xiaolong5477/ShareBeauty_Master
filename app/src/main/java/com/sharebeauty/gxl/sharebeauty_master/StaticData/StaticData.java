package com.sharebeauty.gxl.sharebeauty_master.StaticData;


import android.app.AlertDialog;

/**
 * Created by yr on 2015/11/11.
 */
public class StaticData {
    public static final String DB_NAME="User_UID_";
    public static AlertDialog StateDialog;
    public static String[] SortText=new String[]{"评分低到高","价格由低到高","距离由进到远","评分高到低","价格由高到低","距离由远到进"};
    public static String[] ServerModel=new String[]{"全部","到店","上门"};
    public static String[] InComeModel=new String[]{"全部","本月","上月"};
    public static String[] OrderState=new String[]{"待确认","未完成","已完成"};
}
