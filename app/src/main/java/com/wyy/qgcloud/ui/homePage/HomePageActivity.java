package com.wyy.qgcloud.ui.homePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.changePassword.ChangePasswordActivity;
import com.wyy.qgcloud.ui.my.MyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePageActivity extends AppCompatActivity {

    private LoginInfo.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        dataBean = (LoginInfo.DataBean) intent.getSerializableExtra("data");
    }

    public int getUserId() {
        return dataBean.getUserId();
    }

    public LoginInfo.DataBean getUser() {
        return dataBean;
    }


}
