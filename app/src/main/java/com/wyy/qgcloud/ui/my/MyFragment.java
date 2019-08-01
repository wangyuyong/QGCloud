package com.wyy.qgcloud.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.homePage.HomePageActivity;
import com.wyy.qgcloud.ui.personalMsg.PersonalMsgActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment implements MyContract.MyView {

    @BindView(R.id.layout_personal_msg)
    LinearLayout layoutPersonalMsg;
    @BindView(R.id.layout_change_password)
    LinearLayout layoutChangePassword;
    @BindView(R.id.layout_setting)
    LinearLayout layoutSetting;
    @BindView(R.id.layout_exit)
    LinearLayout layoutExit;
    @BindView(R.id.tv_my_name)
    TextView tvMyName;
    @BindView(R.id.tv_my_group)
    TextView tvMyGroup;
    @BindView(R.id.tv_my_position)
    TextView tvMyPosition;
    @BindView(R.id.imv_my_icon)
    CircleImageView imvMyIcon;
    private MyContract.MyPresent myPresent;
    private int userId;
    private LoginInfo.DataBean user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this,view);
        myPresent = new MyPresent();
        myPresent.bindView(this);
        HomePageActivity homePageActivity = (HomePageActivity) getActivity();
        userId = homePageActivity.getUserId();  //获取用户id
        user = homePageActivity.getUser();      //获取整个用户对象
        showPersonalMsg();  //显示个人信息
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        myPresent.unbindView();
    }

    @OnClick({R.id.layout_personal_msg, R.id.layout_change_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_personal_msg:
                Intent intent1 = new Intent(getActivity(), PersonalMsgActivity.class);
                intent1.putExtra("user", user);
                startActivity(intent1);
                break;
            case R.id.layout_change_password:
                Intent intent2 = new Intent(getActivity(), PersonalMsgActivity.class);
                intent2.putExtra("userId", userId);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void showPersonalMsg() {
        //显示头像
        Glide.with(this)
                .load(user.getIcon())
                .into(imvMyIcon);
        //显示姓名
        tvMyName.setText(user.getUserName());
        //显示组别
        tvMyGroup.setText(user.getGroupName());
        //显示身份
        int role = user.getRole();
        switch (role){
            case 0:
                tvMyPosition.setText("普通用户");
                break;
            case 1:
                tvMyPosition.setText("组长");
                break;
            case 2:
                tvMyPosition.setText("工作室负责人");
                break;
                default:
                    break;
        }
    }
}
