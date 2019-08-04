package com.wyy.qgcloud.ui.clouddisk.AuthorityLimit;

import android.content.Context;

import com.wyy.qgcloud.enity.ChangePasswordInfo;
import com.wyy.qgcloud.enity.GroupInfo;
import com.wyy.qgcloud.net.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AuthorityModel implements AuthorityContract.AuthorityModel {
    @Override
    public Observable<ChangePasswordInfo> getUpdateAuthorityInfo(Context context, int userId, int fileId, int authority, int told) {
        return RetrofitManager.getInstance()
                .getHttpService()
                .getUpdateAuthorityInfo(userId, fileId, authority, told)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<GroupInfo> getGroupInfo() {
        return RetrofitManager.getInstance()
                .getHttpService()
                .getGroupInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
