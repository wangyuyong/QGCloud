package com.wyy.qgcloud.ui.clouddisk;

import android.util.Log;

import com.wyy.qgcloud.enity.FileInfo;
import com.wyy.qgcloud.enity.MakeDirInfo;
import com.wyy.qgcloud.enity.RenameInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;;

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
                .subscribe(new Observer<FileInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FileInfo fileInfo) {
                        //有权限
                        if (fileInfo.getStatus()){
                            view.hideHomePage();
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

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

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
                .subscribe(new Observer<FileInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FileInfo fileInfo) {
                        //有权限
                        if (fileInfo.getStatus()){
                            fileInfoList.add(fileInfo);
                            positionList.add(position);
                            view.updataDir(fileInfo.getData());
                            view.showHomePage();
                        }else {
                            //无权限
                            view.showError(fileInfo.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void makeDir(final int userId, final String fileName) {
        //获取文件的全路径
        StringBuffer filePath = new StringBuffer("/");
        for (int i = 0; i < positionList.size(); i++){
            //获取i级目录
            FileInfo.DataBean file = fileInfoList.get(i).getData().get(positionList.get(i));
            //拼接每一级目录，得到全路径
            filePath.append(file.getFileName() + "/");
        }
        //去掉最后一个'/'
        filePath.deleteCharAt(filePath.length() - 1);
        String path = filePath.toString();
        Log.d("CloudPresent",path);
        model.requestMakeDir(userId,path,fileName)
                .subscribe(new Observer<MakeDirInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MakeDirInfo makeDirInfo) {
                        if (makeDirInfo.getStatus()){
                            //有权限，则刷新文件列表
                            model.requestOpenDir(userId,fileInfoList.get(fileInfoList.size() - 2).getData().get(positionList.get(positionList.size() - 1)).getFileId())
                                    .subscribe(new Observer<FileInfo>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(FileInfo fileInfo) {
                                            view.updataDir(fileInfo.getData());
                                            //更新fileInfo容器
                                            fileInfoList.remove(fileInfoList.size() - 1);
                                            fileInfoList.add(fileInfo);
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        }else {
                            view.showError(makeDirInfo.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void back(final int userId) {
        if (fileInfoList.size() > 1){
            //取出上一级文件信息
            FileInfo file = fileInfoList.get(fileInfoList.size() - 2);
            for (int temp : positionList){
                Log.d("CloudPresent","" + temp);
            }
            //获取上一级目录Id
            int folderId = file.getData().get(positionList.get(positionList.size() - 1)).getFolderId();
            final int level = file.getData().get(positionList.get(positionList.size() - 1)).getLevel();
            Log.d("CloudPresent","父目录:" + folderId);
            //移除记录
            fileInfoList.remove(fileInfoList.size() - 1);
            positionList.remove(positionList.size() - 1);
            //发送网络请求
            model.requestOpenDir(userId,folderId)
                    .subscribe(new Observer<FileInfo>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(FileInfo fileInfo) {
                            //如果有权限，返回上一级目录
                            if (fileInfo.getStatus()){
                                if (level == 2){
                                    view.hideHomePage();
                                }
                                view.updataDir(fileInfo.getData());
                            }else {
                                //没有权限，返回根目录并提示用户
                                displayRoot(userId);
                                view.showError(fileInfo.getMessage());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }

    @Override
    public void queryDetail(int position) {
        FileInfo.DataBean dataBean = fileInfoList.get(fileInfoList.size() - 1).getData().get(position);
        view.showDetail(dataBean);
    }

    @Override
    public void rename(final int userId, String fileName, int position) {
        int fileId = fileInfoList.get(fileInfoList.size() - 1).getData().get(position).getFileId();
        model.requestRename(userId,fileId,fileName)
                .subscribe(new Observer<RenameInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RenameInfo renameInfo) {
                        if (renameInfo.getStatus()){
                            //有权限，刷新列表
                            int fileID = fileInfoList.get(fileInfoList.size() - 2).getData().get(positionList.get(positionList.size() - 1)).getFileId();
                            model.requestOpenDir(userId,fileID)
                                    .subscribe(new Observer<FileInfo>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(FileInfo fileInfo) {
                                            fileInfoList.remove(fileInfoList.size() - 1);
                                            fileInfoList.add(fileInfo);
                                            view.updataDir(fileInfo.getData());
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        }else {
                            //无权限，弹出错误信息
                            view.showError(renameInfo.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
