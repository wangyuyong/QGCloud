package com.wyy.qgcloud.ui.personalMsg;

import android.content.Context;

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
    public Observable<ChangePasswordInfo> getChangeMsgInfo(Context context, int userId, String email, File icon, String phone, String groupName) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), icon);
        builder.addFormDataPart("userId", userId+"");
        builder.addFormDataPart("email", email);
        builder.addFormDataPart("icon", icon.getName(), body);
        builder.addFormDataPart("phone", phone);
        builder.addFormDataPart("groupName", groupName);
        List<MultipartBody.Part> parts = builder.build().parts();
        return RetrofitManager.getInstance()
                .getHttpService()
                .getChangeMsgInfo(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
