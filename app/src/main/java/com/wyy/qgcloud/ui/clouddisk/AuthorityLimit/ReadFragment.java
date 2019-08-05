package com.wyy.qgcloud.ui.clouddisk.AuthorityLimit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.LimitAdapter;
import com.wyy.qgcloud.enity.GroupInfo;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.homePage.HomePageActivity;
import com.wyy.qgcloud.util.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadFragment extends Fragment implements AuthorityContract.AuthorityView {

    ExpandableListView readExpandList;
    private AuthorityContract.AuthorityPresent authorityPresent;
    private List<GroupInfo.DataBean> groupMemberList;
    private List<String> groupList;     //父项数据来源
    private LimitAdapter adapter;
    private LoginInfo.DataBean user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_is_read, container, false);
        authorityPresent = new AuthorityPresent();
        authorityPresent.bindView(this);
        HomePageActivity homePageActivity = (HomePageActivity) getActivity();
        user = homePageActivity.getUser();      //获取整个用户对象
        readExpandList = view.findViewById(R.id.expand_is_read);
        initGroupList();
        authorityPresent.getGroupInfo();
        initGroupMemberList();
        initView();

        return view;
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
        adapter = new LimitAdapter(groupMemberList,groupList, getActivity());
    }

    private void initView(){
        readExpandList.setGroupIndicator(null);
        readExpandList.setAdapter(adapter);
        registerForContextMenu(readExpandList);
    }

    @Override
    public void initList(GroupInfo.DataBean dataBean) {
        groupMemberList.add(dataBean);
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
    public void onDestroyView() {
        super.onDestroyView();
        authorityPresent.unbindView();
    }

    public List<Integer> getToId(){
        return adapter.getToId();
    }
}
