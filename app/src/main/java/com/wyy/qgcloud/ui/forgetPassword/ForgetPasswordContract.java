package com.wyy.qgcloud.ui.forgetPassword;

import android.content.Context;
import android.widget.EditText;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;
import com.wyy.qgcloud.enity.GetEmailCodeInfo;
import com.wyy.qgcloud.enity.SetNewPasswordInfo;

import io.reactivex.Observable;

public interface ForgetPasswordContract {

    interface ForgetPasswordView extends BaseView {
        String getEdt(EditText editText);

        void showError(String msg);

        void showSuccess(String msg);
    }

    interface ForgetPasswordPresent extends BasePresent<ForgetPasswordView> {
        void getEmailCodeInfo(Context context, String email);
        void getNewPasswordInfo(Context context, String code, String email, String password);
    }

    interface ForgetPasswordModel {
        Observable<GetEmailCodeInfo> getEmailCodeInfo(Context context, String email);
        Observable<SetNewPasswordInfo> getNewPasswordInfo(Context context, String code, String email, String password);
    }
}
