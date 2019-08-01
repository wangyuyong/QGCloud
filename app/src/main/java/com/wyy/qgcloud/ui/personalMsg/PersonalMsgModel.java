package com.wyy.qgcloud.ui.personalMsg;

import android.content.Context;
import android.util.Log;

import com.wyy.qgcloud.enity.ChangePasswordInfo;
import com.wyy.qgcloud.net.RetrofitManager;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PersonalMsgModel implements PersonalMsgContract.PersonalMsgModel {
    @Override
    public Observable<ChangePasswordInfo> getChangeMsgInfo(Context context, int userId, String email, String phone) {
        return RetrofitManager.getInstance()
                .getHttpService()
                .getChangeMsgInfo(userId, email, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
