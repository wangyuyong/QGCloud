package com.wyy.qgcloud.ui.transfer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.TransferAdapter;
import com.wyy.qgcloud.ui.transfer.checkfile.CheckFileFragment;
import com.wyy.qgcloud.ui.transfer.download.DownloadFragment;
import com.wyy.qgcloud.ui.transfer.upload.UploadFragment;

import java.util.ArrayList;
import java.util.List;

public class TransferFragment extends Fragment {

    private List<Fragment> fragmentList;
    private UploadFragment uploadFragment;
    private DownloadFragment dowloadFragment;
    private ViewPager transferVp;
    private TabLayout transferTb;
    private TransferAdapter adapter;
    private CheckFileFragment checkFileFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transfer,container,false);
        transferTb = view.findViewById(R.id.tb_transfer);
        transferVp = view.findViewById(R.id.vp_transfer);
        init();
        return view;
    }

    //初始化界面
    private void init(){
        fragmentList = new ArrayList<>();
        uploadFragment = new UploadFragment();
        dowloadFragment = new DownloadFragment();
        checkFileFragment = new CheckFileFragment();
        fragmentList.add(uploadFragment);
        fragmentList.add(dowloadFragment);
        fragmentList.add(checkFileFragment);
        adapter = new TransferAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        transferVp.setAdapter(adapter);
        transferVp.setOffscreenPageLimit(2);
        transferTb.setupWithViewPager(transferVp);
        transferTb.getTabAt(0).setText("上传");
        transferTb.getTabAt(1).setText("下载");
        transferTb.getTabAt(2).setText("查看");
    }
}
