package com.wyy.qgcloud.ui.clouddisk;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.FileAdapter;
import com.wyy.qgcloud.adapter.OnItemClickedListener;
import com.wyy.qgcloud.enity.FileInfo;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.dialog.ConfigOnClickedListener;
import com.wyy.qgcloud.ui.dialog.TransferFileDialog;

import java.util.ArrayList;
import java.util.List;

public class CloudFragment extends Fragment implements CloudContract.CloudView {

    CloudContract.CloudPresent present;
    RecyclerView fileRv;
    ImageButton transferIBtn;
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
        transferIBtn = view.findViewById(R.id.ibtn_transfer_dir);
        transferIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransferFileDialog dialog = new TransferFileDialog(getActivity(),R.style.dialog);
                dialog.setListener(new ConfigOnClickedListener() {
                    @Override
                    public void onClick(String info) {
                        present.makeDir(user.getUserId(),info);
                    }
                });
                dialog.show();
            }
        });
        initRecyclerView();
        present = new CloudPresent();
        present.bindView(this);
        initFileList();
        return view;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void updataDir(List<FileInfo.DataBean> fileList) {
        fileInfos.clear();
        fileInfos.addAll(fileList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void makeDir(FileInfo.DataBean dir) {
        fileInfos.add(dir);
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
