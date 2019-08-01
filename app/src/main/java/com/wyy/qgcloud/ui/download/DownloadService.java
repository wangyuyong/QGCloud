package com.wyy.qgcloud.ui.download;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import com.wyy.qgcloud.constant.Directory;
import com.wyy.qgcloud.net.DownloadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 开启服务下载文件
 */
public class DownloadService extends Service {

    private static final String TAG = "DownloadService";

    private List<DownloadTask> downloadTasks;
    private List<String> downloadUrls;

    private DownloadBinder mBinder = new DownloadBinder();

    public DownloadService() {
        downloadTasks = new ArrayList<>();
        downloadUrls = new ArrayList<>();
    }

    public class DownloadBinder extends Binder{
        public void startDownload(DownloadTask task,String downloadUrl){
            if (task != null){
                //加入下载线程组
                downloadTasks.add(task);
                downloadUrls.add(downloadUrl);
                //开始下载
                task.execute(downloadUrl);
                Log.d(TAG,"开始下载");
            }
        }

        public void pauseDownload(int position){
            if (!downloadTasks.isEmpty()){
                //取出对应下载线程
                DownloadTask task = downloadTasks.get(position);
                task.pauseDownload();
            }
        }

        public void resumeDownload(int position){
            DownloadTask task = downloadTasks.get(position);
            task.execute(downloadUrls.get(position));
        }

        public void cancelDownload(int position){
            if (!downloadTasks.isEmpty()){
                //取出对应线程
                DownloadTask task = downloadTasks.get(position);
                task.cancelDownload();
                //删除取消下载的文件
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + Directory.CONST_SAVE_DIRECTORY;
                File file = new File(directory + "/" + task.getFileName());
                if (file.exists()){
                    file.delete();
                }
                //从线程组中移除
                downloadTasks.remove(position);
                downloadUrls.remove(position);
            }
            Log.d(TAG,"cancel");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
