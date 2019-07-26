package com.wyy.qgcloud.ui.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.net.HttpService;
import com.wyy.qgcloud.net.RetrofitManager;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitManager manager = RetrofitManager.getInstance();
        HttpService service = manager.getHttpService();

        int a;
    }
}
