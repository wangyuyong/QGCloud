package com.wyy.qgcloud.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wyy.qgcloud.MyToast;
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

    private LoginContract.LoginPresent loginPresent = new LoginPresent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginPresent = new LoginPresent();
        loginPresent.bindView(this);
        loginEmailEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = getEdt(loginEmailEdt);
                String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                boolean format = Pattern.matches(regex, email);
                if(!format){
                    loginEmailEdt.setTextColor(getResources().getColor(R.color.colorError));
                }else{
                    loginEmailEdt.setTextColor(getResources().getColor(R.color.colorTextBlack));
                }
            }
        });
        loginEmailEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String email = getEdt(loginEmailEdt);
                    String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                    boolean format = Pattern.matches(regex, email);
                    if(format) {
                        loginPresent.getEmailInfo(LoginActivity.this, email);
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
    }

    @Override
    public String getEdt(EditText editText) {
        String editMsg = editText.getText().toString();
        return editMsg;
    }


    @OnClick({R.id.btn_login, R.id.btn_forget_password, R.id.btn_to_register})
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
                loginEmailEdt.setBackgroundColor(getResources().getColor(R.color.colorError));
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                break;
            //弹窗提示
            case 2:
                MyToast.getMyToast().ToastShow(LoginActivity.this,null, R.drawable.sad, msg);
                break;
            default:
                break;
        }
    }

    @Override
    public void showSuccess(String msg) {
        MyToast.getMyToast().ToastShow(LoginActivity.this,null, R.drawable.happy, msg);
    }

    private void login(){
        String email = getEdt(loginEmailEdt);
        String password = getEdt(loginPasswordEdt);
        loginPresent.getLoginInfo(this, email, password);
    }

}
