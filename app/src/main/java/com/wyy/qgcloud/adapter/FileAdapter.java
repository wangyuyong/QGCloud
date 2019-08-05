package com.wyy.qgcloud.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private OnItemClickedListener itemListener;

    private OnItemMenuClickedListener menuListener;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fileIconIv;
        TextView fileNameTv;
        TextView fileTimeTv;
        ImageView menuIv;
        LinearLayout fileLl;

        public ViewHolder(View itemView) {
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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_adapter_file,viewGroup,false);
        ViewHolder holder = new ViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        FileInfo.DataBean file = fileLists.get(i);
        String fileName = file.getFileName();
        String fileTime = file.getUploadTime();

        viewHolder.fileNameTv.setText(fileName);
        viewHolder.fileTimeTv.setText(fileTime);
        if (!file.getFileType().equals("dir")){
            viewHolder.fileIconIv.setImageResource(R.mipmap.ic_word);
        }else {
            viewHolder.fileIconIv.setImageResource(R.mipmap.ic_dir);
        }

        viewHolder.menuIv.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (menuListener != null){
                    menuListener.onClick(i);
                }
            }
        });

        viewHolder.fileLl.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (itemListener != null){
                    itemListener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("CloudFragment",""+fileLists.size());
        return fileLists.size();
    }

    public void setItemListener(OnItemClickedListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setMenuListener(OnItemMenuClickedListener menuListener){
        this.menuListener = menuListener;
    }
}
