package com.wyy.qgcloud.ui.transfer.upload;

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
import android.widget.Toast;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.adapter.DownloadAdapter;
import com.wyy.qgcloud.adapter.OnItemClickedListener;
import com.wyy.qgcloud.adapter.UploadAdapter;
import com.wyy.qgcloud.app.MyApplication;
import com.wyy.qgcloud.enity.FileValidInfo;
import com.wyy.qgcloud.enity.UploadFileMessage;
import com.wyy.qgcloud.net.RetrofitManager;
import com.wyy.qgcloud.net.UploadListener;
import com.wyy.qgcloud.net.UploadRequestBody;
import com.wyy.qgcloud.ui.transfer.download.DownloadContract;
import com.wyy.qgcloud.util.MyToast;
import com.wyy.qgcloud.util.SharedPerencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadFragment extends Fragment implements DownloadContract.DownloadView {

    RecyclerView uploadFileRv;
    LinearLayoutManager manager;
    UploadAdapter adapter;
    List<UploadFileMessage> uploadFileMessageList;
    List<UploadRequestBody> bodyList = new ArrayList<>();

    private static final int CONST_FIRST = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload,container,false);
        EventBus.getDefault().register(this);
        uploadFileRv = view.findViewById(R.id.rv_upload_file);
        init();

        return view;
    }

    public void init(){
        uploadFileMessageList = new ArrayList<>();
        manager = new LinearLayoutManager(getActivity());
        adapter = new UploadAdapter(uploadFileMessageList);
        uploadFileRv.setLayoutManager(manager);
        uploadFileRv.setAdapter(adapter);
        adapter.setListener(new OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                int firstItem = manager.findFirstVisibleItemPosition();
                DownloadAdapter.ViewHolder viewHolder = null;
                View view =  uploadFileRv.getChildAt(position - firstItem);
                if (null != uploadFileRv.getChildViewHolder(view)){
                    viewHolder = (DownloadAdapter.ViewHolder)uploadFileRv.getChildViewHolder(view);
                    if (viewHolder.isStart()){
                        viewHolder.reserveStart();
                        viewHolder.setPaused();
                        bodyList.get(position).paused();
                    }else {
                        viewHolder.reserveStart();
                        viewHolder.setStart();
                        getFile(uploadFileMessageList.get(position),position);
                    }
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void startUpload(UploadFileMessage msg){
        uploadFileMessageList.add(msg);
        adapter.notifyDataSetChanged();
        getFile(msg,CONST_FIRST);
    }

    private void getFile(UploadFileMessage msg,int count){
        //创建请求体
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        UploadRequestBody body = null;
        File file = new File(msg.getUploadFile());
        if (count == CONST_FIRST){
            body = new UploadRequestBody(file,new UploadFile(uploadFileMessageList.size() - 1,msg));
        }else {
            body = new UploadRequestBody(file,new UploadFile(count,msg));
        }
        //第一次将请求体加入列表
        if (count == CONST_FIRST){
            bodyList.add(body);
        }else {
            //不是第一次请求，将该请求替换原来的请求
            bodyList.set(count,body);
        }
        builder.addFormDataPart("userId",msg.getUserId() + "");
        Log.d("UploadFragment","userId:" + msg.getUserId());
        builder.addFormDataPart("filePath",msg.getFilePath());
        Log.d("UploadFragment","filePath:" + msg.getFilePath());
        builder.addFormDataPart("upload",msg.getFileName(),body);
        Log.d("UploadFragment","fileName:" + msg.getFileName());
        List<MultipartBody.Part> parts = builder.build().parts();

        //已下载长度
        int length = SharedPerencesUtil.getLength(msg.getUploadFile());

        RetrofitManager.getInstance()
                .getHttpService()
                .uploadFile(parts,length)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FileValidInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FileValidInfo fileValidInfo) {
                        if (fileValidInfo.getStatus()){
                            MyToast.getMyToast().ToastShow(MyApplication.getContext(),null,R.drawable.ic_happy,fileValidInfo.getMessage());
                        }else {
                            MyToast.getMyToast().ToastShow(MyApplication.getContext(),null,R.drawable.ic_sad,fileValidInfo.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyToast.getMyToast().ToastShow(MyApplication.getContext(),null,R.drawable.ic_sad,"未知错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    class UploadFile implements UploadListener{

        private int position;
        private UploadFileMessage message;

        public UploadFile(int position,UploadFileMessage message){
            this.position = position;
            this.message = message;
        }

        @Override
        public void onProgress(int progress) {
            Log.d("UploadFragment","progress:" + progress);
            adapter.setProgress(position,progress);
        }

        @Override
        public void onFailed() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MyToast.getMyToast().ToastShow(MyApplication.getContext(),null,R.drawable.ic_sad,"未知错误");
                }
            });
        }

        @Override
        public void onSucceed() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(),message.getFileName() + "上传成功",Toast.LENGTH_SHORT).show();
                    uploadFileMessageList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
