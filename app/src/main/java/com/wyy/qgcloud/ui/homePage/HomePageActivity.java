package com.wyy.qgcloud.ui.homePage;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.ViewPagerAdapter;
import com.wyy.qgcloud.app.MyApplication;
import com.wyy.qgcloud.enity.FilePathMessge;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.enity.UploadFileMessage;
import com.wyy.qgcloud.ui.addressList.AddressListFragment;
import com.wyy.qgcloud.ui.clouddisk.CloudFragment;
import com.wyy.qgcloud.ui.my.MyFragment;
import com.wyy.qgcloud.ui.transfer.TransferFragment;
import com.wyy.qgcloud.util.MyToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.home_bottom_navigation)
    BottomNavigationView bottomNavigationView;
    private LoginInfo.DataBean dataBean;
    private MenuItem menuItem;
    private String path;
    public static Activity mHomePageActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        mHomePageActivity = this;
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        dataBean = (LoginInfo.DataBean) intent.getSerializableExtra("data");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_item_address:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_item_cloud:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_item_transmit:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_item_my:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return false;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(menuItem != null){
                    menuItem.setChecked(false);
                }else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(i);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        setViewPager(viewPager);
    }

    private void setViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AddressListFragment());
        adapter.addFragment(new CloudFragment());
        adapter.addFragment(new TransferFragment());
        adapter.addFragment(new MyFragment());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
    }

    public LoginInfo.DataBean getUser() {
        return dataBean;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                //获取文件并将文件转化为file对象
                Uri uri = data.getData();
                if (uri == null){
                    MyToast.getMyToast().ToastShow(MyApplication.getContext(),null,R.drawable.ic_happy,"无法获取这份文件");
                    return;
                }
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if (cursor == null){
                    MyToast.getMyToast().ToastShow(MyApplication.getContext(),null,R.drawable.ic_happy,"无法获取这份文件");
                    return;
                }
                int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String file = cursor.getString(index);
                if (file == null){
                    MyToast.getMyToast().ToastShow(MyApplication.getContext(),null,R.drawable.ic_happy,"无法获取这份文件");
                    return;
                }
                String fileName = file.substring(file.lastIndexOf("/") + 1);
                SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
                String time = format.format(new Date());
                int userId = dataBean.getUserId();
                UploadFileMessage msg = new UploadFileMessage(file,fileName,time,userId,path);
                EventBus.getDefault().post(msg);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void openFile(FilePathMessge msg){
        path = msg.getFilePath();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
