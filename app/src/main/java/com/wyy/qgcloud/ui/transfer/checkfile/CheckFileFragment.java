package com.wyy.qgcloud.ui.transfer.checkfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.wyy.qgcloud.BuildConfig;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.CheckFileAdapter;
import com.wyy.qgcloud.adapter.OnItemClickedListener;
import com.wyy.qgcloud.constant.Directory;
import com.wyy.qgcloud.enity.CheckFile;
import com.wyy.qgcloud.util.DirectoryUtil;
import com.wyy.qgcloud.util.OpenFileUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CheckFileFragment extends Fragment {

    RecyclerView checkFileRv;
    LinearLayoutManager manager;
    CheckFileAdapter adapter;
    List<CheckFile> checkFileList;
    LinearLayout noFileLl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_file,container,false);
        EventBus.getDefault().register(this);
        checkFileRv = view.findViewById(R.id.rv_check_file);
        noFileLl = view.findViewById(R.id.ll_no_file);

        init();

        return view;
    }

    public void init(){
        checkFileList = new ArrayList<>();
        File fileName = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + Directory.CONST_SAVE_DIRECTORY);
        if (!fileName.exists()){
            fileName.exists();
        }
        String[] fileList = fileName.list();
        //将文件加中的文件添加到容器中
        if(fileList != null) {
            for (String temp : fileList){
                CheckFile checkFile = new CheckFile(temp);
                checkFileList.add(checkFile);
            }
        }
        if (checkFileList.isEmpty()){
            noFileLl.setVisibility(View.VISIBLE);
        }
        manager = new LinearLayoutManager(getActivity());
        adapter = new CheckFileAdapter(checkFileList);
        checkFileRv.setLayoutManager(manager);
        checkFileRv.setAdapter(adapter);
        adapter.setCheckListener(new OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + Directory.CONST_SAVE_DIRECTORY + "/" + checkFileList.get(position).getFileName());
                /*Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".fileprovider",file);
                intent.setDataAndType(uri, DirectoryUtil.getMIMEType(checkFileList.get(position).getFileName()));
                startActivity(intent);*/
                try {
                    OpenFileUtil.openFile(getActivity(),file);
                }catch (Exception e){
                    Toast.makeText(getActivity(),"无法打开文件",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        adapter.setDeleteListener(new OnItemClickedListener() {
            @Override
            public void onItemClick(final int position) {
                final File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + Directory.CONST_SAVE_DIRECTORY + "/" + checkFileList.get(position).getFileName());
                if (file.exists()){
                    new QMUIDialog.MessageDialogBuilder(getActivity())
                            .setTitle("提示")
                            .setMessage("确定要删除吗？")
                            .addAction("取消", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            })
                            .addAction(0,"确认", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    file.delete();
                                    Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_SHORT).show();
                                    checkFileList.remove(position);
                                    adapter.notifyDataSetChanged();
                                    dialog.dismiss();
                                    if (checkFileList.isEmpty()){
                                        noFileLl.setVisibility(View.VISIBLE);
                                    }
                                }
                            })
                            .show();
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updataList(CheckFile message){
        checkFileList.add(message);
        adapter.notifyDataSetChanged();
        if (!checkFileList.isEmpty()){
            noFileLl.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
