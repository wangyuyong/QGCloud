package com.wyy.qgcloud.ui.register;

import android.content.Context;
import android.graphics.Bitmap;

import com.wyy.qgcloud.enity.RegisterInfo;
import com.wyy.qgcloud.enity.ValidateCodeInfo;
import com.wyy.qgcloud.net.RetrofitManager;
import java.io.File;
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
    public Observable<RegisterInfo> getRegisterInfo(Context context, String email, String password, File icon, String userName, String phone, String code) {
        RequestBody iconFile = RequestBody.create(MediaType.parse("multipart/form-data"), icon);
        MultipartBody.Part iconPart = MultipartBody.Part.createFormData("icon", icon.getName(), iconFile);
        return RetrofitManager.getInstance()
                .getHttpService()
                .getRegisterInfo(email, password, iconPart, userName, phone, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
