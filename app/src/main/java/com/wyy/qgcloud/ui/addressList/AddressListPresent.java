package com.wyy.qgcloud.ui.addressList;

import android.content.Context;
import android.util.Log;

import com.wyy.qgcloud.enity.GroupInfo;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class AddressListPresent implements AddressListContract.AddressListPresent {

    private AddressListContract.AddressListModel addressListModel;
    private AddressListContract.AddressListView addressListView;

    @Override
    public void bindView(AddressListContract.AddressListView view) {
        this.addressListView = view;
        addressListModel = new AddressListModel();
    }

    @Override
    public void unbindView() {
        addressListModel = null;
        addressListView = null;
    }


    @Override
    public void getGroupInfo() {
        Log.d("wx", "1");
        addressListModel.getGroupInfo().subscribe(new Observer<GroupInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GroupInfo groupInfo) {
                addressListView.initList(groupInfo.getData());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
