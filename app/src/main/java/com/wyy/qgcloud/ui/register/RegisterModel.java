package com.wyy.qgcloud.ui.register;

import android.content.Context;
import android.graphics.Bitmap;

import com.wyy.qgcloud.enity.RegisterInfo;
import com.wyy.qgcloud.enity.ValidateCodeInfo;
import com.wyy.qgcloud.net.RetrofitManager;
import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegisterModel implements RegisterContract.RegisterModel{


    @Override
    public Observable<ValidateCodeInfo> getValidateCodeInfo(Context context) {
        return RetrofitManager.getInstance()
                .getHttpService()
                .getValidateCodeInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<RegisterInfo> getRegisterInfo(Context context, String email, String password, File head, String userName, String phone, String code) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"),head);

        builder.addFormDataPart("email",email);
        builder.addFormDataPart("password",password);
        builder.addFormDataPart("userName",userName);
        builder.addFormDataPart("phone",phone);
        builder.addFormDataPart("code",code);
        builder.addFormDataPart("head",head.getName(),body);
        List<MultipartBody.Part> parts = builder.build().parts();
        return RetrofitManager.getInstance()
                .getHttpService()
                .getRegisterInfo(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
