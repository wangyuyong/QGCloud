package com.wyy.qgcloud.ui.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.wyy.qgcloud.enity.RegisterInfo;
import com.wyy.qgcloud.enity.ValidateCodeInfo;
import com.wyy.qgcloud.ui.homePage.HomePageActivity;
import com.wyy.qgcloud.ui.login.LoginActivity;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegisterPresent implements RegisterContract.RegisterPresent {

    private RegisterContract.RegisterView registerView;
    private RegisterContract.RegisterModel registerModel;

    @Override
    public void bindView(RegisterContract.RegisterView view) {
        this.registerView = view;
        registerModel = new RegisterModel();
    }

    @Override
    public void unbindView() {
        registerView = null;
        registerModel = null;
    }

    @Override
    public void getValidateCodeInfo(Context context) {
        registerModel.getValidateCodeInfo(context).subscribe(new Observer<ValidateCodeInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ValidateCodeInfo validateCodeInfo) {
                registerView.displayCode(validateCodeInfo.getImage());
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
    public void getRegisterInfo(final Context context, String email, String password, File icon, String userName, String phone, String code) {

        registerModel.getRegisterInfo(context, email, password, icon, userName, phone, code).subscribe(new Observer<RegisterInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegisterInfo registerInfo) {
                //对用户进行相关提示
                if(registerInfo.getStatus()){
                    //注册成功
                    registerView.showSuccess("注册成功！请登录。");
                    RegisterActivity.mRegisterActivity.finish();
                }else{
                    //注册失败
                    registerView.showError(registerInfo.getMessage(),2);
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
