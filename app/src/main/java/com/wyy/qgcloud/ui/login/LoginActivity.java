package com.wyy.qgcloud.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wyy.qgcloud.R;
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

    private LoginContract.LoginPresent loginPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
                if(format){
                    //格式正确则发送网络请求判断是否存在
                }else {
                    //格式不正确则提示用户
                }
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
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_forget_password:

                break;
            case R.id.btn_to_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        loginPresent.unbindView();
        super.onDestroy();
    }

    private void login(){
        String email = getEdt(loginEmailEdt);
        String password = getEdt(loginPasswordEdt);
        loginPresent.getLoginInfo(this, email, password);
    }
}
