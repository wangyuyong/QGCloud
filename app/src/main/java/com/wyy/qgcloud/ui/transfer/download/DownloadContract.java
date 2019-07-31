package com.wyy.qgcloud.ui.transfer.download;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;
import com.wyy.qgcloud.net.DownloadListener;

public interface DownloadContract {
    interface DownloadView extends BaseView{

    }

    interface DownloadPresent extends BasePresent<DownloadView>{

        /**
         * 开始下载
         * @param userId 用户Id
         * @param fileId 文件Id
         */
        void start(int userId, int fileId, String fileName, DownloadListener listener);

        /**
         * 停止下载
         * @param position 位置
         */
        void paused(int position);

        /**
         * 恢复下载
         * @param position 位置
         */
        void resume(int position);

        /**
         * 取消下载
         * @param position 位置
         */
        void cancel(int position);

    }

    interface DownloadModel{

    }
}
