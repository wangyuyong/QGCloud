package com.wyy.qgcloud.ui.personalMsg;

import android.content.Context;

import com.wyy.qgcloud.enity.ChangePasswordInfo;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PersonalMsgPresent implements PersonalMsgContract.PersonalMsgPresent {

    private PersonalMsgContract.PersonalMsgModel personalMsgModel;
    private PersonalMsgContract.PersonalMsgView personalMsgView;

    @Override
    public void bindView(PersonalMsgContract.PersonalMsgView view) {
        this.personalMsgView = view;
        personalMsgModel = new PersonalMsgModel();
    }

    @Override
    public void unbindView() {
        personalMsgModel = null;
        personalMsgView = null;
    }

    @Override
    public void getChangeMsgInfo(Context context, int userId, String email, File icon, String phone, String groupName) {
        personalMsgModel.getChangeMsgInfo(context, userId, email, icon, phone, groupName).subscribe(new Observer<ChangePasswordInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ChangePasswordInfo changePasswordInfo) {
                if(changePasswordInfo.getStatus()){
                    personalMsgView.showSuccess("修改成功！");
                }else {
                    personalMsgView.showError(changePasswordInfo.getMessage());
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
