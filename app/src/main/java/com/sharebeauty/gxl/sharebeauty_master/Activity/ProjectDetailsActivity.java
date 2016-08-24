package com.sharebeauty.gxl.sharebeauty_master.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.MyPageAdapter;
import com.sharebeauty.gxl.sharebeauty_master.BaseActivity;
import com.sharebeauty.gxl.sharebeauty_master.CustomView.LPagePointView;
import com.sharebeauty.gxl.sharebeauty_master.CustomView.ObservableScrollView;
import com.sharebeauty.gxl.sharebeauty_master.Fragment.IndexHeadFragment;
import com.sharebeauty.gxl.sharebeauty_master.R;
import com.sharebeauty.gxl.sharebeauty_master.Utils.XLog;

import java.util.ArrayList;
import java.util.List;

public class ProjectDetailsActivity extends BaseActivity implements View.OnClickListener,ObservableScrollView.ScrollViewListener {
    @ViewInject(R.id.title)private TextView title;
    @ViewInject(R.id.left_arrow)private ImageView left_arrow;
    @ViewInject(R.id.right_img)private ImageView right_img;
    @ViewInject(R.id.details_webview)private WebView details_webview;
    @ViewInject(R.id.details_viewpager)private ViewPager details_viewpager;
    @ViewInject(R.id.pointview)private LPagePointView pointview;
    @ViewInject(R.id.appointment_Box)private LinearLayout appointment_Box;
    @ViewInject(R.id.ProjectDetails_Box)private ObservableScrollView ProjectDetails_Box;
    @ViewInject(R.id.topbar)private RelativeLayout topbar;

    //导航栏高度
    private float listHeaderHeight;

    private List<Fragment> Fragment_list = new ArrayList<Fragment>();
    private MyPageAdapter Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        ViewUtils.inject(this);
        InitData();
        InitFragMent();
        InitView();
        //设置webview的属性
        setWebViewAtt();
        setViewPagerAtt();
    }

    private void InitData() {
        listHeaderHeight=(int) getResources().getDimension(R.dimen.topbar_height);
    }

    //创建FragMnet
    private void InitFragMent(){
        Fragment_list.clear();
            for (int i = 0; i < 5; i++) {
                IndexHeadFragment Fragment=IndexHeadFragment.MyFragment();
                Fragment_list.add(Fragment);
            }
    }
    private void InitView() {
        title.setText("项目名称");
        right_img.setVisibility(View.INVISIBLE);
        left_arrow.setOnClickListener(this);
        appointment_Box.setOnClickListener(this);
        ProjectDetails_Box.setScrollViewListener(this);
    }
    private void setViewPagerAtt(){
        Adapter=new MyPageAdapter(getSupportFragmentManager(),Fragment_list);
        details_viewpager.setAdapter(Adapter);
        pointview.setPointSize(Fragment_list.size());
        pointview.setPointColor(getResources().getColor(R.color.base_color_1));
        details_viewpager.setOnPageChangeListener(changeListener);
    }

    private ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int arg0) {}

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            pointview.onChange(arg0, arg1);
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {}
    };
    private void setWebViewAtt(){
        //优先使用缓存
        details_webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //WebView加载web资源
        details_webview.loadUrl("https://weixin.qq.com/cgi-bin/readtemplate?t=weixin");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        details_webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //如果访问的页面中有Javascript，则webview必须设置支持Javascript
        WebSettings settings = details_webview.getSettings();
        settings.setJavaScriptEnabled(true);
        //判断页面加载过程
        details_webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                    releaseScreen();
                } else {
                    // 加载中
                    lockScreen(true);
                }

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_arrow:
                finish();
                break;
            case R.id.appointment_Box:
                startActivity(new Intent(ProjectDetailsActivity.this,AppointmentActivity.class));
                break;
        }
    }

    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(details_webview.canGoBack())
            {
                details_webview.goBack();//返回上一页面
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if(Math.abs(y)<=listHeaderHeight){
            float NowY=Math.abs(y);
            float progress=NowY/listHeaderHeight;
            XLog.e("Scroll","progress="+progress+"---listHeaderHeight="+listHeaderHeight+"---Math.abs(y)="+Math.abs(y));
            setTopBarHeight(listHeaderHeight * (1-progress));
            setAnim(1 - progress);
        }else{
            setTopBarHeight(0);
            setAnim(0);
        }

    }
    private void setTopBarHeight(float height){
        ViewGroup.LayoutParams lp;
        lp=topbar.getLayoutParams();
        lp.height= (int)height;
        topbar.setLayoutParams(lp);
    }
    private void setAnim(float progress){
        title.setScaleX(progress);
        title.setScaleY(progress);
        left_arrow.setScaleX(progress);
        left_arrow.setScaleY(progress);
        right_img.setScaleX(progress);
        right_img.setScaleY(progress);
    }
}
