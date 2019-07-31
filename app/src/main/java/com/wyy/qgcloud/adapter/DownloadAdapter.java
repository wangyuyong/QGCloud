package com.wyy.qgcloud.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUIProgressBar;
import com.wyy.qgcloud.R;
import com.wyy.qgcloud.enity.FileMessage;

import java.util.List;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.ViewHolder>{

    private List<FileMessage> fileMessageList;

    private OnItemClickedListener listener;

    public DownloadAdapter(List<FileMessage> fileMessageList){
        this.fileMessageList = fileMessageList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private QMUIProgressBar downloadPb;
        private ImageView fileIv;
        private TextView fileNameTv;
        private TextView fileUploadTimeTv;
        private boolean isStart = true;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            downloadPb = itemView.findViewById(R.id.pb_download);
            fileIv = itemView.findViewById(R.id.iv_download_file);
            fileNameTv = itemView.findViewById(R.id.tv_download_file_name);
            fileUploadTimeTv = itemView.findViewById(R.id.tv_download_file_upload_time);
        }

        public void setProgress(int progress){
            downloadPb.setProgress(progress);
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
            downloadPb.setBackgroundResource(R.mipmap.ic_start);
        }

        public void setStart(){
            downloadPb.setBackgroundResource(R.mipmap.ic_stop);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_adapter_download,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        FileMessage message = fileMessageList.get(i);
        viewHolder.fileNameTv.setText(message.getFileName());
        viewHolder.fileUploadTimeTv.setText(message.getFileUploadTime());
        viewHolder.downloadPb.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (listener != null){
                    listener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileMessageList.size();
    }

    public void setListener(OnItemClickedListener listener) {
        this.listener = listener;
    }
}
