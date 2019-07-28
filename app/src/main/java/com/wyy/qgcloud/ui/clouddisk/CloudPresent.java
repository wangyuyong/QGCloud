package com.wyy.qgcloud.ui.clouddisk;

import com.wyy.qgcloud.enity.FileInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class CloudPresent implements CloudContract.CloudPresent {

    CloudContract.CloudView view;
    CloudContract.CloudModel model;
    /**
     * 记录当前文件夹fileInfo以及必要的祖先文件夹
     */
    List<FileInfo> fileInfos = new ArrayList<>();

    public CloudPresent(){
        model = new CloudModel();
    }

    @Override
    public void displayRoot(int userId) {
        model.requestOpenDir(userId,0)
                .subscribe(new Consumer<FileInfo>() {
                    @Override
                    public void accept(FileInfo fileInfo) throws Exception {
                        //有权限
                        if (fileInfo.getMessage() == null){
                            if (fileInfos.size() == 0){
                                fileInfos.add(fileInfo);
                            }else {
                                fileInfos.clear();
                                fileInfos.add(fileInfo);
                            }
                            view.updataDir(fileInfo.getData());
                        }else {
                            //无权限
                            view.showError(fileInfo.getMessage());
                        }
                    }
                });
    }

    @Override
    public void openDir(int userId, int position) {
        //获得文件夹的Id
        int fileId = fileInfos.get(fileInfos.size() - 1)
                .getData()
                .get(position)
                .getFileId();

        model.requestOpenDir(userId,fileId)
                //更新界面
                .subscribe(new Consumer<FileInfo>() {
                    @Override
                    public void accept(FileInfo fileInfo) throws Exception {
                        //有权限
                        if (fileInfo.getMessage() == null){
                            fileInfos.add(fileInfo);
                            view.updataDir(fileInfo.getData());
                        }else {
                            //无权限
                            view.showError(fileInfo.getMessage());
                        }
                    }
                });
    }

    @Override
    public void makeDir(int userId,String fileName) {
        StringBuffer filePath = new StringBuffer();
        for (int i = 0)
    }

    @Override
    public void setDirPath(FileInfo file) {

    }

    @Override
    public void bindView(CloudContract.CloudView view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }
}
