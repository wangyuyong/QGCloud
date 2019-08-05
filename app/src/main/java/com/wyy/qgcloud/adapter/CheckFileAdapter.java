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
import com.wyy.qgcloud.enity.CheckFile;

import java.util.List;

public class CheckFileAdapter extends RecyclerView.Adapter<CheckFileAdapter.ViewHolder> {

    private List<CheckFile> checkFileList;
    private OnItemClickedListener checkListener;
    private OnItemClickedListener deleteListener;

    public CheckFileAdapter(List<CheckFile> checkFileList){
        this.checkFileList = checkFileList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout checkFileLl;
        ImageView checkFileIv;
        TextView fileNameTv;
        ImageView deleteFileIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkFileLl = itemView.findViewById(R.id.ll_check_file);
            checkFileIv = itemView.findViewById(R.id.iv_check_file);
            fileNameTv = itemView.findViewById(R.id.tv_check_file_name);
            deleteFileIv = itemView.findViewById(R.id.imv_check_file_delete);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_adapter_check_file,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        CheckFile checkFile = checkFileList.get(i);
        viewHolder.fileNameTv.setText(checkFile.getFileName());
        viewHolder.checkFileLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkListener != null){
                    checkListener.onItemClick(i);
                }
            }
        });
        viewHolder.deleteFileIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteListener != null){
                    deleteListener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return checkFileList.size();
    }

    public void setCheckListener(OnItemClickedListener checkListener) {
        this.checkListener = checkListener;
    }

    public void setDeleteListener(OnItemClickedListener deleteListener){
        this.deleteListener = deleteListener;
    }
}
