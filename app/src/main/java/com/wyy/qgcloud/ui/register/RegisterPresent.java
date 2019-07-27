package com.wyy.qgcloud.ui.register;

import android.content.Context;

import com.wyy.qgcloud.enity.RegisterInfo;
import com.wyy.qgcloud.enity.ValidateCodeInfo;

import java.io.File;

import io.reactivex.functions.Consumer;

public class RegisterPresent implements RegisterContract.RegisterPresent {

    private RegisterContract.RegisterView registerView;
    private RegisterContract.RegisterModel registerModel;

    @Override
    public void bindView(RegisterContract.RegisterView view) {

    }

    @Override
    public void unbindView() {

    }

    @Override
    public void getValidateCodeInfo(Context context) {
        registerModel.getValidateCodeInfo(context).subscribe(new Consumer<ValidateCodeInfo>() {
            @Override
            public void accept(ValidateCodeInfo validateCodeInfo) throws Exception {

            }
        });
    }

    @Override
    public void getRegisterInfo(Context context, String email, String password, File icon, String userName, String phone, String code) {
        registerModel.getRegisterInfo(context, email, password, icon, userName, phone, code).subscribe(new Consumer<RegisterInfo>() {
            @Override
            public void accept(RegisterInfo registerInfo) throws Exception {

            }
        });
    }
}
