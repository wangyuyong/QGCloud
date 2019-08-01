package com.wyy.qgcloud.ui.addressList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.wyy.qgcloud.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressListFragment extends Fragment implements AddressListContract.AddressListView {

    @BindView(R.id.expand_address_list)
    ExpandableListView expandAddressList;
    private AddressListContract.AddressListPresent addressListPresent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_list, container, false);
        ButterKnife.bind(this, view);
        addressListPresent = new AddressListPresent();
        addressListPresent.bindView(this);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addressListPresent.unbindView();
    }


}
