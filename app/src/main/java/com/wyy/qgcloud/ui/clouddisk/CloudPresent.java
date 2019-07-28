package com.wyy.qgcloud.ui.clouddisk;

import com.wyy.qgcloud.enity.FileInfo;
import com.wyy.qgcloud.enity.MakeDirInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class CloudPresent implements CloudContract.CloudPresent {

    CloudContract.CloudView view;
    CloudContract.CloudModel model;
    /**
     * 记录当前文件夹fileInfo以及必要的祖先文件夹
     */
    private List<FileInfo> fileInfoList = new ArrayList<>();

    /**
     * 记录每次点击的位置
     */
    private List<Integer> positionList = new ArrayList<>();

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
                            if (fileInfoList.size() == 0){
                                fileInfoList.add(fileInfo);
                            }else {
                                fileInfoList.clear();
                                positionList.clear();
                                fileInfoList.add(fileInfo);
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
    public void openDir(int userId, final int position) {
        //获得文件夹的Id
        int fileId = fileInfoList.get(fileInfoList.size() - 1)
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
                            fileInfoList.add(fileInfo);
                            positionList.add(position);
                            view.updataDir(fileInfo.getData());
                        }else {
                            //无权限
                            view.showError(fileInfo.getMessage());
                        }
                    }
                });
    }

    @Override
    public void makeDir(int userId, final String fileName) {
        //获取文件的全路径
        StringBuffer filePath = new StringBuffer();
        for (int i = 0; i < positionList.size(); i++){
            //获取i级目录
            FileInfo.DataBean file = fileInfoList.get(i).getData().get(positionList.get(i));
            //拼接每一级目录，得到全路径
            filePath.append(file.getFileName() + "/");
        }
        //去掉最后一个'/'
        filePath.deleteCharAt(filePath.length() - 1);
        String path = filePath.toString();
        model.requestMakeDir(userId,path,fileName)
                .subscribe(new Consumer<MakeDirInfo>() {
                    @Override
                    public void accept(MakeDirInfo makeDirInfo) throws Exception {
                        if (makeDirInfo.getMessage() == null){
                            //有权限，则将FileInfo.Databean添加到容器中
                            FileInfo.DataBean newDir = new FileInfo.DataBean();
                            newDir.setFileName(fileName);
                            newDir.setFileId(makeDirInfo.getData().getFileId());
                            newDir.setUploadTime(makeDirInfo.getData().getUploadTime());
                            //添加至databean容器中
                            fileInfoList.get(positionList.get(positionList.size() - 1)).getData().add(newDir);
                            view.makeDir(newDir);
                        }else {
                            view.showError(makeDirInfo.getMessage());
                        }
                    }
                });
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
