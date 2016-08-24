package com.sharebeauty.gxl.sharebeauty_master;

import android.app.Activity;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sharebeauty.gxl.sharebeauty_master.MainFragment.IndexFragment;
import com.sharebeauty.gxl.sharebeauty_master.MainFragment.SetFragment;
import com.sharebeauty.gxl.sharebeauty_master.MainFragment.ShareFragment;
import com.sharebeauty.gxl.sharebeauty_master.MainFragment.ShopFragment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends BaseActivity {
    @ViewInject(R.id.home_index)private RelativeLayout home_index;
    @ViewInject(R.id.home_share)private RelativeLayout home_share;
    @ViewInject(R.id.home_set)private RelativeLayout home_set;
    @ViewInject(R.id.home_shop)private RelativeLayout home_shop;
    private int index = 0;
    private int currentTabIndex = 0;
    private Fragment[] fragments;
    private RelativeLayout[] mTabs;

    private IndexFragment mIndexFragment;
    private SetFragment mSetFragment;
    private ShareFragment mShareFragment;
    private ShopFragment mShopFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        init();
    }

    private void init() {
        mTabs = new RelativeLayout[]{home_index,home_share,home_shop,home_set};
        mTabs[0].setSelected(true);
        mShareFragment = new ShareFragment();
        mIndexFragment = new IndexFragment();
        mShopFragment = new ShopFragment();
        mSetFragment = new SetFragment();
        fragments = new Fragment[]{mIndexFragment,mShareFragment,mShopFragment,mSetFragment};
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.homepager_about,fragments[0]).show(fragments[0]);
        beginTransaction.add(R.id.homepager_about,fragments[1]).hide(fragments[1]);
        beginTransaction.add(R.id.homepager_about,fragments[2]).hide(fragments[2]);
        beginTransaction.add(R.id.homepager_about,fragments[3]).hide(fragments[3]);
        beginTransaction.commit();
    }
    public void onTabClicked(View view) {
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.home_index:
                index = 0;
                if(index<currentTabIndex){
                    trx.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
                }
                break;
            case R.id.home_share:
                index = 1;
                if(index<currentTabIndex){
                    trx.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
                }else{
                    trx.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
                }
                break;
            case R.id.home_shop:
                index = 2;
                if(index<currentTabIndex){
                    trx.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
                }else{
                    trx.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
                }
                break;
            case R.id.home_set:
                index = 3;
                if(index<currentTabIndex){
                    trx.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
                }else{
                    trx.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
                }
                break;
        }
        if (currentTabIndex != index) {
            trx.hide(fragments[currentTabIndex]);
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    private AnimatedVectorDrawable loadVectorDrawable(int resourceId) {
        return (AnimatedVectorDrawable) getResources().getDrawable(resourceId);
    }
}
