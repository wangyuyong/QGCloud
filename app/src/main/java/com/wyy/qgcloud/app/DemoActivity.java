package com.wyy.qgcloud.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.TransferAdapter;
import com.wyy.qgcloud.ui.clouddisk.CloudFragment;
import com.wyy.qgcloud.ui.transfer.checkfile.CheckFileFragment;
import com.wyy.qgcloud.ui.transfer.download.DownloadFragment;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

        ViewPager viewPager = findViewById(R.id.vp_demo);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CloudFragment());
        fragments.add(new DownloadFragment());
        fragments.add(new CheckFileFragment());
        TransferAdapter adapter = new TransferAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
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
}
