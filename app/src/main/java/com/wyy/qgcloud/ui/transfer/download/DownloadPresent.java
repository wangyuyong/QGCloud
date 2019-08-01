package com.wyy.qgcloud.ui.transfer.download;

import com.wyy.qgcloud.constant.Api;
import com.wyy.qgcloud.net.DownloadListener;
import com.wyy.qgcloud.net.DownloadTask;
import com.wyy.qgcloud.ui.download.DownloadService;

public class DownloadPresent implements DownloadContract.DownloadPresent {

    DownloadContract.DownloadView view;

    private DownloadService.DownloadBinder binder;

    @Override
    public void start(int userId, int fileId, String fileName, DownloadListener listener) {
        if (binder != null){
            DownloadTask task = new DownloadTask(listener,fileName);
            binder.startDownload(task, Api.CONST_DOWNLOAD + "?userId=" + userId + "&fileId=" + fileId);
        }
    }

    @Override
    public void paused(int position) {
        if (binder != null){
            binder.pauseDownload(position);
        }
    }

    @Override
    public void resume(int position) {
        if (binder != null){
            binder.resumeDownload(position);
        }
    }

    @Override
    public void cancel(int position) {
        if (binder != null){
            binder.cancelDownload(position);
        }
    }

    @Override
    public void bindView(DownloadContract.DownloadView view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    public void setBinder(DownloadService.DownloadBinder binder) {
        this.binder = binder;
    }
}
