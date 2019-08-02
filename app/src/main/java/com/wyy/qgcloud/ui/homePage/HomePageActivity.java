package com.wyy.qgcloud.ui.homePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.ViewPagerAdapter;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.addressList.AddressListFragment;
import com.wyy.qgcloud.ui.clouddisk.CloudFragment;
import com.wyy.qgcloud.ui.my.MyFragment;
import com.wyy.qgcloud.ui.transfer.TransferFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.home_bottom_navigation)
    BottomNavigationView bottomNavigationView;
    private LoginInfo.DataBean dataBean;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
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
        // 取消滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
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

}
