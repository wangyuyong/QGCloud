package com.wyy.qgcloud.ui.login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wyy.qgcloud.ui.personalMsg.PersonalMsgActivity;
import com.wyy.qgcloud.util.MyToast;
import com.wyy.qgcloud.R;

import com.wyy.qgcloud.adapter.OnMultiClickListener;
import com.wyy.qgcloud.ui.forgetPassword.ForgetPasswordActivity;
import com.wyy.qgcloud.ui.register.RegisterActivity;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    @BindView(R.id.edt_login_email)
    EditText loginEmailEdt;
    @BindView(R.id.edt_login_password)
    EditText loginPasswordEdt;
    @BindView(R.id.btn_forget_password)
    Button forgetPasswordBtn;
    @BindView(R.id.btn_to_register)
    Button toRegisteBtn;
    @BindView(R.id.btn_login)
    Button loginBtn;

    public static Activity mLoginActivity;
    private LoginContract.LoginPresent loginPresent = new LoginPresent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLoginActivity = this;
        loginPresent = new LoginPresent();
        loginPresent.bindView(this);
        loginPasswordEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        loginEmailEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String email = getEdt(loginEmailEdt);
                    String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                    boolean format = Pattern.matches(regex, email);
                    if(format) {
                        loginPresent.getEmailInfo(LoginActivity.this, email);
                    }else{
                        showError("邮箱格式不正确！请重新输入。", 1);
                    }
                }
            }
        });
        loginBtn.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                login();
            }
        });
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public String getEdt(EditText editText) {
        String editMsg = editText.getText().toString();
        return editMsg;
    }

    @OnClick({ R.id.btn_forget_password, R.id.btn_to_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_forget_password:
                Intent intent1 = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_to_register:
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        loginPresent.unbindView();
        super.onDestroy();
    }

    @Override
    public void showError(String msg, int kind) {
        switch (kind){
            //爆红提示
            case 1:
                loginEmailEdt.setTextColor(getResources().getColor(R.color.colorError));
                MyToast.getMyToast().ToastShow(LoginActivity.this,null, R.drawable.ic_sad, msg);
                break;
            //弹窗提示
            case 2:
                MyToast.getMyToast().ToastShow(LoginActivity.this,null, R.drawable.ic_sad, msg);
                break;
            default:
                break;
        }
    }

    @Override
    public void showSuccess(String msg) {
        MyToast.getMyToast().ToastShow(LoginActivity.this,null, R.drawable.ic_happy, msg);
    }

    private void login(){
        String email = getEdt(loginEmailEdt);
        String password = getEdt(loginPasswordEdt);
        loginPresent.getLoginInfo(this, email, password);
    }

    //权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                   finish();
                }
                break;
            default:
                break;
        }
    }

}
