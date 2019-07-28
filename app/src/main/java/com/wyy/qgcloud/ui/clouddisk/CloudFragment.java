package com.wyy.qgcloud.ui.clouddisk;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.FileAdapter;
import com.wyy.qgcloud.adapter.OnItemClickedListener;
import com.wyy.qgcloud.enity.FileInfo;
import com.wyy.qgcloud.enity.LoginInfo;

import java.util.ArrayList;
import java.util.List;

public class CloudFragment extends Fragment implements CloudContract.CloudView {

    CloudContract.CloudPresent present;
    RecyclerView fileRv;
    FileAdapter adapter;
    List<FileInfo.DataBean> fileInfos = new ArrayList<>();

    /**
     * 获取从activity得到的用户信息
     */
    LoginInfo.DataBean user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cloud, container, false);
        fileRv = view.findViewById(R.id.rv_file);
        initRecyclerView();
        present = new CloudPresent();
        present.bindView(this);
        initFileList();
        return view;
    }

    @Override
    public void showError(String message) {
        //展示错误信息
    }

    @Override
    public void updataDir(List<FileInfo.DataBean> fileList) {
        fileList.clear();
        fileList.addAll(fileList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        present.unbindView();
    }

    //初始化recyclerview
    private void initRecyclerView(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter = new FileAdapter(fileInfos);
        fileRv.setLayoutManager(manager);
        fileRv.setAdapter(adapter);
        adapter.setListener(new OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                present.openDir(user.getUserId(),position);
            }
        });
    }

    private void initFileList(){
        //user暂时为null
        present.displayRoot(user.getUserId());
    }
}
