package com.wyy.qgcloud.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.TransferAdapter;
import com.wyy.qgcloud.ui.clouddisk.AuthorityLimit.ReadFragment;
import com.wyy.qgcloud.ui.clouddisk.AuthorityLimit.WriteFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LimitDialog extends DialogFragment {

    private List<Fragment> fragmentList;
    private ReadFragment readFragment;
    private WriteFragment writeFragment;
    private TransferAdapter adapter;

    @BindView(R.id.tb_set_limit)
    TabLayout tbSetLimit;
    @BindView(R.id.vp_set_limit)
    ViewPager vpSetLimit;
    @BindView(R.id.btn_user_position_cancel)
    Button btnUserPositionCancel;
    @BindView(R.id.btn_user_position_sure)
    Button btnUserPositionSure;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_set_limit, null);
        ButterKnife.bind(view);
        init();
        return view;
    }


    @OnClick({R.id.tb_set_limit, R.id.vp_set_limit, R.id.btn_user_position_cancel, R.id.btn_user_position_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tb_set_limit:
                break;
            case R.id.vp_set_limit:
                break;
            case R.id.btn_user_position_cancel:
                dismiss();
                break;
            case R.id.btn_user_position_sure:
                break;
        }
    }

    private void init(){
        fragmentList = new ArrayList<>();
        readFragment = new ReadFragment();
        writeFragment = new WriteFragment();
        fragmentList.add(readFragment);
        fragmentList.add(writeFragment);
        adapter = new TransferAdapter(getActivity().getSupportFragmentManager(), fragmentList);
        vpSetLimit.setAdapter(adapter);
        vpSetLimit.setOffscreenPageLimit(1);
        tbSetLimit.setupWithViewPager(vpSetLimit);
        tbSetLimit.getTabAt(0).setText("可读");
        tbSetLimit.getTabAt(0).setText("可写");
    }

}
