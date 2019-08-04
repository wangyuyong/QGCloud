package com.wyy.qgcloud.ui.clouddisk.AuthorityLimit;

import android.content.Context;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;
import com.wyy.qgcloud.enity.ChangePasswordInfo;
import com.wyy.qgcloud.enity.GroupInfo;

import io.reactivex.Observable;

public interface AuthorityContract {

    interface AuthorityView extends BaseView{
        void initList(GroupInfo.DataBean dataBean);
        void showError(String msg);
        void showSuccess(String msg);
    }

    interface AuthorityModel{
        Observable<ChangePasswordInfo> getUpdateAuthorityInfo(Context context, int userId, int fileId, int authority, int told);
        Observable<GroupInfo> getGroupInfo();
    }

    interface AuthorityPresent extends BasePresent<AuthorityView>{
        void getUpdateAuthorityInfo(Context context, int userId, int fileId, int authority, int told);
        void getGroupInfo();
    }
}
