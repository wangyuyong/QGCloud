package com.wyy.qgcloud.ui.addressList;

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


}
