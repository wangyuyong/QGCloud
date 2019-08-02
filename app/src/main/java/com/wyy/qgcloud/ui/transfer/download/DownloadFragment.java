package com.wyy.qgcloud.ui.transfer.download;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.DownloadAdapter;
import com.wyy.qgcloud.adapter.OnItemClickedListener;
import com.wyy.qgcloud.app.MyApplication;
import com.wyy.qgcloud.constant.Api;
import com.wyy.qgcloud.enity.CheckFile;
import com.wyy.qgcloud.enity.FileMessage;
import com.wyy.qgcloud.net.DownloadListener;
import com.wyy.qgcloud.net.DownloadTask;
import com.wyy.qgcloud.ui.download.DownloadService;
import com.wyy.qgcloud.util.MyToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class DownloadFragment extends Fragment implements DownloadContract.DownloadView {

    List<FileMessage> fileMessageList;
    RecyclerView downloadRv;
    LinearLayoutManager manager;
    DownloadAdapter adapter;
    /**
     * 记录下载列表的长度
     */
    private int length;

    //绑定服务
    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download,container,false);
        EventBus.getDefault().register(this);
        //绑定服务
        Intent intent = new Intent(getActivity(),DownloadService.class);
        getActivity().bindService(intent,connection, Context.BIND_AUTO_CREATE);

        downloadRv = view.findViewById(R.id.rv_download_list);
        init();

        return view;
    }

    //初始化组件
    private void init(){
        fileMessageList = new ArrayList<>();
        adapter = new DownloadAdapter(fileMessageList);
        manager = new LinearLayoutManager(getActivity());
        downloadRv.setLayoutManager(manager);
        downloadRv.setAdapter(adapter);
        adapter.setListener(new OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                int firstItem = manager.findFirstVisibleItemPosition();
                DownloadAdapter.ViewHolder viewHolder = null;
                View view =  downloadRv.getChildAt(position - firstItem);
                if (null != downloadRv.getChildViewHolder(view)){
                    viewHolder = (DownloadAdapter.ViewHolder)downloadRv.getChildViewHolder(view);
                    if (viewHolder.isStart()){
                        downloadBinder.pauseDownload(position);
                        viewHolder.setPaused();
                        viewHolder.reserveStart();
                    }else {
                        DownloadTask task = new DownloadTask(new DownloadClass(position),fileMessageList.get(position).getFileName());
                        downloadBinder.resumeDownload(task,position);
                        viewHolder.setStart();
                        viewHolder.reserveStart();
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        getActivity().unbindService(connection);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void startDownload(FileMessage message){
        for (FileMessage temp : fileMessageList){
            if (temp.getFileId() == message.getFileId()){
                MyToast.getMyToast().ToastShow(getActivity(),null,R.drawable.ic_sad,"文件已在下载列表");
                return;
            }
        }
        //将文件添加到下载列表
        fileMessageList.add(message);
        adapter.notifyDataSetChanged();
        //开始下载文件
        String fileName = message.getFileName();
        String fileUploadTime = message.getFileUploadTime();
        String downloadUrl = Api.CONST_DOWNLOAD  + "?userId=" + message.getUserId() + "&fileId=" + message.getFileId();
        downloadBinder.startDownload(new DownloadTask(new DownloadClass(fileMessageList.size() - 1),fileName),downloadUrl);
    }

    class DownloadClass implements DownloadListener{
        /**
         * 位置
         */
        private int position;

        public DownloadClass(int position) {
            this.position = position;
        }

        @Override
        public void onProgress(int progress) {
            Log.d("DownloadFragment","" + Thread.currentThread().getName() + ":" + progress);
            adapter.setProgress(position,progress);
        }

        @Override
        public void onSuccess() {
            fileMessageList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(MyApplication.getContext(),"下载成功",Toast.LENGTH_SHORT);
            CheckFile checkFile = new CheckFile(fileMessageList.get(position).getFileName());
            EventBus.getDefault().post(checkFile);
        }

        @Override
        public void onFailed() {
            MyToast.getMyToast().ToastShow(getActivity(),null,R.drawable.ic_sad,"下载失败，网速不佳");
        }

        @Override
        public void onPaused() {
            MyToast.getMyToast().ToastShow(getActivity(),null,R.drawable.ic_happy,"暂停成功");
        }

        @Override
        public void onCanceled() {
            MyToast.getMyToast().ToastShow(getActivity(),null,R.drawable.ic_happy,"下载取消");
        }
    }
}
