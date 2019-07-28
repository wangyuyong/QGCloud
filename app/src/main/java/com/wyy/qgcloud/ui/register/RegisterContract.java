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

        void displayCode(byte[] icon);

        //参数kind若为1则为爆红提示，若为2则为弹窗提示
        void showError(String msg, int kind);

        void showSuccess(String msg);  //均为弹窗提示
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
