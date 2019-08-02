package com.wyy.qgcloud.ui.clouddisk;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;
import com.wyy.qgcloud.enity.FileInfo;
import com.wyy.qgcloud.enity.FileValidInfo;
import com.wyy.qgcloud.enity.MakeDirInfo;
import com.wyy.qgcloud.enity.RenameInfo;

import java.util.List;

import io.reactivex.Observable;

public interface CloudContract {
    interface CloudView extends BaseView{

        /**
         * 进入文件夹
         * @param fileList 文件列表
         */
        void updataDir(List<FileInfo.DataBean> fileList);

        /**
         * 展示错误信息
         * @param message 错误信息
         */
        void showError(String message);

        /**
         * 隐藏返回键
         */
        void hideHomePage();

        /**
         * 显示返回按钮
         */
        void showHomePage();

        /**
         * 显示详细信息
         * @param dataBean 文件信息
         */
        void showDetail(FileInfo.DataBean dataBean);
    }

    interface CloudPresent extends BasePresent<CloudView>{

        /**
         * 返回根目录
         * @param userId 用户Id
         */
        void displayRoot(int userId);

        /**
         * 打开文件夹
         * @param userId 用户Id
         * @param position 文件夹位置
         */
        void openDir(int userId,int position);

        /**
         * 创建文件夹
         * @param userID 用户Id
         * @param fileName 文件名称
         */
        void makeDir(int userID,String fileName);

        /**
         * 返回上一级目录
         * @param userId 用户Id
         */
        void back(int userId);

        /**
         * 查看文件夹的详细信息
         * @param position 查看的位置
         */
        void queryDetail(int position);

        /**
         * 重命名文件名
         * @param userId 用户Id
         * @param fileName 新文件名
         * @param position 位置
         */
        void rename(int userId,String fileName,int position);

        /**
         * 上传文件
         * @param userId 用户Id
         */
        void upload(int userId);

        /**
         * 删除文件
         * @param userId 用户Id
         * @param position 点击位置
         */
        void deleteFile(int userId,int position);
    }

    interface CloudModel{
        /**
         * 请求打开文件夹
         * @param userId 用户Id
         * @param fileId 文件夹Id
         */
        Observable<FileInfo> requestOpenDir(int userId, int fileId);

        /**
         * 请求打开文件夹
         * @param userId 用户Id
         * @param filePath 用户路径
         * @param fileName 文件名称
         * @return Observable<MakeDirInfo>
         */
        Observable<MakeDirInfo> requestMakeDir(int userId,String filePath,String fileName);

        /**
         * 重命名文件夹
         * @param userId 用户Id
         * @param fileId 文件Id
         * @param fileName 文件名
         * @return Observable<RenameInfo>
         */
        Observable<RenameInfo> requestRename(int userId,int fileId,String fileName);

        /**
         * 请求删除文件
         * @param userId 用户Id
         * @param fileId 文件Id
         * @return Observable<FileValidInfo>
         */
        Observable<FileValidInfo> requestDelete(int userId,int fileId);

    }
}
