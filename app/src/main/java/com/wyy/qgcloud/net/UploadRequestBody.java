package com.wyy.qgcloud.net;

import android.util.Log;

import com.wyy.qgcloud.util.SharedPerencesUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
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
    private boolean isPaused;

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
    public void writeTo(@NotNull BufferedSink bufferedSink){
        Source source = null;
        try {
            source = Okio.source(file);
            long total = SharedPerencesUtil.getLength(file.getName());
            Log.d("UploadRequestBody", "断点长度:" + total);
            long read;
            long length = file.length();

            while ((read = source.read(bufferedSink.getBuffer(), 1024 * 10)) != -1) {
                total += read;
                bufferedSink.flush();
                if (listener != null) {
                    listener.onProgress((int) (100 * total / length));
                }
                if (isPaused){
                    SharedPerencesUtil.saveLength(file.getName(),total);
                    listener.onPaused();
                    return;
                }
            }
                listener.onSucceed();
                SharedPerencesUtil.deleteLength(file.getName());
        } catch (Exception e) {
            if (listener != null) {
                if (!isPaused) {
                    listener.onFailed();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                Util.closeQuietly(source);
                bufferedSink.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.w("淇淇关流失败!","墉墉死了");
            }
        }
    }

    public void paused(){
        isPaused = true;
    }
}
