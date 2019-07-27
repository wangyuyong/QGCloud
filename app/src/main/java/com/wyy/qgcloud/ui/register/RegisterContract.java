package com.wyy.qgcloud.ui.register;

import android.content.Context;
import android.widget.EditText;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;
import com.wyy.qgcloud.enity.RegisterInfo;
import com.wyy.qgcloud.enity.ValidateCodeInfo;

import java.io.File;

import io.reactivex.Observable;

public interface RegisterContract {

    interface RegisterView extends BaseView{
        String getEdt(EditText editText);
        void displayIcon(File icon);
    }

    interface RegisterPresent extends BasePresent<RegisterView>{
        void getValidateCodeInfo(Context context);
        void getRegisterInfo(Context context, String email, String password, File icon, String userName, String phone, String code);
    }

    interface RegisterModel {
        Observable<ValidateCodeInfo> getValidateCodeInfo(Context context);
        Observable<RegisterInfo> getRegisterInfo(Context context, String email, String password, File icon, String userName, String phone, String code);
    }
}
