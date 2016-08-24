package com.sharebeauty.gxl.sharebeauty_master.CustomView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sharebeauty.gxl.sharebeauty_master.R;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/7/28.
 */
public class NumView extends LinearLayout {

    private ImageView mIvAdd;
    private ImageView mIvReduce;
    private TextView mTvNum;
    // 不要设置成静态的，每一个NumView，都应该有一个最大值和最小值；
    private int mMaxNum;
    private int mMinNum;

    // 是否循环
    private boolean mCycleable;

    public NumView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public NumView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumView(Context context) {
        super(context, null);
    }

    public int getMaxNum() {
        return mMaxNum;
    }

    public void setMaxNum(int maxNum) {
        mMaxNum = maxNum;
    }

    public int getMinNum() {
        return mMinNum;
    }

    public void setMinNum(int minNum) {
        mMinNum = minNum;
    }

    public boolean isCycleable() {
        return mCycleable;
    }

    public void setCycleable(boolean cycleable) {
        mCycleable = cycleable;
    }

    private void initData() {
        // initial
        init(0, 0, 0, false);
    }

    /**
     *
     * @param minNum the min number in the field;
     * @param maxNum the max number in the field;
     * @param defaultNum the default number to show;
     * @param cycleable is or not cycle;
     */
    public void init(int minNum, int maxNum, int defaultNum, boolean cycleable) {
        setMinNum(minNum);
        setMaxNum(maxNum);
        setCycleable(cycleable);

        setNum(defaultNum);
    }

    private void initView(Context context) {
        initData();
        View view = View.inflate(context, R.layout.view_num, this);

        mIvAdd = (ImageView) view.findViewById(R.id.iv_num_add);
        mIvReduce = (ImageView) view.findViewById(R.id.iv_num_reduce);
        mTvNum = (TextView) view.findViewById(R.id.tv_num);

        mIvAdd.setOnClickListener(listener);
        mIvReduce.setOnClickListener(listener);
        autoChangeWithLongClick(mIvAdd, mIvReduce);
    }


    private void autoChangeWithLongClick(ImageView... ivs) {
        for (ImageView iv : ivs) {
            iv.setOnLongClickListener(mLongClickListener);
            iv.setOnTouchListener(mTouchListener);
        }
    }

    // 自动变化的 Task；
    private Handler mHandler = new Handler();
    private static final int DELAY_TIME = 160;
    private int mUnit = 1;
    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            setNum(getNum() + mUnit);
            mHandler.postDelayed(mTask, DELAY_TIME);
        }
    };

    // 长按开启自动变化；
    private boolean mLongClicked;
    OnLongClickListener mLongClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            mLongClicked = true;
            mHandler.postDelayed(mTask, DELAY_TIME);
            return mLongClicked;
        }

    };

    // 实现：非长按状态下拖动一定距离开启自动变化；
    private float mYLast;
    private boolean mChanging;
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float y = ev.getY();
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mYLast = y;
                break;
            case MotionEvent.ACTION_UP:
                mChanging = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float delta = Math.abs(y - mYLast);
                if(delta > 100 && !mChanging && !mLongClicked){
                    mHandler.postDelayed(mTask, DELAY_TIME);
                    mChanging = true;
                    return true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    };


    @SuppressLint("ClickableViewAccessibility")
    View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setAlpha(0.2f);
                    v.setBackgroundColor(0x88ffffff);
                    if (v.getId() == R.id.iv_num_reduce) {
                        mUnit = -1 * Math.abs(mUnit);
                    } else if (v.getId() == R.id.iv_num_add) {
                        mUnit = Math.abs(mUnit);
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    // alpha animation
                    v.setBackgroundResource(0);
                    v.animate().alpha(1f).setDuration(200).start();

                    // recover
                    mLongClicked = false;
                    mHandler.removeCallbacks(mTask);
                    break;
            }
            return false;
        }
    };

    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int num = getNum();

            switch(v.getId()){
                case R.id.iv_num_add:
                    num = num+1;
                    break;

                case R.id.iv_num_reduce:
                    num = num-1;
                    break;
            }

            setNum(num);
        }
    };

    public void setNum(int num){
        // default pattern:00
        setNum(num, "00");
    }

    public void setNum(int num, String pattern){
        if(num > mMaxNum){
            if(mCycleable){
                num = mMinNum;
            } else {
                num = num -1;
            }
        }

        if(num < mMinNum){
            if(mCycleable){
                num = mMaxNum;
            } else {
                num = num+1;
            }
        }

        String str = getFormatNumWithPattern(num, pattern);

        if(mTvNum != null && str!=null){
            mTvNum.setText(str);
        }
    }

    public int getNum(){
        return Integer.valueOf(mTvNum.getText().toString());
    }

    public static String getFormatNumWithPattern(Object object, String pattern){
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(object);
    }
}
