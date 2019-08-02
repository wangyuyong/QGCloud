package com.wyy.qgcloud.ui.addressList;

import android.content.Context;
import android.util.Log;

import com.wyy.qgcloud.enity.GroupInfo;
import com.wyy.qgcloud.enity.MemberChangeInfo;
import com.wyy.qgcloud.net.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class AddressListModel implements AddressListContract.AddressListModel {

    @Override
    public Observable<GroupInfo> getGroupInfo() {
        Log.d("wx","111");
        return RetrofitManager.getInstance()
                .getHttpService()
                .getGroupInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MemberChangeInfo> getJoinGroupInfo(Context context, int userId, int toUserId, int groupId) {
        return RetrofitManager.getInstance()
                .getHttpService()
                .getJoinGroupInfo(userId, toUserId, groupId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MemberChangeInfo> getUserPositionInfo(Context context, int userId, int toUserId, int role) {
        return RetrofitManager.getInstance()
                .getHttpService()
                .getUserPositionInfo(userId, toUserId, role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
