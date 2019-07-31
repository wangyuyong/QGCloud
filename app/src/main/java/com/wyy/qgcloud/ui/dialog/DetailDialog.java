package com.wyy.qgcloud.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.wyy.qgcloud.R;
import com.wyy.qgcloud.enity.FileInfo;


public class DetailDialog extends Dialog {

    TextView dirNameTv;
    TextView uploadPersonTv;
    TextView uploadTimeTv;
    TextView downloadCountTv;

    private FileInfo.DataBean dataBean;


    public DetailDialog(Context context) {
        super(context);
    }

    public DetailDialog(Context context, int themeResId,FileInfo.DataBean dataBean) {
        super(context, themeResId);
        this.dataBean = dataBean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_detail_information);
        dirNameTv = findViewById(R.id.tv_dir_name);
        uploadPersonTv = findViewById(R.id.tv_upload_person);
        uploadTimeTv = findViewById(R.id.tv_upload_time);
        downloadCountTv = findViewById(R.id.tv_download_count);
        setInformation(dataBean);
    }

    private void setInformation(FileInfo.DataBean dataBean){
        dirNameTv.setText(dataBean.getFileName());
        uploadPersonTv.setText(dataBean.getUserName());
        uploadTimeTv.setText(dataBean.getUploadTime());
        downloadCountTv.setText("" + dataBean.getDownloadCount());
    }

}
