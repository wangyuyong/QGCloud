package com.wyy.qgcloud.ui.register;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.wyy.qgcloud.enity.RegisterInfo;
import com.wyy.qgcloud.enity.ValidateCodeInfo;
import com.wyy.qgcloud.ui.homePage.HomePageActivity;

import java.io.File;
import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;

public class RegisterPresent implements RegisterContract.RegisterPresent {

    private RegisterContract.RegisterView registerView;
    private RegisterContract.RegisterModel registerModel;

    @Override
    public void bindView(RegisterContract.RegisterView view) {
        this.registerView = view;
        registerModel = new RegisterModel();
    }

    @Override
    public void unbindView() {
        registerView = null;
        registerModel = null;
    }

    @Override
    public void getValidateCodeInfo(Context context) {
        registerModel.getValidateCodeInfo(context).subscribe(new Consumer<ValidateCodeInfo>() {
            @Override
            public void accept(ValidateCodeInfo validateCodeInfo) throws Exception {
                registerView.displayCode(validateCodeInfo.getImage());
            }
        });
    }

    @Override
    public void getRegisterInfo(final Context context, String email, String password, File icon, String userName, String phone, String code) {

        registerModel.getRegisterInfo(context, email, password, icon, userName, phone, code).subscribe(new Consumer<RegisterInfo>() {
            @Override
            public void accept(RegisterInfo registerInfo) throws Exception {
                //对用户进行相关提示
                if(registerInfo.getStatus()){
                    //注册成功
                    registerView.showSuccess(registerInfo.getMessage());
                    Intent intent = new Intent(context, HomePageActivity.class);
                    context.startActivity(intent);
                }else{
                    //注册失败
                    registerView.showError(registerInfo.getMessage(),2);
                }
            }
        });
    }


}
