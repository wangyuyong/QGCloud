package com.wyy.qgcloud.ui.personalMsg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.base.BaseActivity;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.my.MyFragment;
import com.wyy.qgcloud.util.MyToast;

import java.io.File;
import java.lang.reflect.Field;
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
    @BindView(R.id.tv_personal_email)
    TextView tvPersonalEmail;
    @BindView(R.id.edt_change_phone)
    EditText edtChangePhone;
    private PersonalMsgContract.PersonalMsgPresent personalMsgPresent;
    LoginInfo.DataBean user;
    private LoginInfo.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_msg);
        Intent intent = getIntent();
        dataBean = (LoginInfo.DataBean) intent.getSerializableExtra("data");

        ButterKnife.bind(this);
        personalMsgPresent = new PersonalMsgPresent();
        personalMsgPresent.bindView(this);
//        Intent intent = getIntent();
//        user = (LoginInfo.DataBean) intent.getSerializableExtra("user");
        showPersonalMsg();
//        edtChangePhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    //失去焦点
//                    edtChangePhone.setBackground(null);
//                    File icon = new File(dataBean.getIcon());
//                    String phone = getEdt(edtChangePhone);
//                    String regex = "^[1][3,4,5,7,8,9][0-9]{9}$";
//                    boolean format = Pattern.matches(regex, phone);
//                    if (format){
//                        personalMsgPresent.getChangeMsgInfo(PersonalMsgActivity.this, dataBean.getUserId(), dataBean.getEmail(), icon, phone, dataBean.getGroupName());
//                    }
//                }
//            }
//        });
    }

    @OnClick({R.id.imv_personal_back, R.id.imv_personal_icon, R.id.edt_change_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_personal_back:
                Intent intent = new Intent(this, MyFragment.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imv_personal_icon:  //暂未上线修改头像功能
                break;
            case R.id.edt_change_phone:
                //弹出修改框
                break;
        }
    }

    @Override
    public void showPersonalMsg() {
        //显示头像
        Glide.with(this)
                .load(dataBean.getIcon())
                .into(imvPersonalIcon);
        //显示姓名
        tvPersonalName.setText(dataBean.getUserName());
        //显示手机
        edtChangePhone.setText(dataBean.getPhone());
        //显示邮箱
        tvPersonalEmail.setText(dataBean.getEmail());
    }

    @Override
    public String getEdt(EditText editText) {
        String editMsg = editText.getText().toString();
        return editMsg;
    }

    @Override
    public void showError(String msg) {
        MyToast.getMyToast().ToastShow(PersonalMsgActivity.this, null, R.drawable.ic_sad, msg);
    }

    @Override
    public void showSuccess(String msg) {
        MyToast.getMyToast().ToastShow(PersonalMsgActivity.this, null, R.drawable.ic_happy, msg);
    }
}
