package com.wyy.qgcloud.ui.personalMsg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.base.BaseActivity;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.dialog.ConfigOnClickedListener;
import com.wyy.qgcloud.ui.homePage.HomePageActivity;
import com.wyy.qgcloud.ui.my.MyFragment;
import com.wyy.qgcloud.util.ChangePhoneDialog;
import com.wyy.qgcloud.util.MyToast;

import java.io.File;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalMsgActivity extends BaseActivity implements PersonalMsgContract.PersonalMsgView {

    @BindView(R.id.imv_personal_back)
    ImageView imvPersonalBack;
    @BindView(R.id.personal_msg_toolbar)
    Toolbar personalMsgToolbar;
    @BindView(R.id.imv_personal_icon)
    CircleImageView imvPersonalIcon;
    @BindView(R.id.tv_personal_name)
    TextView tvPersonalName;
    @BindView(R.id.tv_change_phone)
    TextView tvChangePhone;
    @BindView(R.id.tv_personal_email)
    TextView tvPersonalEmail;
    private PersonalMsgContract.PersonalMsgPresent personalMsgPresent;
    LoginInfo.DataBean user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_msg);
        ButterKnife.bind(this);
        personalMsgPresent = new PersonalMsgPresent();
        personalMsgPresent.bindView(this);
        Intent intent = getIntent();
        user = (LoginInfo.DataBean) intent.getSerializableExtra("user");
        showPersonalMsg();
        tvChangePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出修改框
                final ChangePhoneDialog dialog = new ChangePhoneDialog(PersonalMsgActivity.this, R.style.dialog);
                dialog.setListener(new ConfigOnClickedListener() {
                    @Override
                    public void onClick(String info) {
                        dialog.cancel();
                        String regex = "^[1][3,4,5,7,8,9][0-9]{9}$";
                        boolean format = Pattern.matches(regex, info);
                        if(format){
                            personalMsgPresent.getChangeMsgInfo(PersonalMsgActivity.this, user.getUserId(), user.getEmail(), info);
                        }else {
                            //输入格式不正确
                            showError("手机号格式不正确！请重新输入。");
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void showPersonalMsg() {
        //显示头像
        Glide.with(this)
                .load(user.getIcon())
                .into(imvPersonalIcon);
        //显示姓名
        tvPersonalName.setText(user.getUserName());
        //显示手机
        tvChangePhone.setText(user.getPhone());
        //显示邮箱
        tvPersonalEmail.setText(user.getEmail());
    }


    @Override
    public void showError(String msg) {
        MyToast.getMyToast().ToastShow(PersonalMsgActivity.this, null, R.drawable.ic_sad, msg);
    }

    @Override
    public void showSuccess(String msg) {
        MyToast.getMyToast().ToastShow(PersonalMsgActivity.this, null, R.drawable.ic_happy, msg);
    }

    @Override
    public void changePassword(String phone) {
        tvChangePhone.setText(phone);
    }


    @OnClick({R.id.imv_personal_back, R.id.imv_personal_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_personal_back:
                finish();
                Intent intent = new Intent(this, HomePageActivity.class);
                startActivity(intent);
                break;
            //暂未上线修改头像功能
            case R.id.imv_personal_icon:
                break;
        }
    }
}
