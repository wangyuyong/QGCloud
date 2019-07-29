package com.wyy.qgcloud.util;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.wyy.qgcloud.R;

import java.lang.ref.WeakReference;

/**
 * 验证码倒计时工具类
 */

public class CountDownUtil {

    private final int MSG_WHAT_START = 1;           //开始倒计时的标记
    private WeakReference<TextView> mWeakReference;  //弱引用
    private long mCountDownMillis = 60000;           //倒计时总时间
    private String mHintText = "重新获取验证码";    //提示文字
    private long mLastMillis;                       //倒计时剩余时间
    private long mIntervalMillis = 1000;              //文字改变间隔的时间
    private int usableTextColorId = R.color.colorTextWhite;   //可用状态下字体颜色
    private int usableBackgroundColorId = R.color.colorBtn;   //可用状态下背景颜色
    private int unusableTextColorId = R.color.colorBtn;                //不可用状态下字体颜色
    private int unusableBackgroundColorId = R.color.colorTextWhite;   //不可用状态下背景颜色

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_WHAT_START:
                    if(mLastMillis > 0){
                        setUsable(false);
                        mLastMillis -= mIntervalMillis;
                        if(mWeakReference.get()!=null){
                            mHandler.sendEmptyMessageDelayed(MSG_WHAT_START, mIntervalMillis);
                        }
                    }else {
                        setUsable(true);
                    }
                    break;
            }
        }
    };

    public CountDownUtil(TextView textView) {
        mWeakReference = new WeakReference<>(textView);
    }

    public CountDownUtil(TextView textView, long countDownMillis) {
        mWeakReference = new WeakReference<>(textView);
        this.mCountDownMillis = countDownMillis;
    }

    public CountDownUtil setCountDownMillis(long countDownMillis) {
        this.mCountDownMillis = countDownMillis;
        return this;
    }

    //设置颜色
    public CountDownUtil setCountDownColor(@ColorRes int usableTextColorId,@ColorRes int usableBackgroundColorId,@ColorRes int unusableTextColorId,@ColorRes int unusableBackgroundColorId) {
        this.usableTextColorId = usableTextColorId;
        this.usableBackgroundColorId = usableBackgroundColorId;
        this.unusableTextColorId = unusableTextColorId;
        this.unusableBackgroundColorId = unusableBackgroundColorId;
        return this;
    }

    //开始倒计时
    public CountDownUtil start() {
        mLastMillis = mCountDownMillis;
        mHandler.sendEmptyMessage(MSG_WHAT_START);
        return this;
    }

    public CountDownUtil setOnClickListener(@Nullable final View.OnClickListener onClickListener){
        TextView textView = mWeakReference.get();
        if(textView != null){
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHandler.removeMessages(MSG_WHAT_START);
                    start();
                    onClickListener.onClick(v);
                }
            });
        }
        return this;
    }


    //重置倒计时
    public CountDownUtil reset(){
        mLastMillis = 0;
        mHandler.sendEmptyMessage(MSG_WHAT_START);
        return this;
    }


    //设置是否可用
    private void setUsable(boolean usable){
        TextView textView = mWeakReference.get();
        if(textView != null){
            if (usable) {
                if(!textView.isClickable()){
                    textView.setClickable(true);
                    textView.setTextColor(textView.getResources().getColor(usableTextColorId));
                    textView.setBackgroundColor(textView.getResources().getColor(usableBackgroundColorId));
                    textView.setText(mHintText);
                }
            }else {
                if(textView.isClickable()){
                    textView.setClickable(false);
                    textView.setTextColor(textView.getResources().getColor(unusableTextColorId));
                    textView.setBackgroundColor(textView.getResources().getColor(unusableBackgroundColorId));
                }
                String content = mLastMillis/1000 + "秒后重新获取";
                textView.setText(content);
            }
        }
    }
}
