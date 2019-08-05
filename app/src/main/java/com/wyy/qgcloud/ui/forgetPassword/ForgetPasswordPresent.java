package com.wyy.qgcloud.ui.forgetPassword;

import android.content.Context;

import com.wyy.qgcloud.enity.GetEmailCodeInfo;
import com.wyy.qgcloud.enity.SetNewPasswordInfo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ForgetPasswordPresent implements ForgetPasswordContract.ForgetPasswordPresent{

    private ForgetPasswordContract.ForgetPasswordModel forgetPasswordModel;
    private ForgetPasswordContract.ForgetPasswordView forgetPasswordView;
    @Override
    public void bindView(ForgetPasswordContract.ForgetPasswordView view) {
        this.forgetPasswordView = view;
        forgetPasswordModel = new ForgetPasswordModel();
    }

    @Override
    public void unbindView() {
        forgetPasswordModel = null;
        forgetPasswordView = null;
    }

    @Override
    public void getEmailCodeInfo(Context context, String email) {
        forgetPasswordModel.getEmailCodeInfo(context, email).subscribe(new Observer<GetEmailCodeInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GetEmailCodeInfo getEmailCodeInfo) {
                if(getEmailCodeInfo.getStatus()){
                    forgetPasswordView.showSuccess("验证码发送成功！");
                }else {
                    forgetPasswordView.showError(getEmailCodeInfo.getMessage());
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

    @Override
    public void getNewPasswordInfo(Context context, String code, String email, String password) {
        forgetPasswordModel.getNewPasswordInfo(context, code,email, password).subscribe(new Observer<SetNewPasswordInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SetNewPasswordInfo setNewPasswordInfo) {
                if(setNewPasswordInfo.getStatus()){
                    forgetPasswordView.showSuccess("新密码设置成功！请登录。");
                    ForgetPasswordActivity.mForgetPasswordActivity.finish();
                }else {
                    forgetPasswordView.showError(setNewPasswordInfo.getMessage());
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
