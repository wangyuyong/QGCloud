package com.wyy.qgcloud.ui.login;

import android.content.Context;
import android.content.Intent;

import com.wyy.qgcloud.enity.EmailInfo;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.changePassword.ChangePasswordActivity;
import com.wyy.qgcloud.ui.homePage.HomePageActivity;
import com.wyy.qgcloud.ui.personalMsg.PersonalMsgActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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
        loginModel.getEmailInfo(context, email).subscribe(new Observer<EmailInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EmailInfo emailInfo) {
                //根据返回数据在view层操作
                if(!emailInfo.getStatus()){
                    //邮箱不存在
                    loginView.showError(emailInfo.getMessage(), 1);
                }
                //邮箱存在不做提示
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getLoginInfo(final Context context, String email, String password) {
        loginModel.getLoginInfo(context, email, password).subscribe(new Observer<LoginInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginInfo loginInfo) {
                //根据返回数据在view层操作
                if(loginInfo.getStatus()){
                    //登录成功
                    loginView.showSuccess("登录成功!");
                    Intent intent = new Intent(context, PersonalMsgActivity.class);
                    intent.putExtra("data", loginInfo.getData());
                    context.startActivity(intent);
                    LoginActivity.mLoginActivity.finish();
                }else{
                    //登录失败，弹窗提示
                    loginView.showError(loginInfo.getMessage(),2);
                }

            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
