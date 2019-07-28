package com.wyy.qgcloud.ui.login;

import android.content.Context;

import com.wyy.qgcloud.enity.EmailInfo;
import com.wyy.qgcloud.enity.LoginInfo;

import io.reactivex.functions.Consumer;

public class LoginPresent implements LoginContract.LoginPresent {

    private LoginContract.LoginModel loginModel;
    private LoginContract.LoginView loginView;

    @Override
    public void bindView(LoginContract.LoginView view) {
        this.loginView = view;
        loginModel = new LoginModel();
    }

    @Override
    public void unbindView() {
        loginModel = null;
        loginView = null;
    }

    @Override
    public void getEmailInfo(Context context, String email) {
        loginModel.getEmailInfo(context, email).subscribe(new Consumer<EmailInfo>() {
            @Override
            public void accept(EmailInfo emailInfo) throws Exception {
                //根据返回数据在view层操作
            }
        });
    }

    @Override
    public void getLoginInfo(Context context, String email, String password) {
        loginModel.getLoginInfo(context, email, password).subscribe(new Consumer<LoginInfo>() {
            @Override
            public void accept(LoginInfo loginInfo) throws Exception {
                //根据返回数据在view层操作
            }
        });
    }
}
