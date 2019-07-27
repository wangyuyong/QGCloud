package com.wyy.qgcloud.ui.download;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wyy.qgcloud.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownLoadTest extends AppCompatActivity {

    @BindView(R.id.start_download)
    Button startDownload;
    @BindView(R.id.pause_download)
    Button pauseDownload;
    @BindView(R.id.cancel)
    Button cancel;

    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_test);
        ButterKnife.bind(this);
        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    @OnClick({R.id.start_download, R.id.pause_download, R.id.cancel})
    public void onViewClicked(View view) {
        if (downloadBinder == null){
            Log.d("Down","null");
            return;
        }
        switch (view.getId()) {
            case R.id.start_download:
                String url = "http://b4e2590a.ngrok.io/downloadServlet";
                downloadBinder.startDownload(url,"/downloadServlet");
                break;
            case R.id.pause_download:
                downloadBinder.pauseDownload();
                break;
            case R.id.cancel:
                downloadBinder.cancelDownload();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
