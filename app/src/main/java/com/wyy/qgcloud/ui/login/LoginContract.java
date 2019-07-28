package com.wyy.qgcloud.ui.login;

import android.content.Context;
import android.widget.EditText;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;
import com.wyy.qgcloud.enity.EmailInfo;
import com.wyy.qgcloud.enity.LoginInfo;

import io.reactivex.Observable;


public interface LoginContract {
    interface LoginView extends BaseView {
        String getEdt(EditText editText);
    }

    interface LoginPresent extends BasePresent<LoginView> {
        void getEmailInfo(Context context, String email);
        void getLoginInfo(Context context, String email, String password);
    }

    interface LoginModel{
        Observable<EmailInfo> getEmailInfo(Context context, String email);
        Observable<LoginInfo> getLoginInfo(Context context, String email, String password);

    }
}
