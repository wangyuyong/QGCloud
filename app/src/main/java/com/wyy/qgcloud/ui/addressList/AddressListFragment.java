package com.wyy.qgcloud.ui.addressList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.AddressListAdapter;
import com.wyy.qgcloud.enity.GroupInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressListFragment extends Fragment implements AddressListContract.AddressListView {

    @BindView(R.id.expand_address_list)
    ExpandableListView expandAddressList;
    private AddressListContract.AddressListPresent addressListPresent;
    private List<GroupInfo.DataBean> groupMemberList;
    private List<String> groupList;  //父项数据来源
    private AddressListAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_list, container, false);
        ButterKnife.bind(this, view);
        addressListPresent = new AddressListPresent();
        addressListPresent.bindView(this);
        initGroupList();
        addressListPresent.getGroupInfo();
        initGroupMemberList();
        initView();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addressListPresent.unbindView();
    }

    private void initGroupList(){
        groupList =new ArrayList<String>();
        groupList.add("后台组");
        groupList.add("前端组");
        groupList.add("移动组");
        groupList.add("数据挖掘组");
        groupList.add("嵌入式组");
        groupList.add("设计组");
        groupList.add("图形组");
        groupList.add("未分组");
    }   //父项列表

    private void initGroupMemberList(){
        groupMemberList = new ArrayList<>();
        adapter = new AddressListAdapter(groupMemberList,groupList, getActivity());
    }

    private void initView(){
        expandAddressList.setAdapter(adapter);
        registerForContextMenu(expandAddressList);
    }

    @Override
    public void initList(GroupInfo.DataBean dataBean) {
        groupMemberList.add(dataBean);
    }
}
