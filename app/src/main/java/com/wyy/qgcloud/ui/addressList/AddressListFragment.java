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
import com.wyy.qgcloud.adapter.BottomSheetOnClickListener;
import com.wyy.qgcloud.adapter.OnItemClickedListener;
import com.wyy.qgcloud.app.MyApplication;
import com.wyy.qgcloud.enity.GroupInfo;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.dialog.ConfigOnClickedListener;
import com.wyy.qgcloud.ui.homePage.HomePageActivity;
import com.wyy.qgcloud.ui.personalMsg.PersonalMsgActivity;
import com.wyy.qgcloud.util.ChangePhoneDialog;
import com.wyy.qgcloud.util.JoinGroupDialog;
import com.wyy.qgcloud.util.MyToast;
import com.wyy.qgcloud.util.UserOnClickListener;
import com.wyy.qgcloud.util.UserPositionDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressListFragment extends Fragment implements AddressListContract.AddressListView {

    @BindView(R.id.expand_address_list)
    ExpandableListView expandAddressList;
    private AddressListContract.AddressListPresent addressListPresent;
    private List<GroupInfo.DataBean> groupMemberList;
    private List<String> groupList;  //父项数据来源
    private AddressListAdapter adapter;
    private LoginInfo.DataBean user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_address_list, container, false);
        ButterKnife.bind(this, view);
        addressListPresent = new AddressListPresent();
        addressListPresent.bindView(this);
        HomePageActivity homePageActivity = (HomePageActivity) getActivity();
        user = homePageActivity.getUser();      //获取整个用户对象
        initGroupList();
        addressListPresent.getGroupInfo();
        initGroupMemberList();
        initView();
        adapter.setBottomSheetOnClickListener(new BottomSheetOnClickListener() {
            @Override
            public void bottomSheetOnClick(int item, final int toUserId) {
                final int userId = user.getUserId();
                switch (item){
                        case 0:
                            //弹出用户分组dialog
                            final JoinGroupDialog dialog1 = new JoinGroupDialog(getActivity(), R.style.dialog);
                            dialog1.setListener(new UserOnClickListener() {
                                @Override
                                public void onClick(int position) {
                                    dialog1.cancel();
                                    addressListPresent.getJoinGroupInfo(getActivity(),userId,toUserId, position);
                                }
                            });
                            dialog1.show();
                            break;
                        case 1:
                            //弹出身份赋予dialog
                                final UserPositionDialog dialog2 = new UserPositionDialog(getActivity() , R.style.dialog);
                                dialog2.setListener(new UserOnClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        dialog2.cancel();
                                        addressListPresent.getUserPositionInfo(getActivity(), userId, toUserId,position);
                                    }
                                });
                                dialog2.show();
                            break;
                        default:
                            break;
                }
            }
        });
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
    }

    private void initGroupMemberList(){
        groupMemberList = new ArrayList<>();
        adapter = new AddressListAdapter(groupMemberList,groupList, getActivity());
    }

    private void initView(){
        expandAddressList.setGroupIndicator(null);
        expandAddressList.setAdapter(adapter);
        registerForContextMenu(expandAddressList);
    }

    @Override
    public void initList(GroupInfo.DataBean dataBean) {
        groupMemberList.add(dataBean);
        expandAddressList.setAdapter(adapter);
    }


    @Override
    public void showError(String msg) {
        MyToast.getMyToast().ToastShow(getActivity(), null, R.drawable.ic_sad, msg);
    }

    @Override
    public void showSuccess(String msg) {
        MyToast.getMyToast().ToastShow(getActivity(), null, R.drawable.ic_happy, msg);
    }

    @Override
    public void refreshList() {
        groupMemberList.clear();
        addressListPresent.getGroupInfo();
        initGroupMemberList();
        initView();
    }
}
