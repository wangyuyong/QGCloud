package com.wyy.qgcloud.ui.changePassword;

import android.content.Context;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;
import com.wyy.qgcloud.enity.ChangePasswordInfo;

import io.reactivex.Observable;

public interface ChangePasswordContract {

    interface ChangePasswordView extends BaseView{

    }

    interface ChangePasswordModel {
        Observable<ChangePasswordInfo> getChangePasswordInfo(Context context, String password, String email, String userId);
    }

    interface ChangePasswordPresent extends BasePresent<ChangePasswordView>{

    }
}
