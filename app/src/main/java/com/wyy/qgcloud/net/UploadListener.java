package com.wyy.qgcloud.net;

public interface UploadListener {

    /**
     * 上传进度
     * @param progress 进度
     */
    void onProgress(int progress);

    /**
     * 上传失败
     */
    void onFailed();

    /**
     * 上传成功
     */
    void onSucceed();

    /**
     * 暂停
     */
    void onPaused();
}
