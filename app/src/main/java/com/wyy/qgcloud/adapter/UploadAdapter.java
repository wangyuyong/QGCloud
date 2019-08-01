package com.wyy.qgcloud.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUIProgressBar;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.enity.UploadFileMessage;

import java.util.ArrayList;
import java.util.List;

public class UploadAdapter extends RecyclerView.Adapter<UploadAdapter.ViewHolder> {

    private List<UploadFileMessage> uploadFileMessageList;
    private List<ViewHolder> viewHolderList = new ArrayList<>();
    private OnItemClickedListener listener;

    public UploadAdapter(List<UploadFileMessage> uploadFileMessageList) {
        this.uploadFileMessageList = uploadFileMessageList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fileIv;
        TextView fileNameTv;
        TextView fileUploadTimeTv;
        QMUIProgressBar fileUploadPb;

        private boolean isStart = true;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fileUploadPb = itemView.findViewById(R.id.pb_download);
            fileIv = itemView.findViewById(R.id.iv_download_file);
            fileNameTv = itemView.findViewById(R.id.tv_download_file_name);
            fileUploadTimeTv = itemView.findViewById(R.id.tv_download_file_upload_time);
        }

        public void reserveStart(){
            if (isStart){
                isStart = false;
            }else {
                isStart = true;
            }
        }

        public boolean isStart(){
            return isStart;
        }

        public void setPaused(){
            fileUploadPb.setBackgroundResource(R.mipmap.ic_start);
        }

        public void setStart(){
            fileUploadPb.setBackgroundResource(R.mipmap.ic_stop);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_adapter_download,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.fileNameTv.setText(uploadFileMessageList.get(i).getFileName());
        viewHolder.fileUploadTimeTv.setText(uploadFileMessageList.get(i).getFileUploadTime());
        viewHolder.fileUploadPb.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (listener != null){
                    listener.onItemClick(i);
                }
            }
        });
        viewHolderList.add(viewHolder);
    }

    @Override
    public int getItemCount() {
        return uploadFileMessageList.size();
    }

    public void setProgress(int position,int progress){
        Log.d("UploadAdapter","position:" + position);
        /*ViewHolder viewHolder = viewHolderList.get(position);
        viewHolder.fileUploadPb.setProgress(progress);*/
    }

    public void setListener(OnItemClickedListener listener) {
        this.listener = listener;
    }
}
