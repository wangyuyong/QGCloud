package com.wyy.qgcloud.ui.personalMsg;

import android.content.Context;
import android.widget.EditText;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;
import com.wyy.qgcloud.enity.ChangePasswordInfo;
import com.wyy.qgcloud.enity.LoginInfo;

import java.io.File;

import io.reactivex.Observable;

public interface PersonalMsgContract {

    interface PersonalMsgView extends BaseView {
        void showPersonalMsg();
        String getEdt(EditText editText);
        void showError(String msg);
        void showSuccess(String msg);
    }

    interface PersonalMsgModel{
        Observable<ChangePasswordInfo> getChangeMsgInfo(Context context, int userId, String email, String phone);
    }

    interface PersonalMsgPresent extends BasePresent<PersonalMsgView> {
        void getChangeMsgInfo(Context context, int userId, String email, String phone);;
    }
}
