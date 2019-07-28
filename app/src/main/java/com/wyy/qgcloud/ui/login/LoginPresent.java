package com.wyy.qgcloud.ui.login;

import android.content.Context;
import android.content.Intent;

import com.wyy.qgcloud.enity.EmailInfo;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.homePage.HomePageActivity;

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
    public void getEmailInfo(Context context, final String email) {
        loginModel.getEmailInfo(context, email).subscribe(new Consumer<EmailInfo>() {
            @Override
            public void accept(EmailInfo emailInfo) throws Exception {
                //根据返回数据在view层操作
                if(!emailInfo.getStatus()){
                    //邮箱不存在
                    loginView.showError(emailInfo.getMessage(), 1);
                }
                //邮箱存在不做提示
            }
        });
    }

    @Override
    public void getLoginInfo(final Context context, String email, String password) {
        loginModel.getLoginInfo(context, email, password).subscribe(new Consumer<LoginInfo>() {
            @Override
            public void accept(LoginInfo loginInfo) throws Exception {
                //根据返回数据在view层操作
                if(loginInfo.getStatus()){
                    //登录成功
                    loginView.showSuccess("登录成功!");
                    Intent intent = new Intent(context, HomePageActivity.class);
                    intent.putExtra("data", loginInfo.getData());
                }else{
                    //登录失败，弹窗提示
                    loginView.showError(loginInfo.getMessage(),2);
                }

            }
        });
    }
}
