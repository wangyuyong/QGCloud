package com.wyy.qgcloud.ui.clouddisk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.FileAdapter;
import com.wyy.qgcloud.adapter.OnItemClickedListener;
import com.wyy.qgcloud.adapter.OnItemMenuClickedListener;
import com.wyy.qgcloud.adapter.OnMultiClickListener;
import com.wyy.qgcloud.app.MyApplication;
import com.wyy.qgcloud.enity.FileInfo;
import com.wyy.qgcloud.enity.FileMessage;
import com.wyy.qgcloud.enity.LoginInfo;
import com.wyy.qgcloud.ui.dialog.ConfigOnClickedListener;
import com.wyy.qgcloud.ui.dialog.DetailDialog;
import com.wyy.qgcloud.ui.dialog.MakeUploadDirWindow;
import com.wyy.qgcloud.ui.dialog.TransferFileDialog;
import com.wyy.qgcloud.ui.dialog.WindowClickedListener;
import com.wyy.qgcloud.ui.dialog.bottomsheet.BottomSheet;
import com.wyy.qgcloud.ui.homePage.HomePageActivity;
import com.wyy.qgcloud.util.MyToast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CloudFragment extends Fragment implements CloudContract.CloudView {

    CloudContract.CloudPresent present;
    RecyclerView fileRv;
    ImageView makeDirIv;
    ImageView backIv;
    FileAdapter adapter;
    List<FileInfo.DataBean> fileInfos = new ArrayList<>();
    LinearLayoutManager manager;
    BottomSheet bottomSheet;

    /**
     * 获取从activity得到的用户信息
     */
    LoginInfo.DataBean User;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cloud, container, false);

        User = ((HomePageActivity)(getActivity())).getUser();

        final int userID = User.getUserId();

        present = new CloudPresent();
        present.bindView(this);

        fileRv = view.findViewById(R.id.rv_file);
        backIv = view.findViewById(R.id.iv_back);
        makeDirIv = view.findViewById(R.id.iv_make_dir);
        bottomSheet = new BottomSheet();
        //返回按钮点击事件
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CloudPresent","点击");
                present.back(User.getUserId());
            }
        });

        //弹出选择框
        makeDirIv.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                final MakeUploadDirWindow window = MakeUploadDirWindow.showWindow(getActivity(),makeDirIv);
                window.setMakeDirListener(new WindowClickedListener() {
                    @Override
                    public void onClick() {
                        window.dismiss();
                        final TransferFileDialog dialog = new TransferFileDialog(getActivity(),R.style.dialog);
                        dialog.setListener(new ConfigOnClickedListener() {
                            @Override
                            public void onClick(String info) {
                                present.makeDir(User.getUserId(),info);
                                dialog.cancel();
                            }
                        });
                        dialog.show();
                    }
                });

                window.setUploadListener(new WindowClickedListener() {
                    @Override
                    public void onClick() {
                        window.dismiss();
                        present.upload(User.getUserId());
                    }
                });
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
        adapter.setItemListener(new OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                present.openDir(User.getUserId(),position);
            }
        });

        //设置菜单的点击事件
        adapter.setMenuListener(new OnItemMenuClickedListener() {
            @Override
            public void onClick(final int position) {
                //设置每一个菜单项的点击列表
                bottomSheet.setDetailListener(new OnMultiClickListener() {
                    @Override
                    public void onMultiClick(View v) {
                        present.queryDetail(position);
                    }
                });
                bottomSheet.setRenameListener(new OnMultiClickListener() {
                    @Override
                    public void onMultiClick(View v) {
                        final TransferFileDialog dialog = new TransferFileDialog(getActivity(),R.style.dialog);
                        dialog.setListener(new ConfigOnClickedListener() {
                            @Override
                            public void onClick(String info) {
                                present.rename(User.getUserId(),info,position);
                                dialog.cancel();
                            }
                        });
                        dialog.show();
                    }
                });
                bottomSheet.setDownloadListener(new OnMultiClickListener() {
                    @Override
                    public void onMultiClick(View v) {
                        FileInfo.DataBean file = fileInfos.get(position);
                        if (file.getFileType().equals("dir")){
                            MyToast.getMyToast().ToastShow(getActivity(),null,R.drawable.ic_sad,"文件夹下载功能暂未开发");
                            return;
                        }
                        FileMessage message = new FileMessage(file.getFileName() + "." + file.getFileType(),file.getUploadTime(),file.getFileId(),User.getUserId());
                        MyToast.getMyToast().ToastShow(getActivity(),null,R.drawable.ic_happy,"已加入下载列表");
                        EventBus.getDefault().post(message);
                    }
                });
                bottomSheet.setDeleteListener(new OnMultiClickListener() {
                    @Override
                    public void onMultiClick(View v) {
                        present.deleteFile(User.getUserId(),position);
                    }
                });
                bottomSheet.show(getActivity().getSupportFragmentManager(),"show");
            }
        });
    }

    private void initFileList(){
        present.displayRoot(User.getUserId());
    }

    @Override
    public void hideHomePage() {
        backIv.setVisibility(View.GONE);
    }

    @Override
    public void showHomePage() {
        backIv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetail(FileInfo.DataBean dataBean) {
        DetailDialog detailDialog = new DetailDialog(getActivity(),R.style.dialog,dataBean);
        detailDialog.show();
    }
}
