package com.wyy.qgcloud.ui.changePassword;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.base.BaseActivity;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.login.LoginActivity;
import com.wyy.qgcloud.ui.my.MyFragment;
import com.wyy.qgcloud.util.MyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordContract.ChangePasswordView {

    @BindView(R.id.imv_change_back)
    ImageView imvChangeBack;
    @BindView(R.id.edt_change_old_password)
    EditText edtChangeOldPassword;
    @BindView(R.id.edt_change_new_password)
    EditText edtChangeNewPassword;
    @BindView(R.id.edt_check_password)
    EditText edtCheckPassword;
    @BindView(R.id.btn_change_password)
    Button btnChangePassword;
    private ChangePasswordContract.ChangePasswordPresent changePasswordPresent;
    private LoginInfo.DataBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        changePasswordPresent = new ChangePasswordPresent();
        changePasswordPresent.bindView(this);
        Intent intent = getIntent();
        user = (LoginInfo.DataBean)intent.getSerializableExtra("user");
        edtChangeOldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        edtChangeNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        edtCheckPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    public String getEdt(EditText editText) {
        String editMsg = editText.getText().toString();
        return editMsg;
    }

    @Override
    public void showError(String msg) {
        MyToast.getMyToast().ToastShow(ChangePasswordActivity.this,null, R.drawable.ic_sad, msg);
    }

    @Override
    public void showSuccess(String msg) {
        MyToast.getMyToast().ToastShow(ChangePasswordActivity.this,null, R.drawable.ic_happy, msg);
    }

    @OnClick({R.id.imv_change_back, R.id.btn_change_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_change_back:
                finish();
                break;
            case R.id.btn_change_password:
                String newPassword = getEdt(edtChangeNewPassword);
                String checkPassword = getEdt(edtCheckPassword);
                if(newPassword.equals(checkPassword)){
                    changePassword();
                }else{
                    showError("新密码与确认密码不符！请检查。");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        changePasswordPresent.unbindView();
        super.onDestroy();
    }

    private void changePassword(){
        String oldPassword = getEdt(edtChangeOldPassword);
        String newPassword = getEdt(edtChangeNewPassword);
        changePasswordPresent.getChangePasswordInfo(this, user.getUserId(), oldPassword, newPassword);
    }

}
