package com.wyy.qgcloud.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.enity.FileInfo;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private List<FileInfo.DataBean> fileLists;

    private OnItemClickedListener listener;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fileIconIv;
        TextView fileNameTv;
        TextView fileTimeTv;
        ImageView menuIv;
        LinearLayout fileLl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fileIconIv = itemView.findViewById(R.id.iv_item_icon);
            fileNameTv = itemView.findViewById(R.id.tv_file_name);
            fileTimeTv = itemView.findViewById(R.id.tv_time);
            menuIv = itemView.findViewById(R.id.iv_file_menu);
            fileLl = itemView.findViewById(R.id.ll_file);
        }
    }

    public FileAdapter(List<FileInfo.DataBean> fileLists) {
        this.fileLists = fileLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_adapter_file,viewGroup,false);
        ViewHolder holder = new ViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String fileName = fileLists.get(i).getFileName();
        String fileTime = fileLists.get(i).getUploadTime();

        viewHolder.fileNameTv.setText(fileName);
        viewHolder.fileTimeTv.setText(fileTime);

        //待更改
        viewHolder.fileIconIv.setImageResource(R.mipmap.ic_launcher);

        viewHolder.menuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //待写
            }
        });

        viewHolder.fileLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileLists.size();
    }

    public void setListener(OnItemClickedListener listener) {
        this.listener = listener;
    }
}
