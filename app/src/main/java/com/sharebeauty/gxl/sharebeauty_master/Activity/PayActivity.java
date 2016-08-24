package com.sharebeauty.gxl.sharebeauty_master.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sharebeauty.gxl.sharebeauty_master.BaseActivity;
import com.sharebeauty.gxl.sharebeauty_master.R;

/**
 * Created by Administrator on 2016/8/2.
 */
public class PayActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.title)private TextView title;
    @ViewInject(R.id.left_arrow)private ImageView left_arrow;
    @ViewInject(R.id.right_img)private ImageView right_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ViewUtils.inject(this);
        InitView();
    }
    private void InitView() {
        title.setText("选择支付方式");
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
