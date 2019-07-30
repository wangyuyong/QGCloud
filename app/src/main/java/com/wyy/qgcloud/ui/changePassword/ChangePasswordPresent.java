package com.wyy.qgcloud.ui.changePassword;

import android.content.Context;

import com.wyy.qgcloud.enity.ChangePasswordInfo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ChangePasswordPresent implements ChangePasswordContract.ChangePasswordPresent {

    private ChangePasswordContract.ChangePasswordModel changePasswordModel;
    private ChangePasswordContract.ChangePasswordView changePasswordView;
    @Override
    public void bindView(ChangePasswordContract.ChangePasswordView view) {
        this.changePasswordView = view;
        changePasswordModel = new ChangePasswordModel();
    }

    @Override
    public void unbindView() {
        changePasswordModel = null;
        changePasswordView = null;
    }

    @Override
    public void getChangePasswordInfo(Context context, int userId, String oldPassword, String newPassword) {
        changePasswordModel.getChangePasswordInfo(context, userId, oldPassword, newPassword).subscribe(new Observer<ChangePasswordInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ChangePasswordInfo changePasswordInfo) {
                if(changePasswordInfo.getStatus()){
                    changePasswordView.showSuccess("修改成功！");
                }else{
                    changePasswordView.showError(changePasswordInfo.getMessage());
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
