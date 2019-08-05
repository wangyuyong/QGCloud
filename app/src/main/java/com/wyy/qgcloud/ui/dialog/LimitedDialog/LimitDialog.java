package com.wyy.qgcloud.ui.dialog.LimitedDialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.OnMultiClickListener;
import com.wyy.qgcloud.adapter.TransferAdapter;
import com.wyy.qgcloud.enity.PowerMessage;
import com.wyy.qgcloud.ui.clouddisk.AuthorityLimit.ReadFragment;
import com.wyy.qgcloud.ui.clouddisk.AuthorityLimit.WriteFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class LimitDialog extends DialogFragment {

    private List<Fragment> fragmentList;
    private ReadFragment readFragment;
    private WriteFragment writeFragment;
    private TransferAdapter adapter;

    private TabLayout tbSetLimit;
    private ViewPager vpSetLimit;
    private Button btnUserPositionCancel;
    private Button btnUserPositionSure;

    private int position;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //隐藏title
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setBackgroundDrawable(new ColorDrawable(0xffffff));
        window.setAttributes(lp);
        View view = inflater.inflate(R.layout.dialog_set_limit,null);
        tbSetLimit = view.findViewById(R.id.tb_set_limit);
        vpSetLimit = view.findViewById(R.id.vp_set_limited);
        btnUserPositionCancel = view.findViewById(R.id.btn_user_position_cancel);
        btnUserPositionSure = view.findViewById(R.id.btn_user_position_sure);
        init();
        return view;
    }

    private void init(){
        fragmentList = new ArrayList<>();
        readFragment = new ReadFragment();
        writeFragment = new WriteFragment();
        fragmentList.add(readFragment);
        fragmentList.add(writeFragment);
        adapter = new TransferAdapter(getChildFragmentManager(), fragmentList);
        vpSetLimit.setAdapter(adapter);
        vpSetLimit.setOffscreenPageLimit(1);
        tbSetLimit.setupWithViewPager(vpSetLimit);
        tbSetLimit.getTabAt(0).setText("可读");
        tbSetLimit.getTabAt(1).setText("可写");

        btnUserPositionCancel.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                dismiss();
            }
        });

        btnUserPositionSure.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (tbSetLimit.getSelectedTabPosition() == 0){
                    PowerMessage msg = new PowerMessage(PowerMessage.CONST_CAN_READ,readFragment.getToId(),position);
                    EventBus.getDefault().post(msg);
                }else {
                    PowerMessage msg = new PowerMessage(PowerMessage.CONST_CAN_WRITE,writeFragment.getToId(),position);
                    EventBus.getDefault().post(msg);
                }
                dismiss();
            }
        });
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
