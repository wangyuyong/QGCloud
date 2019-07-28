package com.wyy.qgcloud.ui.clouddisk;

import com.wyy.qgcloud.enity.FileInfo;
import com.wyy.qgcloud.enity.MakeDirInfo;
import com.wyy.qgcloud.net.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CloudModel implements CloudContract.CloudModel {

    @Override
    public Observable<FileInfo> requestOpenDir(int userId, int fileId) {
        return RetrofitManager.getInstance()
                .getHttpService()
                .getFileInfi(userId,fileId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MakeDirInfo> requestMakeDir(int userId, String filePath, String fileName) {
        return RetrofitManager.getInstance()
                .getHttpService()
                .getMakeDirInfo(userId,filePath,fileName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
