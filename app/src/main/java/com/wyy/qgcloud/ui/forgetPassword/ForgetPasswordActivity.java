package com.wyy.qgcloud.ui.forgetPassword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyy.qgcloud.util.CountDownUtil;
import com.wyy.qgcloud.util.MyToast;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.OnMultiClickListener;
import com.wyy.qgcloud.base.BaseActivity;
import com.wyy.qgcloud.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordContract.ForgetPasswordView {

    @BindView(R.id.imv_forget_back)
    ImageView imvForgetBack;
    @BindView(R.id.edt_forget_email)
    EditText edtForgetEmail;
    @BindView(R.id.edt_forget_code)
    EditText edtForgetCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.edt_forget_new_password)
    EditText edtForgetNewPassword;
    @BindView(R.id.btn_finish_set_new_password)
    Button btnFinishSetNewPassword;
    private ForgetPasswordContract.ForgetPasswordPresent forgetPasswordPresent;
    public static Activity mForgetPasswordActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        mForgetPasswordActivity = this;
        forgetPasswordPresent = new ForgetPasswordPresent();
        forgetPasswordPresent.bindView(this);
        edtForgetNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        btnFinishSetNewPassword.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                setNewPassword();
            }
        });
        new CountDownUtil(tvGetCode)
                .setCountDownMillis(60000)
                .setCountDownColor(R.color.colorTextWhite, R.color.colorBtn, R.color.colorBtn, R.color.colorTextWhite)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = getEdt(edtForgetEmail);
                        forgetPasswordPresent.getEmailCodeInfo(ForgetPasswordActivity.this, email);
                    }
                });

    }

    @OnClick(R.id.imv_forget_back)
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.imv_forget_back:
                finish();
                break;
                default:
                    break;

        }
    }

    @Override
    public String getEdt(EditText editText) {
        String editMsg = editText.getText().toString();
        return editMsg;
    }

    @Override
    public void showError(String msg) {
        MyToast.getMyToast().ToastShow(ForgetPasswordActivity.this,null, R.drawable.ic_sad, msg);
    }

    @Override
    public void showSuccess(String msg) {
        MyToast.getMyToast().ToastShow(ForgetPasswordActivity.this,null, R.drawable.ic_happy, msg);
    }


    void setNewPassword(){
        String code = getEdt(edtForgetCode);
        String email = getEdt(edtForgetEmail);
        String password = getEdt(edtForgetNewPassword);
        forgetPasswordPresent.getNewPasswordInfo(this, code, email, password);
    }

    @Override
    protected void onDestroy() {
        forgetPasswordPresent.unbindView();
        super.onDestroy();
    }


}
