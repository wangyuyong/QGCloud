package com.wyy.qgcloud.ui.addressList;

import android.content.Context;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;
import com.wyy.qgcloud.enity.GroupInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface AddressListContract {

    interface AddressListView extends BaseView{
        void initList(GroupInfo.DataBean dataBean);
    }

    interface AddressListModel{
        Observable<GroupInfo> getGroupInfo();
    }

    interface AddressListPresent extends BasePresent<AddressListView>{
        void getGroupInfo();
    }
}
