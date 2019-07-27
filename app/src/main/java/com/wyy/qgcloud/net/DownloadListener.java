package com.wyy.qgcloud.net;

public interface DownloadListener {
    /**
     * 完成进度
     * @param progress 完成进度
     */
    void onProgress(int progress);

    /**
     * 下载成功
     */
    void onSuccess();

    /**
     * 下载失败
     */
    void onFailed();

    /**
     * 暂停下载
     */
    void onPaused();

    /**
     * 取消下载
     */
    void onCanceled();
}
