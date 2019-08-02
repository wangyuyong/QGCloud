package com.wyy.qgcloud.ui.addressList;

import android.content.Context;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;
import com.wyy.qgcloud.enity.GroupInfo;
import com.wyy.qgcloud.enity.MemberChangeInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface AddressListContract {

    interface AddressListView extends BaseView{
        void initList(GroupInfo.DataBean dataBean);
        void showError(String msg);
        void showSuccess(String msg);
    }

    interface AddressListModel{
        Observable<GroupInfo> getGroupInfo();
        Observable<MemberChangeInfo> getJoinGroupInfo(Context context, int userId, int toUserId, int groupId);
        Observable<MemberChangeInfo> getUserPositionInfo(Context context, int userId, int toUserId, int role);
    }

    interface AddressListPresent extends BasePresent<AddressListView>{
        void getGroupInfo();
        void getJoinGroupInfo(Context context, int userId, int toUserId, int groupId);
        void getUserPositionInfo(Context context, int userId, int toUserId, int role);
    }
}
