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
import com.wyy.qgcloud.util.MyToast;

import java.util.ArrayList;
import java.util.List;

public class CloudFragment extends Fragment implements CloudContract.CloudView {

    CloudContract.CloudPresent present;
    RecyclerView fileRv;
    ImageButton makeDirIbtn;
    ImageButton backIbtn;
    FileAdapter adapter;
    List<FileInfo.DataBean> fileInfos = new ArrayList<>();
    LinearLayoutManager manager;

    /**
     * 获取从activity得到的用户信息
     */
    LoginInfo.DataBean user = new LoginInfo.DataBean();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cloud, container, false);

        //待删
        user.setUserId(1);

        present = new CloudPresent();
        present.bindView(this);

        FileInfo.DataBean test = new FileInfo.DataBean();

        fileRv = view.findViewById(R.id.rv_file);
        backIbtn = view.findViewById(R.id.ibtn_back);
        makeDirIbtn = view.findViewById(R.id.ibtn_make_dir);
        //返回按钮点击事件
        backIbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CloudPresent","点击");
                present.back(user.getUserId());
            }
        });
        makeDirIbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TransferFileDialog dialog = new TransferFileDialog(getActivity(),R.style.dialog);
                dialog.setListener(new ConfigOnClickedListener() {
                    @Override
                    public void onClick(String info) {
                        dialog.cancel();
                        present.makeDir(user.getUserId(),info);
                    }
                });
                dialog.show();
            }
        });
        initFileList();
        initRecyclerView();
        return view;
    }

    @Override
    public void showError(String message) {
        MyToast.getMyToast().ToastShow(getActivity(),null,R.drawable.ic_sad,message);
    }

    @Override
    public void updataDir(List<FileInfo.DataBean> fileList) {
        fileInfos.clear();
        fileInfos.addAll(fileList);
        for (FileInfo.DataBean temp: fileList){
            Log.d("CloudFragment",temp.getFileName());
        }
        Log.d("CloudFragment","success");
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

    /**
     * 初始化滑动组件
     */
    private void initRecyclerView(){
        manager = new LinearLayoutManager(getActivity());
        adapter = new FileAdapter(fileInfos);
        fileRv.setLayoutManager(manager);
        fileRv.setAdapter(adapter);
        adapter.setListener(new OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                present.openDir(1,position);
            }
        });
    }

    private void initFileList(){
        present.displayRoot(user.getUserId());
    }

    @Override
    public void hideHomePage() {
        backIbtn.setVisibility(View.GONE);
    }

    @Override
    public void showHomePage() {
        backIbtn.setVisibility(View.VISIBLE);
    }
}
