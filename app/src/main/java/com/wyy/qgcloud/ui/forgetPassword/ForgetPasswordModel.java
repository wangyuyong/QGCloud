package com.wyy.qgcloud.ui.forgetPassword;

import android.content.Context;

import com.wyy.qgcloud.enity.GetEmailCodeInfo;
import com.wyy.qgcloud.enity.SetNewPasswordInfo;
import com.wyy.qgcloud.net.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ForgetPasswordModel implements ForgetPasswordContract.ForgetPasswordModel {
    @Override
    public Observable<GetEmailCodeInfo> getEmailCodeInfo(Context context, String email) {
        return RetrofitManager.getInstance()
                .getHttpService()
                .getEmailCodeInfo(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<SetNewPasswordInfo> getNewPasswordInfo(Context context, String code, String email, String password) {
        return RetrofitManager.getInstance()
                .getHttpService()
                .getNewPasswordInfo(code, email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
