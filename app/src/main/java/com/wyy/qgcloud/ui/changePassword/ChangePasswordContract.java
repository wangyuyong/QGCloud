package com.wyy.qgcloud.ui.changePassword;

import android.content.Context;
import android.widget.EditText;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;
import com.wyy.qgcloud.enity.ChangePasswordInfo;

import io.reactivex.Observable;

public interface ChangePasswordContract {

    interface ChangePasswordView extends BaseView{
        String getEdt(EditText editText);
        void showError(String msg);
        void showSuccess(String msg);
    }

    interface ChangePasswordModel {
        Observable<ChangePasswordInfo> getChangePasswordInfo(Context context, int userId, String oldPassword, String newPassword);
    }

    interface ChangePasswordPresent extends BasePresent<ChangePasswordView>{
        void getChangePasswordInfo(Context context,int userId, String oldPassword, String newPassword);
    }
}
