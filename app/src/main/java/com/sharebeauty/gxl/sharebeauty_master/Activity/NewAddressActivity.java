package com.sharebeauty.gxl.sharebeauty_master.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sharebeauty.gxl.sharebeauty_master.Adapter.SelectAddressAdapter;
import com.sharebeauty.gxl.sharebeauty_master.BaseActivity;
import com.sharebeauty.gxl.sharebeauty_master.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/2.
 */
public class NewAddressActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.title)private TextView title;
    @ViewInject(R.id.left_arrow)private ImageView left_arrow;
    @ViewInject(R.id.right_img)private ImageView right_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        ViewUtils.inject(this);
        InitView();
    }
    private void InitView() {
        title.setText("新增收货地址");
        left_arrow.setOnClickListener(this);
        right_img.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_arrow:
                finish();
                break;
        }
    }
}
