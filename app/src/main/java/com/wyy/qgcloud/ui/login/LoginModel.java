package com.wyy.qgcloud.ui.login;

import android.content.Context;

import com.wyy.qgcloud.enity.EmailInfo;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.net.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements LoginContract.LoginModel{

    @Override
    public Observable<EmailInfo> getEmailInfo(Context context, String email) {
        return RetrofitManager.getInstance()
                .getHttpService()
                .getEmailInfo(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<LoginInfo> getLoginInfo(Context context, String email, String password) {
         return RetrofitManager.getInstance()
                .getHttpService()
                .getLoginInfo(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
