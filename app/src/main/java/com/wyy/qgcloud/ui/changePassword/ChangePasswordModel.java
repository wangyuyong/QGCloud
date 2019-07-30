package com.wyy.qgcloud.ui.changePassword;

import android.content.Context;

import com.wyy.qgcloud.enity.ChangePasswordInfo;
import com.wyy.qgcloud.net.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChangePasswordModel implements ChangePasswordContract.ChangePasswordModel {

    @Override
    public Observable<ChangePasswordInfo> getChangePasswordInfo(Context context, int userId, String oldPassword, String newPassword) {
        return RetrofitManager.getInstance()
                .getHttpService()
                .getChangePasswordInfo(userId, oldPassword,newPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
