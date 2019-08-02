package com.wyy.qgcloud.net;

import android.util.Log;

import com.wyy.qgcloud.util.SharedPerencesUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class UploadRequestBody extends RequestBody {
    /**
     * 上传文件
     */
    private File file;

    /**
     * 上传监听
     */
    private UploadListener listener;

    /**
     * 暂停下载
     */
    private boolean isPaused = true;

    public UploadRequestBody(File file,UploadListener listener) {
        this.file = file;
        this.listener = listener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return MediaType.parse("application/octet-stream");
    }

    @Override
    public long contentLength() throws IOException {
        return file.length();
    }

    @Override
    public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {

        Source source = null;
        try {
            source = Okio.source(file);
            long total = 0;
            long read;
            long length = file.length();

            while ((read = source.read(bufferedSink.getBuffer(),1024 * 10)) != -1){
                total += read;
                bufferedSink.flush();
                if (listener != null){
                    listener.onProgress((int) (total/length));
                }
            }
        }catch (Exception e){
            if (listener != null){
                listener.onFailed();
            }
            e.printStackTrace();
        }finally {
            Util.closeQuietly(source);
        }

        /*//文件总长度
        long fileTotal = file.length();
        //已上传长度
        int uploadLength = SharedPerencesUtil.getLength(file.getName());

        byte[] bytes = new byte[1024 * 10];
        FileInputStream inputStream = new FileInputStream(file);

        try {
            int len;
            while ((len = inputStream.read(bytes)) != -1){
                if (!isPaused){
                    SharedPerencesUtil.saveLength(file.getName(),uploadLength);
                    Log.d("UploadRequestBody","断点长度:" + uploadLength);
                    return;
                }
                bufferedSink.write(bytes,0,len);
                uploadLength += len;
                if (listener != null){
                    listener.onProgress((int) (100 * uploadLength / fileTotal));
                }
            }
            listener.onSucceed();
        }catch (Exception e){
            listener.onFailed();
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                inputStream.close();
            }
        }*/
    }

    public void paused(){
        isPaused = true;
    }
}
