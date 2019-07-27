package com.wyy.qgcloud.ui.login;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.net.HttpService;
import com.wyy.qgcloud.net.RetrofitManager;
import com.wyy.qgcloud.ui.download.DownloadService;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
