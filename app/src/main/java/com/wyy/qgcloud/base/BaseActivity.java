package com.wyy.qgcloud.base;

import android.content.Context;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        if(me.getAction() == MotionEvent.ACTION_DOWN){
            View v = getCurrentFocus();  //得到当前页面的焦点
            if(isShouldHideKeyboard(v,me)){
                hideKeyboard(v.getWindowToken());  //收起键盘
            }
        }
        return super.dispatchTouchEvent(me);
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event){
        if(v != null && (v instanceof EditText)){
            int[] l = {0,0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1];
            int bottom = top+v.getHeight();
            int right = left+v.getWidth();
            if(event.getX() > top && event.getY() < bottom){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }

    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
