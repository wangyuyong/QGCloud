package com.wyy.qgcloud.ui.download;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.wyy.qgcloud.net.DownloadListener;
import com.wyy.qgcloud.net.DownloadTask;

import java.io.File;

public class DownloadService extends Service {

    private static final String TAG = "DownloadService";

    private DownloadTask task;

    private String downloadUrl;

    private String fileName;

    private DownloadBinder mBinder = new DownloadBinder();

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            Log.d(TAG,"" + progress);
        }

        @Override
        public void onSuccess() {
            Log.d(TAG,"success");
        }

        @Override
        public void onFailed() {
            task = null;
            Toast.makeText(DownloadService.this,"下载失败",Toast.LENGTH_LONG).show();
            Log.d(TAG,"faild");
        }

        @Override
        public void onPaused() {
            task = null;
            Toast.makeText(DownloadService.this,"暂停下载",Toast.LENGTH_LONG).show();
            Log.d(TAG,"paused");
        }

        @Override
        public void onCanceled() {
            task = null;
            Toast.makeText(DownloadService.this,"取消下载",Toast.LENGTH_LONG).show();
            Log.d(TAG,"cancel");
        }
    };

    public DownloadService() {
    }

    class DownloadBinder extends Binder{
        public void startDownload(String url,String name){
            if (task == null){
                downloadUrl = url;
                fileName = name;
                task = new DownloadTask(listener,fileName);
                task.execute(downloadUrl);
                Log.d(TAG,"开始下载");
            }
        }

        public void pauseDownload(){
            if (task != null){
                task.pauseDownload();
            }
        }

        public void cancelDownload(){
            if (task != null){
                task.cancelDownload();
            }
            if (fileName != null){
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(directory + fileName);
                if (file.exists()){
                    file.delete();
                }
                Log.d(TAG,"cancel");
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
