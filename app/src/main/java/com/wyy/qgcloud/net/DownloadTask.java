package com.wyy.qgcloud.net;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.wyy.qgcloud.constant.Directory;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String,Integer,Integer> {
    /**
     * 下载成功
     */
    public static final int CONST_SUCCESS = 0;
    /**
     * 下载失败
     */
    public static final int CONST_FAILED = 1;
    /**
     * 暂停下载
     */
    public static final int CONST_PAUSED = 2;
    /**
     * 取消下载
     */
    public static final int CONST_CANCELED = 3;

    /**
     * 监听下载状态
     */
    private DownloadListener listener;

    //获取文件总长度
    long contentLength;

    private boolean isCanceled = false;
    private boolean isPaused = false;

    private int lastProgress;

    private String fileName;

    private static final String TAG = "DownloadTask";

    public DownloadTask(DownloadListener listener,String fileName){
        this.listener = listener;
        this.fileName = fileName;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        InputStream is = null;
        RandomAccessFile saveFile = null;
        File file = null;
        try {
            //记录下载长度
            long downloadedLength = 0;
            String downloadUrl = strings[0];
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + Directory.CONST_SAVE_DIRECTORY;
            File myFile = new File(directory);
            if (!myFile.exists()){
                myFile.mkdir();
            }
            file = new File(directory + "/" + fileName);
            Log.d("DownloadTask",directory);
            //文件存在，记录下载的长度
            if (file.exists()){
                downloadedLength = file.length();
            }
            OkHttpClient client = new OkHttpClient.Builder()
                    .writeTimeout(10000,TimeUnit.MILLISECONDS)
                    .readTimeout(10000,TimeUnit.MILLISECONDS)
                    .addNetworkInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Response response = chain.proceed(chain.request());
                            contentLength = Integer.valueOf(response.header("Content-Length ","0"));
                            return response;
                        }
                    })
                    .build();
            Request request = new Request.Builder()
                    .addHeader("Range","bytes=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            Log.d("DownloadTask","" + contentLength);
            if (contentLength == 0){
                //文件总长度为0,下载失败
                return CONST_FAILED;
            }else if (contentLength == downloadedLength){
                return CONST_SUCCESS;
            }
            if (response != null){
                is = response.body().byteStream();
                //随机读写文件
                saveFile = new RandomAccessFile(file,"rw");
                //跳过已下载的字节
                saveFile.seek(downloadedLength);
                //一次读取的字节数
                byte[] bytes = new byte[1024 * 8];
                //下载总数
                int total = 0;
                int len;
                while ((len = is.read(bytes)) != -1){
                    if (isCanceled){
                        return CONST_CANCELED;
                    }else if (isPaused){
                        return CONST_PAUSED;
                    }else {
                        total += len;
                        saveFile.write(bytes,0,len);
                        //计算已下载的百分比
                        int progress = (int)((total + downloadedLength) * 100 / contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return CONST_SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
                if (saveFile != null){
                    saveFile.close();
                }
                if (isCanceled && file != null){
                    file.delete();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return CONST_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress){
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case CONST_SUCCESS:
                listener.onSuccess();
                break;
            case CONST_FAILED:
                listener.onFailed();
                break;
            case CONST_PAUSED:
                listener.onPaused();
                break;
            case CONST_CANCELED:
                listener.onCanceled();
                break;
            default:
                break;
        }
    }

    public void pauseDownload(){
        isPaused = true;
    }

    public void cancelDownload(){
        isCanceled = true;
    }

    /**
     * 获取文件总大小
     * @param downloadUrl url
     * @return long
     */
    private long getContentLength(String downloadUrl){
        //文件总大小
        long contentLength = 0;
        //http响应
        Response response = null;
        //建立httpclient
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Request request = new Request.Builder()
                .get()
                .url(downloadUrl)
                .build();
        try {
            //发送http请求
            response = client.newCall(request).execute();
            if (response != null && response.isSuccessful()){
                Log.d(TAG,"链接成功");
                //获取文件总大小
                contentLength = Integer.valueOf(response.header("Content-Length","0"));
                Log.d("DownloadTask","" + contentLength);
            }else {
                Log.d(TAG,"链接失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            response.body().close();
        }
        return contentLength;
    }

    public String getFileName() {
        return fileName;
    }
}
